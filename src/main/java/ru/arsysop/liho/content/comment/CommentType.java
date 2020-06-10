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
import ru.arsysop.liho.file.File;

public interface CommentType {

	/**
	 * @param file description of a file to check. Cannot be {@code null}
	 * @return a sign if the {@linkplain Content} can extract header from the file described or not
	 * @since 0.1
	 */
	boolean compatibleWith(File file);

	/**
	 * @param file source of comment, previously tested for compatibility
	 * @return lazily-built comment block from the {@code file}
	 * @since 0.1
	 */
	HeadingComment comment(File file);
}
