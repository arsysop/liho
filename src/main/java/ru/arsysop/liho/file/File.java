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
package ru.arsysop.liho.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface File {

	Path origin();

	Extension extension();

	String name();

	Path folder();

	/**
	 * As we need only top part of a file, there is no need to read it to the bottom. Here we initiate a stream of
	 * lines, encouraging a client to analyse it in the fly and close it asap.
	 *
	 * @return lazy stream of file lines
	 * @throws IOException as any operation on a file system, can decline further processing whenever it wants due to
	 *                     interface OS reasons
	 * @see java.nio.file.Files#lines(Path)
	 */
	Stream<String> lines() throws IOException;
}
