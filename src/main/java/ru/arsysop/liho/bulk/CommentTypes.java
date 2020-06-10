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
package ru.arsysop.liho.bulk;

import ru.arsysop.liho.content.comment.CommentType;
import ru.arsysop.liho.file.File;

import java.util.List;

final class CommentTypes {
	private final List<CommentType> types;

	CommentTypes(List<CommentType> types) {
		this.types = types;
	}

	boolean supports(File file) {
		return types.stream().anyMatch(type -> type.compatibleWith(file));
	}

	/**
	 * It is mandatory to ask if the given file is {@linkplain #supports(File)} prior.
	 */
	CommentType type(File file) {
		//noinspection OptionalGetWithoutIsPresent
		return types.stream().filter(type -> type.compatibleWith(file)).findFirst().get();
	}
}
