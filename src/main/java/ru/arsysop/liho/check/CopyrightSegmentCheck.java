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
import ru.arsysop.liho.check.issues.*;
import ru.arsysop.liho.report.IssueType;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class CopyrightSegmentCheck implements SegmentCheck {

	private final Cashed<String, Pattern> pattern;

	CopyrightSegmentCheck() {
		pattern = new Cashed<>("(.*)Copyright \\(c\\)\\s+(\\d+)(,\\s+(\\d+))?\\s+(.*)", Pattern::compile);
	}

	public Set<IssueType> analyze(String source) {
		Matcher matcher = pattern.get().matcher(source);
		if (!matcher.matches()) {
			return Collections.singleton(new NoCopyright());
		}
		Set<IssueType> issues = new HashSet<>();
		analyzePrequel(matcher.group(1), issues::add);
		analyzeInceptionYear(matcher.group(2), issues::add);
		analyzeUpdateYear(Optional.ofNullable(matcher.group(4)), // null can legally come from RegexAPI
				matcher.group(2), issues::add);
		analyzeOwner(matcher.group(5), issues::add);
		return issues;
	}

	private void analyzePrequel(String prequel, Consumer<IssueType> complain) {
		if (prequel.length() == 0) {
			return;
		}
		if (prequel.trim().length() == 0) {
			complain.accept(new CopyrightWhiteSpacePrequel());
		} else {
			complain.accept(new CopyrightPrequel());
		}
	}

	private void analyzeInceptionYear(String inception, Consumer<IssueType> complain) {
		if (yearLooksSuspicious(Integer.parseInt(inception))) {
			complain.accept(new CopyrightSuspiciousInceptionYear());
		}
	}

	private void analyzeUpdateYear(Optional<String> source, String inception, Consumer<IssueType> complain) {
		if (!source.isPresent()) {
			return;
		}
		int update = Integer.parseInt(source.get());
		if (yearLooksSuspicious(update)) {
			complain.accept(new CopyrightSuspiciousUpdateYear());
		}
		if (update < Integer.parseInt(inception)) {
			complain.accept(new CopyrightLateUpdateYear());
		}
	}

	private boolean yearLooksSuspicious(int year) {
		return (year < 1941) || (year > LocalDateTime.now().getYear());
	}

	private void analyzeOwner(String owner, Consumer<IssueType> complain) {
		if (owner.trim().equals("and others")) {
			complain.accept(new CopyrightNoOwner());
		}
	}

}
