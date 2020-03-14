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
		new AnalyzedTree(() -> report).accept(new TestResource("ok-bulk-src").path());
		report.assertEquals();
	}

	@Test
	void defaultIntegrationNok() {
		HeapReport report = new HeapReport();
		new AnalyzedTree(() -> report).accept(new TestResource("nok-bulk-src").path());
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
