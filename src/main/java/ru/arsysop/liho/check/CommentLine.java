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

import java.util.Objects;

public final class CommentLine {
	private final String content;
	private final int position;

	public CommentLine(String content, int position) {
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
		CommentLine line = (CommentLine) another;
		return content.equals(line.content) && (position == line.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, position);
	}

}
