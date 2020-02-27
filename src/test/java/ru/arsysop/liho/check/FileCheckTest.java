package ru.arsysop.liho.check;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.HeapReport;
import ru.arsysop.liho.TestResource;
import ru.arsysop.liho.check.issues.DuplicateCopyright;
import ru.arsysop.liho.check.issues.NoCopyright;
import ru.arsysop.liho.check.issues.NoLicenseHeader;
import ru.arsysop.liho.check.java.JavaCommentType;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.Issue;
import ru.arsysop.liho.report.IssueLocation;

import java.util.Collections;

final class FileCheckTest {

	@Test
	void ok() {
		check("A.java").assertEquals();
	}

	@Test
	void noLicenseHeader() {
		File file = new TestResource("src/org/eclipse/errors/NoLicenseHeader.java").file();
		check(file).assertEquals(
				new Issue(new NoLicenseHeader(), new IssueLocation(file.origin(), 1))
		);
	}

	@Test
	void noCopyright() {
		File file = new TestResource("src/org/eclipse/errors/NoCopyright.java").file();
		check(file).assertEquals(
				new Issue(new NoCopyright(), new IssueLocation(file.origin(), 1))
		);
	}@Test
	void duplicatedCopyright() {
		File file = new TestResource("src/org/eclipse/errors/DuplicatedCopyright.java").file();
		check(file).assertEquals(
				new Issue(new DuplicateCopyright(), new IssueLocation(file.origin(), 8))
		);
	}

	private HeapReport check(String name) {
		return check(new TestResource(name).file());
	}

	private HeapReport check(File file) {
		HeapReport report = new HeapReport();
		new FileCheck(
				new JavaCommentType().comment(file),
				Collections.singletonList(new CopyrightHeaderChecks(file))
		).analyze(report);
		return report;
	}
}
