/********************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   ArSysOp - initial API and implementation
 ********************************************************************************/
package ru.arsysop.liho.check.comment;

import ru.arsysop.liho.check.ContentLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/***
 * <p>Stateful class that collects a {@code comment block} content line by line.</p>
 * <p>State sensitive methods are </p>
 * <ul>
 *     <li>{@linkplain #body()}</li>
 *     <li>{@linkplain #complete()}</li>
 * </ul>
 *
 * @since 0.1
 */
public abstract class CommentSearchEngine {
	private final AtomicBoolean found = new AtomicBoolean(false);
	private final AtomicBoolean completed = new AtomicBoolean(false);
	private final List<ContentLine> content = new ArrayList<>();

	/**
	 * <p>Update engine state with the next file line.</p>
	 * <p>Blank line does not affect the state.</p>
	 *
	 * @param source comment content piece is to be extracted from the source and appended to the engine's state
	 * @throws IllegalStateException on {@code update} attempt for {@code completed} engine
	 * @throws NullPointerException  if input source is {@code null}
	 * @since 0.1
	 */
	public final void update(String source, int position) {
		Objects.requireNonNull(source);
		String line = source.trim();
		if (line.isEmpty()) {
			return;
		}
		if (completed.get()) {
			throw new IllegalStateException("Cannot update state after it's completed");
		}
		updateFound(line); // TODO: redesign to avoid temporal coupling here. vertical delegation?
		updateContent(line, position);
		updateCompleted(line);
	}

	/**
	 * @return indicator of if the engine has already recognised the end of the comment block under processing or if it
	 * is still gathering comment content pieces (is updatable).
	 * @since 0.1
	 */
	public final boolean complete() {
		return completed.get();
	}

	/**
	 * @return all comment content pieces collected to the moment
	 * @since 0.1
	 */
	public final List<ContentLine> body() {
		return trimLast(
				content.stream()
						.map(cl -> new ContentLine(strip(cl.content().trim()), cl.position()))
						.filter(ContentLine::notEmpty)
						.collect(Collectors.toList()));
	}

	private List<ContentLine> trimLast(List<ContentLine> body) {
		if (found.get() && complete() && !includeLast()) {
			body.remove(body.size() - 1);
		}
		return body;
	}

	private void updateCompleted(String line) {
		if (end(line)) {
			completed.set(true);
		} else if (!found.get()) { // foreign line
			completed.set(true);
		}
	}

	private void updateFound(String line) {
		if (!found.get() && start(line)) {
			found.set(true);
		}
	}

	private void updateContent(String line, int position) {
		if (found.get() && !completed.get()) {
			content.add(new ContentLine(line, position));
		}
	}

	/**
	 * @param line input line that possibly can update {@code content} state
	 * @return {@code true} if the {@code line} starts a comment block, {@code false} otherwise
	 * @since 0.1
	 */
	protected abstract boolean start(String line);

	/**
	 * @param line input line that possibly can update {@code content} state
	 * @return {@code true} if the {@code line} ends a comment block, {@code false} otherwise
	 * @since 0.1
	 */
	protected abstract boolean end(String line);

	/***
	 * In vast majority of comment cases we cannot detect if the comment ends until something foreign is found.
	 * In these cases this last (foreign) line ends the comment block, but itself must not be included
	 * in the comment content.
	 * @return {@code true} if {@code end line} belongs to the comment content and {@code false} otherwise
	 * @since 0.1
	 */
	protected abstract boolean includeLast();

	/**
	 * Free a content line of comment-symbols, if any
	 *
	 * @param line content line
	 * @since 0.1
	 */
	protected abstract String strip(String line);

}
