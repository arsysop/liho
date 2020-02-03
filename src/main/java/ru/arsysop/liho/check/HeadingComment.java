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
import java.util.stream.Stream;

public abstract class HeadingComment implements Comment {

	private final List<CommentSearchEngine> engines;

	protected HeadingComment(List<CommentSearchEngine> engines) {
		Objects.requireNonNull(engines);
		if (engines.isEmpty()) {
			throw new IllegalArgumentException("There is no way we'll find any header without comment search engine");
		}
		this.engines = engines;
	}

	@Override
	public List<String> get(File file) throws IOException {
		return comment(file.lines());
	}

	private List<String> comment(Stream<String> lines) {
		lines
				.filter(this::hasContent)
				.peek(this::updateEngines)
				.filter(any -> allOver()) // just stop pulling lines from a file to avoid the whole file reading
				.findFirst(); // the first foreign line for all engines
		return harvest();
	}

	private boolean hasContent(String line) {
		return !line.trim().isEmpty();
	}

	private void updateEngines(String line) {
		engines.stream()
				.filter(e -> !e.complete())
				.forEach(e -> e.update(line));
	}

	private boolean allOver() {
		return engines.stream().allMatch(CommentSearchEngine::complete);
	}

	private List<String> harvest() {
		//noinspection OptionalGetWithoutIsPresent - is not possible by design
		return engines.stream()
				.map(CommentSearchEngine::body)
				.filter(c -> !c.isEmpty())
				.findFirst().get();
	}

}
