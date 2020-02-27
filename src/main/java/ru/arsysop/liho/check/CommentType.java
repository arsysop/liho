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

public interface CommentType {
	/**
	 * @param file description of a file to check. Cannot be {@code null}
	 * @return a sign if the {@linkplain Comment} can extract header from the file described or not
	 * @since 0.1
	 */
	boolean compatibleWith(File file);

	/**
	 * @param file source of comment, previously tested for compatibility
	 * @return lazily-built comment from the {@code file}
	 */
	Comment comment(File file);
}
