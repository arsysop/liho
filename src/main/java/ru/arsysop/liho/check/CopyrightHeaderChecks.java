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

import ru.arsysop.liho.check.issues.DuplicateCopyright;
import ru.arsysop.liho.check.issues.NoCopyright;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.IssueType;

final class CopyrightHeaderChecks extends BaseHeaderChecks {

	CopyrightHeaderChecks(File file) {
		super(file, new CopyrightSegmentCheck());
	}

	@Override
	public IssueType no() {
		return new NoCopyright();
	}

	@Override
	public IssueType duplicate() {
		return new DuplicateCopyright();
	}
}
