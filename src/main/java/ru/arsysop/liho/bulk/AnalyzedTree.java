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
package ru.arsysop.liho.bulk;

import ru.arsysop.liho.bulk.issues.AnalysisFailed;
import ru.arsysop.liho.content.analysis.ContentAnalysis;
import ru.arsysop.liho.content.comment.CommentType;
import ru.arsysop.liho.content.comment.java.JavaCommentType;
import ru.arsysop.liho.content.comment.segment.CopyrightAnalysis;
import ru.arsysop.liho.content.comment.segment.SpdxAnalysis;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class AnalyzedTree implements Consumer<Path> {

	private final CommentTypes types;
	private final ContentAnalysisFactory analyzers;
	private final Supplier<Report> report;

	@SuppressWarnings("WeakerAccess")
	public AnalyzedTree(List<CommentType> types, List<Function<File, ContentAnalysis>> analyzers, Supplier<Report> report) {
		this.types = new CommentTypes(types);
		this.analyzers = new ContentAnalysisFactory(analyzers);
		this.report = report;
	}

	public AnalyzedTree(Supplier<Report> report) {
		this(Arrays.asList(
				new JavaCommentType()),
				Arrays.asList(
						CopyrightAnalysis::new,
						SpdxAnalysis::new),
				report);
	}

	public AnalyzedTree(Report report) {
		this(() -> report);
	}

	@Override
	public void accept(Path root) {
		Report report = this.report.get();
		try {
			Files.walkFileTree(root, new Visitor(types, analyzers, report));
		} catch (IOException e) {
			report.issue(new AnalysisFailed(), new IssueLocation(root));
		}
	}

}
