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

import ru.arsysop.liho.file.File;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public final class HeadingComment implements Comment {
	private final File file;
	private final List<CommentSearchEngine> engines;

	public HeadingComment(File file, List<CommentSearchEngine> engines) {
		Objects.requireNonNull(file);
		Objects.requireNonNull(engines);
		this.file = file;
		if (engines.isEmpty()) {
			throw new IllegalArgumentException("There is no way we'll find any header without comment search engine");
		}
		this.engines = engines;
	}

	@Override
	public final List<CommentLine> content() throws IOException {
		return comment(file.lines());
	}

	@Override
	public final File owner() {
		return file;
	}

	private List<CommentLine> comment(Stream<String> lines) {
		AtomicInteger position = new AtomicInteger(0);
		//noinspection ResultOfMethodCallIgnored
		lines
				.peek(line -> position.incrementAndGet())
				.filter(this::hasContent)
				.peek(line -> updateEngines(line, position.get()))
				.filter(any -> allOver()) // just stop pulling lines from a file to avoid the whole file reading
				.findFirst(); // the first foreign line for all engines stops the file reading
		return harvest();
	}

	private boolean hasContent(String line) {
		return !line.trim().isEmpty();
	}

	private void updateEngines(String line, int position) {
		engines.stream()
				.filter(e -> !e.complete())
				.forEach(e -> e.update(line, position));
	}

	private boolean allOver() {
		return engines.stream().allMatch(CommentSearchEngine::complete);
	}

	private List<CommentLine> harvest() {
		//noinspection OptionalGetWithoutIsPresent - is not possible by design
		return engines.stream()
				.map(CommentSearchEngine::body)
				.filter(c -> !c.isEmpty())
				.findFirst().get();
	}

}
