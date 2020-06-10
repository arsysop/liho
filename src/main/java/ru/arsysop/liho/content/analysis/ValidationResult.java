/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
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
