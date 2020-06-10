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
import ru.arsysop.liho.content.comment.AnalysedHeadingComment;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.file.FileFromPath;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

final class Visitor implements FileVisitor<Path> {

	private final CommentTypes types;
	private final ContentAnalysisFactory analyzers;
	private final Report report;

	Visitor(CommentTypes types, ContentAnalysisFactory analyzers, Report report) {
		this.types = types;
		this.analyzers = analyzers;
		this.report = report;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
		File file = new FileFromPath(path);
		if (!types.supports(file)) {

			return FileVisitResult.CONTINUE;
		}
		new AnalysedHeadingComment(types.type(file).comment(file), analyzers.apply(file)).accept(report);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		report.issue(new AnalysisFailed(), new IssueLocation(file));
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
		return FileVisitResult.CONTINUE;
	}

}
