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
package ru.arsysop.liho.content.comment.issues;

import ru.arsysop.liho.report.IssueType;

public final class DuplicatedSpdx extends IssueType {

	public DuplicatedSpdx() {
		super("duplicated-spdx", "Duplicate of `SPDX-License-Identifier` statement. There should be the only one.");
	}

}