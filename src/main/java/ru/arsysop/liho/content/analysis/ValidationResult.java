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
package ru.arsysop.liho.content.analysis;

import ru.arsysop.liho.report.IssueType;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public abstract class ValidationResult {

	private final boolean found;
	private final Set<IssueType> issues;

	@SuppressWarnings("WeakerAccess")
	protected ValidationResult(boolean found, Set<IssueType> issues) {
		Objects.requireNonNull(issues);
		this.found = found;
		this.issues = issues;
	}

	final boolean found() {
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
		ValidationResult result = (ValidationResult) another;
		return found == result.found && issues.equals(result.issues);
	}

	@Override
	public int hashCode() {
		return Objects.hash(found(), issues());
	}

	public final static class NotFound extends ValidationResult {

		public NotFound() {
			super(false, Collections.emptySet());
		}

	}

	public final static class Ok extends ValidationResult {

		public Ok() {
			super(true, Collections.emptySet());
		}

	}

	public final static class Issues extends ValidationResult {

		public Issues(Set<IssueType> issues) {
			super(true, issues);
		}

	}

}
