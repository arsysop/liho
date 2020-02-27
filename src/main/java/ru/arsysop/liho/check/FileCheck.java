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

import ru.arsysop.liho.check.issues.FileNotAccessible;
import ru.arsysop.liho.check.issues.NoLicenseHeader;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class FileCheck {

	private final Comment comment;
	private final List<SegmentChecks> checks;

	public FileCheck(Comment comment, List<SegmentChecks> checks) {
		this.comment = comment;
		this.checks = checks;
	}

	public void analyze(Report report) {
		List<CommentLine> source = source(report);
		if (source.isEmpty()) {
			return;
		}
		scan(source);
		report(report);
	}

	private void scan(List<CommentLine> source) {
		source.forEach(line -> checks.forEach(ch -> ch.update(line)));
	}

	private void report(Report report) {
		checks.forEach(ch -> ch.reportIssues(report));
	}

	private List<CommentLine> source(Report report) {
		List<CommentLine> source;
		try {
			source = comment.content();
		} catch (IOException e) {
			report.issue(new FileNotAccessible(), new IssueLocation(comment.owner().origin()));
			return Collections.emptyList();
		}
		if (source.isEmpty()) {
			report.issue(new NoLicenseHeader(), new IssueLocation(comment.owner().origin(), 1));
		}
		return source;
	}

}
