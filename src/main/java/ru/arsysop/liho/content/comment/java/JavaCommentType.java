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

import ru.arsysop.liho.content.comment.CommentType;
import ru.arsysop.liho.content.comment.HeadingComment;
import ru.arsysop.liho.file.Extension;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.file.PredefinedExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class JavaCommentType implements CommentType {

	private final Set<Extension> extensions = new HashSet<>(Arrays.asList(
			new PredefinedExtension("java"),
			new PredefinedExtension("groovy"),
			new PredefinedExtension("kt"),
			new PredefinedExtension("kts")));

	@Override
	public boolean compatibleWith(File file) {
		return extensions.contains(file.extension());
	}

	@Override
	public HeadingComment comment(File file) {
		return new HeadingComment(file, Arrays.asList(
				new BlockComment(),
				new LineComment()));
	}

}
