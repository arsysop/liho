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
