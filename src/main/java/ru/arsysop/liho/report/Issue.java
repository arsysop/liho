package ru.arsysop.liho.report;

public final class Issue {

	private final IssueType type;
	private final IssueLocation location;

	public Issue(IssueType type, IssueLocation location) {
		this.type = type;
		this.location = location;
	}

	public IssueType type() {
		return type;
	}

	public IssueLocation location() {
		return location;
	}

}
