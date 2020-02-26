package ru.arsysop.liho.check;

import ru.arsysop.liho.report.Issue;
import ru.arsysop.liho.report.IssueType;
import ru.arsysop.liho.report.Report;

import java.util.List;

interface SegmentChecks {

	void update(CommentLine line);

	void reportIssues(Report report);

	IssueType no();

	IssueType duplicate();
}
