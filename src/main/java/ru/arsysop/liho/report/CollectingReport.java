package ru.arsysop.liho.report;

import java.util.ArrayList;
import java.util.List;

public final class CollectingReport implements Report {

	private final List<Issue> issues = new ArrayList<>();

	@Override
	public void issue(IssueType type, IssueLocation location) {
		issues.add(new Issue(type, location));
	}

}
