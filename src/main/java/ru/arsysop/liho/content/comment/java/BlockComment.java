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
