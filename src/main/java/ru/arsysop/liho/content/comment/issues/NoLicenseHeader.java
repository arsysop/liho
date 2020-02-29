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
package ru.arsysop.liho.content.comment.issues;

import ru.arsysop.liho.report.IssueType;

public final class NoLicenseHeader extends IssueType {

	public NoLicenseHeader() {
		super(
				"no-license-header",
				"There is no License header. " +
						"It should be a heading comment block declaring copyright and licenses. " +
						"For more details see https://www.eclipse.org/projects/handbook/#ip-copyright-headers");
	}

}
