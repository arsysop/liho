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

import java.util.Objects;

public final class ContentLine {

	private final String content;
	private final int position;

	public ContentLine(String content, int position) {
		this.content = content;
		this.position = position;
	}

	public String content() {
		return content;
	}

	public int position() {
		return position;
	}

	public boolean notEmpty() {
		return content.length() > 0;
	}

	@Override
	public boolean equals(Object another) {
		if (!getClass().isInstance(another)) {
			return false;
		}
		ContentLine line = (ContentLine) another;
		return content.equals(line.content) && (position == line.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, position);
	}

}
