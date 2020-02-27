package ru.arsysop.liho.check.comment.issues;

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
