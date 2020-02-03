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
package ru.arsysop.liho.check.java;

import ru.arsysop.liho.check.CommentSearchEngine;

final class LineComment extends CommentSearchEngine {

	@Override
	protected boolean start(String line) {
		return line.startsWith("//");
	}

	@Override
	protected boolean end(String line) {
		return !start(line);
	}

	@Override
	protected boolean includeLast() {
		return false;
	}

	@Override
	protected String strip(String line) {
		return line.substring(2).trim();
	}

}
