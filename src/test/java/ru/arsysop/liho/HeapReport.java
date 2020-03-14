package ru.arsysop.liho;

import org.junit.jupiter.api.Assertions;
import ru.arsysop.liho.report.Issue;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.IssueType;
import ru.arsysop.liho.report.Report;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class HeapReport implements Report {

	private final Set<Issue> issues = new HashSet<>();

	@Override
	public void issue(IssueType type, IssueLocation location) {
		issues.add(new Issue(type, location));
	}

	public void assertEquals(Issue... issues) {
		Assertions.assertEquals(new HashSet<>(Arrays.asList(issues)), this.issues);
	}

}
