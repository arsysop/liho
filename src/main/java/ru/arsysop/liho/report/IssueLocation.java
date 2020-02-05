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
package ru.arsysop.liho.report;

import java.nio.file.Path;

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
	 * @param file
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

}
