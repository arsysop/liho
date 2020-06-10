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
package ru.arsysop.liho.content.comment.segment;

import ru.arsysop.lang.function.CachingFunction;
import ru.arsysop.liho.content.analysis.SegmentValidation;
import ru.arsysop.liho.content.analysis.ValidationResult;
import ru.arsysop.liho.content.comment.issues.*;
import ru.arsysop.liho.report.IssueType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class CopyrightSegmentValidation implements SegmentValidation {

	private final CachingFunction<String, Pattern> pattern;

	CopyrightSegmentValidation() {
		pattern = new CachingFunction<>("(.*)Copyright \\(c\\)\\s+(\\d+)(,\\s+(\\d+))?\\s+(.*)", Pattern::compile);
	}

	@Override
	public ValidationResult apply(String source) {
		Matcher matcher = pattern.get().matcher(source);
		if (!matcher.matches()) {
			return new ValidationResult.NotFound();
		}
		Set<IssueType> issues = issues(matcher);
		if (issues.isEmpty()) {
			return new ValidationResult.Ok();
		}
		return new ValidationResult.Issues(issues);
	}

	private Set<IssueType> issues(Matcher matcher) {
		Set<IssueType> issues = new HashSet<>();
		analyzePrequel(matcher.group(1), issues::add);
		analyzeInceptionYear(matcher.group(2), issues::add);
		analyzeUpdateYear(Optional.ofNullable(matcher.group(4)), matcher.group(2), issues::add);
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
