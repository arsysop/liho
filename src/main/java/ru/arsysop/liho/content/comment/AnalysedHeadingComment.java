package ru.arsysop.liho.content.comment;

import ru.arsysop.liho.content.analysis.AnalysedContent;
import ru.arsysop.liho.content.analysis.ContentAnalysis;
import ru.arsysop.liho.content.comment.issues.NoLicenseHeader;
import ru.arsysop.liho.report.IssueType;

import java.util.List;

public class AnalysedHeadingComment extends AnalysedContent {

	public AnalysedHeadingComment(HeadingComment content, List<ContentAnalysis> checks) {
		super(content, checks);
	}

	@Override
	protected IssueType emptyContent() {
		return new NoLicenseHeader();
	}
}
