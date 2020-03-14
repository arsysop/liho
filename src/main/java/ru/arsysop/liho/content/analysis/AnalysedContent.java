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

import ru.arsysop.liho.content.Content;
import ru.arsysop.liho.content.ContentLine;
import ru.arsysop.liho.content.comment.issues.FileNotAccessible;
import ru.arsysop.liho.content.comment.issues.NoLicenseHeader;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.IssueType;
import ru.arsysop.liho.report.Report;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AnalysedContent implements Consumer<Report> {

	private final Content content;
	private final List<ContentAnalysis> checks;

	public AnalysedContent(Content content, List<ContentAnalysis> checks) {
		this.content = content;
		this.checks = checks;
	}

	@Override
	public void accept(Report report) {
		List<ContentLine> source = source(report);
		if (source.isEmpty()) {
			return;
		}
		scan(source);
		report(report);
	}

	private void scan(List<ContentLine> source) {
		source.forEach(line -> checks.forEach(ch -> ch.update(line)));
	}

	private void report(Report report) {
		checks.forEach(ch -> ch.reportIssues(report));
	}

	private List<ContentLine> source(Report report) {
		List<ContentLine> source;
		try {
			source = content.get();
		} catch (IOException e) {
			report.issue(new FileNotAccessible(), new IssueLocation(content.owner().origin()));
			return Collections.emptyList();
		}
		if (source.isEmpty()) {
			report.issue(emptyContent(), new IssueLocation(content.owner().origin(), 1));
		}
		return source;
	}

	protected abstract  IssueType emptyContent();

}
