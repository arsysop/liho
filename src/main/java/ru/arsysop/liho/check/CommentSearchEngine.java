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
package ru.arsysop.liho.check;

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
	private final List<String> content = new ArrayList<>();

	/**
	 * <p>Update engine state with the next file line.</p>
	 * <p>Blank line does not affect the state.</p>
	 *
	 * @param source comment content piece is to be extracted from the source and appended to the engine's state
	 * @throws IllegalStateException on {@code update} attempt for {@code completed} engine
	 * @throws NullPointerException  if input source is {@code null}
	 * @since 0.1
	 */

	public void update(String source) {
		Objects.requireNonNull(source);
		String line = source.trim();
		if (line.isEmpty()) {
			return;
		}
		if (completed.get()) {
			throw new IllegalStateException("Cannot update state after it's completed");
		}
		updateFound(line); // TODO: redesign to avoid temporal coupling here. vertical delegation?
		updateContent(line);
		updateCompleted(line);
	}

	/**
	 * @return indicator of if the engine has already recognised the end of the comment block under processing or if it is
	 * still gathering comment content pieces (is updatable).
	 * @since
	 */
	public boolean complete() {
		return completed.get();
	}

	/**
	 * @return all comment content pieces collected to the moment
	 * @since 0.1
	 */
	public List<String> body() {
		return trimLast(
				content.stream()
						.map(String::trim)
						.map(this::strip)
						.filter(line -> !line.isEmpty())
						.collect(Collectors.toList()));
	}

	private List<String> trimLast(List<String> body) {
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

	private void updateContent(String line) {
		if (found.get() && !completed.get()) {
			content.add(line);
		}
	}

	protected abstract boolean start(String line);

	protected abstract boolean end(String line);

	protected abstract boolean includeLast();

	protected abstract String strip(String line);

}
