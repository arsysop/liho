/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package ru.arsysop.liho.content.comment;

import ru.arsysop.liho.content.Content;
import ru.arsysop.liho.content.ContentLine;
import ru.arsysop.liho.file.File;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public final class HeadingComment implements Content {

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
	public final List<ContentLine> get() throws IOException {
		return comment(file.lines());
	}

	@Override
	public final File owner() {
		return file;
	}

	private List<ContentLine> comment(Stream<String> lines) {
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

	private List<ContentLine> harvest() {
		return engines.stream()
				.map(CommentSearchEngine::body)
				.filter(c -> !c.isEmpty())
				.findFirst()
				.orElseGet(Collections::emptyList);
	}

}
