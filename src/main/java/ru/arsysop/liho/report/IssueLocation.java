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
package ru.arsysop.liho.report;

import java.nio.file.Path;
import java.util.Objects;

public final class IssueLocation {

	private final Path file;
	private final int line;

	public IssueLocation(Path file, int line) {
		this.file = file;
		this.line = line;
	}

	/**
	 * General file issues (like {@code file is not accessible}) do not have line positioning.
	 *
	 * @param file path to a source file where the {@code issue} under description is located
	 */
	public IssueLocation(Path file) {
		this(file, -1);
	}

	public Path file() {
		return file;
	}

	public int line() {
		return line;
	}

	@Override
	public boolean equals(Object obj) {
		if (!getClass().isInstance(obj)) {
			return false;
		}
		IssueLocation location = (IssueLocation) obj;
		return file.toAbsolutePath().toString().equalsIgnoreCase(location.file.toAbsolutePath().toString())
				&& (line == location.line);
	}

	@Override
	public int hashCode() {
		return Objects.hash(file.toAbsolutePath(), line);
	}

	@Override
	public String toString() {
		return file.toAbsolutePath() + ", line " + line;
	}

}
