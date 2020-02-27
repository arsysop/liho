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
