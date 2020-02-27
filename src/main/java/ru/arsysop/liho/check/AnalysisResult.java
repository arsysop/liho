/********************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   ArSysOp - initial API and implementation
 ********************************************************************************/
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
