package ru.arsysop.liho.check;

import ru.arsysop.liho.check.issues.FileNotAccessible;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class FileCheck {

	private final HeadingComment comment;
	private final List<SegmentCheck> checks;

	public FileCheck(HeadingComment comment, List<SegmentCheck> checks) {
		this.comment = comment;
		this.checks = checks;
	}

	public void analyze(Report report) throws IOException {
		comment.content().forEach(line -> checks.forEach(check -> check.analyze(line.content())));
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
