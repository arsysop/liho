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
package ru.arsysop.liho.check.issues;

import ru.arsysop.liho.report.IssueType;

public final class DuplicateCopyright extends IssueType {

	public DuplicateCopyright() {
		super("duplicated-copyright", "Duplicate of `Copyright (c)` statement. There should be the only one.");
	}

}
