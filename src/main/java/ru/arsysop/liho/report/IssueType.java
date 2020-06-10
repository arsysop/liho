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
package ru.arsysop.liho.report;

import java.util.Objects;

/**
 * Data-value class covers {@code validation issue} meta information.
 *
 * @since 0.1
 */
public abstract class IssueType {

	private final String id;
	private final String description;

	protected IssueType(String id, String description) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(description);
		this.id = id;
		this.description = description;
	}

	/**
	 * @return unique, white-space character free, but yet readable identifier of the issue
	 * @since 0.1
	 */
	public String id() {
		return id;
	}

	/**
	 * @return wide description. Can be multiline, can be empty. Cannot be {@code null}. Intended to be exposed in UI,
	 * thus can contain internationalised value.
	 * @since 0.1
	 */
	public String description() {
		return description;
	}

	@Override
	public int hashCode() {
		return id.hashCode() + description.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!IssueType.class.isInstance(obj)) {
			return false;
		}
		IssueType another = (IssueType) obj;
		return id.equals(another.id) && description.equals(another.description);
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", id, description);
	}

}
