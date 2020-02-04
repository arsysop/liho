package ru.arsysop.liho.check.issues;

import ru.arsysop.liho.report.IssueType;

public final class CopyrightLateUpdateYear extends IssueType {

	public CopyrightLateUpdateYear() {
		super("copyright-late-update-year", "Last update year cannot be greater than the inception year.");
	}

}
