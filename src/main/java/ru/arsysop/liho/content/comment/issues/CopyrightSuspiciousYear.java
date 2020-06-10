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
package ru.arsysop.liho.content.comment.issues;

import ru.arsysop.liho.report.IssueType;

abstract class CopyrightSuspiciousYear extends IssueType {
	private final String dedication;

	public CopyrightSuspiciousYear(String dedication) {
		super("copyright-suspicious-" + dedication + "-year",
				"Inception year looks suspicious. " +
						"Must be a four-digit year not greater that the current year " +
						"and not less than the year of a computer invention.");
		this.dedication = dedication;
	}

}
