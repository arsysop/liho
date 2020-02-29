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
package ru.arsysop.liho.content.comment.segment;

import ru.arsysop.liho.content.analysis.ContentSegmentAnalysis;
import ru.arsysop.liho.content.comment.issues.DuplicatedSpdx;
import ru.arsysop.liho.content.comment.issues.NoSpdx;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.IssueType;

public final class SpdxAnalysis extends ContentSegmentAnalysis {

	public SpdxAnalysis(File file) {
		super(file, new SpdxSegmentValidation());
	}

	@Override
	public IssueType notFound() {
		return new NoSpdx();
	}

	@Override
	public IssueType duplicate() {
		return new DuplicatedSpdx();
	}
}
