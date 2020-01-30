package ru.arsysop.liho.report;

import java.util.Objects;

public final class IssueType {

	private final String id;
	private final String description;

	public IssueType(String id, String description) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(description);
		this.id = id;
		this.description = description;
	}

	public String id() {
		return id;
	}

	public String description() {
		return description;
	}

}
