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

import ru.arsysop.liho.content.Content;
import ru.arsysop.liho.content.comment.CommentType;
import ru.arsysop.liho.content.comment.HeadingComment;
import ru.arsysop.liho.file.Extension;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.file.PredefinedExtension;

import java.util.Arrays;

public final class JavaCommentType implements CommentType {
	private final Extension java = new PredefinedExtension("java");

	@Override
	public boolean compatibleWith(File file) {
		return java.equals(file.extension());
	}

	@Override
	public Content comment(File file) {
		return new HeadingComment(file, Arrays.asList(
				new BlockComment(),
				new LineComment()));
	}

}
