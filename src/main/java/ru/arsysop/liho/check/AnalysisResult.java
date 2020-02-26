package ru.arsysop.liho.check;

import ru.arsysop.liho.report.IssueType;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

abstract class AnalysisResult {

	abstract boolean found();

	abstract Set<IssueType> issues();

	@Override
	public boolean equals(Object another) {
		if (!getClass().isInstance(another)) {
			return false;
		}
		AnalysisResult result = (AnalysisResult) another;
		return found() == result.found() && issues().equals(result.issues());
	}

	@Override
	public int hashCode() {
		return Objects.hash(found(), issues());
	}

	final static class NotFound extends AnalysisResult {

		@Override
		public boolean found() {
			return false;
		}

		@Override
		public Set<IssueType> issues() {
			return Collections.emptySet();
		}

	}

	final static class Ok extends AnalysisResult {

		@Override
		public boolean found() {
			return true;
		}

		@Override
		public Set<IssueType> issues() {
			return Collections.emptySet();
		}

	}

	final static class Issues extends AnalysisResult {

		private final Set<IssueType> issues;

		public Issues(Set<IssueType> issues) {
			this.issues = issues;
		}

		@Override
		public boolean found() {
			return true;
		}

		@Override
		public Set<IssueType> issues() {
			return issues;
		}

	}

}
