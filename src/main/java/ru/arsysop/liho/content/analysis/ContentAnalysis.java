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
package ru.arsysop.liho.content.analysis;

import ru.arsysop.liho.content.ContentLine;
import ru.arsysop.liho.report.IssueType;
import ru.arsysop.liho.report.Report;

public interface ContentAnalysis {

	void update(ContentLine line);

	void reportIssues(Report report);

	IssueType notFound();

	IssueType duplicate();
}