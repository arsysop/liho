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
package ru.arsysop.liho.content;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.HeapReport;
import ru.arsysop.liho.TestResource;
import ru.arsysop.liho.content.comment.AnalysedHeadingComment;
import ru.arsysop.liho.content.comment.issues.DuplicatedCopyright;
import ru.arsysop.liho.content.comment.issues.NoCopyright;
import ru.arsysop.liho.content.comment.issues.NoLicenseHeader;
import ru.arsysop.liho.content.comment.java.JavaCommentType;
import ru.arsysop.liho.content.comment.segment.CopyrightAnalysis;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.Issue;
import ru.arsysop.liho.report.IssueLocation;

import java.util.Collections;

final class AnalysedHeadingCommentTest {

	@Test
	void ok() {
		check(new TestResource("A.java").file()).assertEquals();
	}

	@Test
	void noLicenseHeader() {
		File file = new TestResource("src/ru/arsysop/errors/NoLicenseHeader.java").file();
		check(file).assertEquals(
				new Issue(new NoLicenseHeader(), new IssueLocation(file.origin(), 1))
		);
	}

	@Test
	void noCopyright() {
		File file = new TestResource("src/ru/arsysop/errors/NoCopyright.java").file();
		check(file).assertEquals(
				new Issue(new NoCopyright(), new IssueLocation(file.origin(), 1))
		);
	}

	@Test
	void duplicatedCopyright() {
		File file = new TestResource("src/ru/arsysop/errors/DuplicatedCopyright.java").file();
		check(file).assertEquals(
				new Issue(new DuplicatedCopyright(), new IssueLocation(file.origin(), 8))
		);
	}

	private HeapReport check(File file) {
		HeapReport report = new HeapReport();
		new AnalysedHeadingComment(
				new JavaCommentType().comment(file),
				Collections.singletonList(new CopyrightAnalysis(file))
		).accept(report);
		return report;
	}
	
}
