package ru.arsysop.liho.check;

import ru.arsysop.liho.report.IssueType;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

abstract class AnalysisResult {
	private final boolean found;
	private final Set<IssueType> issues;

	protected AnalysisResult(boolean found, Set<IssueType> issues) {
		Objects.requireNonNull(issues);
		this.found = found;
		this.issues = issues;
	}

	public final boolean found() {
		return found;
	}

	public final Set<IssueType> issues() {
		return issues;
	}

	@Override
	public boolean equals(Object another) {
		if (!getClass().isInstance(another)) {
			return false;
		}
		AnalysisResult result = (AnalysisResult) another;
		return found == result.found && issues.equals(result.issues);
	}

	@Override
	public int hashCode() {
		return Objects.hash(found(), issues());
	}

	final static class NotFound extends AnalysisResult {

		NotFound() {
			super(false, Collections.emptySet());
		}

	}

	final static class Ok extends AnalysisResult {

		Ok() {
			super(true, Collections.emptySet());
		}

	}

	final static class Issues extends AnalysisResult {

		Issues(Set<IssueType> issues) {
			super(true, issues);
		}

	}

}
