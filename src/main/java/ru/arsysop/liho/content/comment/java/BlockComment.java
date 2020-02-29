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
package ru.arsysop.liho.content.comment.java;

import ru.arsysop.liho.content.comment.CommentSearchEngine;

final class BlockComment extends CommentSearchEngine {

	@Override
	protected boolean start(String line) {
		return line.trim().startsWith("/*");
	}

	@Override
	protected boolean end(String line) {
		return line.trim().endsWith("*/");
	}

	@Override
	protected boolean includeLast() {
		return true;
	}

	@Override
	protected String strip(String line) {
		String content = line;
		if (content.startsWith("/*")) {
			content = content.substring(2).trim();
		}
		if (content.endsWith("*/")) {
			content = content.substring(0, content.length() - 2).trim();
		}
		if (content.startsWith("*")) {
			content = content.substring(1).trim();
		}
		return content;
	}

}
