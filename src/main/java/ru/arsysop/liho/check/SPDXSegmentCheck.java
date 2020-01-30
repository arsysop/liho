package ru.arsysop.liho.check;

import ru.arsysop.liho.Cashed;
import ru.arsysop.liho.check.issues.NoSpdx;
import ru.arsysop.liho.report.IssueType;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//todo: obsolete EPL version checker
final class SPDXSegmentCheck implements SegmentCheck {

	private final Cashed<String, Pattern> pattern;

	SPDXSegmentCheck() {
		pattern = new Cashed<>("(.*)SPDX-License-Identifier: (.*)", Pattern::compile);
	}

	public List<IssueType> analyze(String source) {
		Matcher matcher = pattern.get().matcher(source);
		if (!matcher.matches()) {
			return Collections.singletonList(new NoSpdx());
		}
		return Collections.emptyList();
	}

}
