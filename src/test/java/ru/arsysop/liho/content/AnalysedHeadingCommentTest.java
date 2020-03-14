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
