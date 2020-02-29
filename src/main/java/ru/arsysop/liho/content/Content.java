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
package ru.arsysop.liho.content;

import ru.arsysop.liho.file.File;

import java.io.IOException;
import java.util.List;

/**
 * Comment build of a file of a particular type.
 *
 * @since 0.1
 */
public interface Content {

	File owner();

	/**
	 * @return line-by-line content body
	 * @throws IOException in case of any file system misbehaviour on a file operation
	 * @since 0.1
	 */
	List<ContentLine> get() throws IOException;
}
