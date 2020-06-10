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
