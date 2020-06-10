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
