package ru.arsysop.liho.check.issues;

import ru.arsysop.liho.report.IssueType;

public final class NoCopyright extends IssueType {

	public NoCopyright() {
		super("no-copyright", "There is no `Copyright (c)` statement");
	}

}
