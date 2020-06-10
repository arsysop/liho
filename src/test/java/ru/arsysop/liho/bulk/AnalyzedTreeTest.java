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

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.HeapReport;
import ru.arsysop.liho.TestResource;
import ru.arsysop.liho.content.comment.issues.CopyrightLateUpdateYear;
import ru.arsysop.liho.content.comment.issues.NoLicenseHeader;
import ru.arsysop.liho.content.comment.issues.NoSpdx;
import ru.arsysop.liho.report.Issue;
import ru.arsysop.liho.report.IssueLocation;

class AnalyzedTreeTest {

	@Test
	void defaultIntegrationOk() {
		HeapReport report = new HeapReport();
		new AnalyzedTree(report).accept(new TestResource("ok-bulk-src").path());
		report.assertEquals();
	}

	@Test
	void defaultIntegrationNok() {
		HeapReport report = new HeapReport();
		new AnalyzedTree(report).accept(new TestResource("nok-bulk-src").path());
		report.assertEquals(
				new Issue(
						new NoLicenseHeader(),
						new IssueLocation(
								new TestResource("nok-bulk-src/ru/arsysop/again/NoLicHeader.java").path(),
								1)),
				new Issue(
						new NoSpdx(),
						new IssueLocation(
								new TestResource("nok-bulk-src/ru/arsysop/again/andagain/InvalidLicHeader.java").path(),
								1)),
				new Issue(
						new CopyrightLateUpdateYear(),
						new IssueLocation(
								new TestResource("nok-bulk-src/ru/arsysop/again/andagain/InvalidLicHeader.java").path(),
								2))
		);
	}

}
