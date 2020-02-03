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

import ru.arsysop.liho.Cashed;
import ru.arsysop.liho.check.issues.NoCopyright;
import ru.arsysop.liho.report.IssueType;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class CopyrightSegmentCheck implements SegmentCheck {

	private final Cashed<String, Pattern> pattern;

	CopyrightSegmentCheck() {
		pattern = new Cashed<>("(.*)Copyright \\(c\\) (\\d{4}){1} ( \\,\\d{4})? (.*)", Pattern::compile);
	}

	public List<IssueType> analyze(String source) {
		Matcher matcher = pattern.get().matcher(source);
		if (!matcher.matches()) {
			return Collections.singletonList(new NoCopyright());
		}
		return Collections.emptyList();
	}

}
