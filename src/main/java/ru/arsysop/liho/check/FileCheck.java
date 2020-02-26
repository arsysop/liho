package ru.arsysop.liho.check;

import ru.arsysop.liho.check.issues.FileNotAccessible;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class FileCheck {

	private final HeadingComment comment;
	private final List<SegmentChecks> checks;

	public FileCheck(HeadingComment comment, List<SegmentChecks> checks) {
		this.comment = comment;
		this.checks = checks;
	}

	public void analyze(Report report) throws IOException {
		update();
		report(report);
	}

	private void update() throws IOException {
		comment.content().forEach(line -> checks.forEach(ch -> ch.update(line)));
	}

	private void report(Report report) {
		checks.forEach(ch -> ch.reportIssues(report));
	}

	private List<CommentLine> source(Report report) {
		try {
			return comment.content();
		} catch (IOException e) {
			report.issue(new FileNotAccessible(), new IssueLocation(comment.owner().origin()));
			return Collections.emptyList();
		}
	}

}
