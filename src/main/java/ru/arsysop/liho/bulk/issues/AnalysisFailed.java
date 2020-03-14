package ru.arsysop.liho.bulk.issues;

import ru.arsysop.liho.report.IssueType;

public final class AnalysisFailed extends IssueType {

	public AnalysisFailed() {
		super("tree-analysis-failure ", "Liho failed to process source root");
	}

}
