package ru.arsysop.liho.check.issues;

import ru.arsysop.liho.report.IssueType;

public final class NoSpdx extends IssueType {

	public NoSpdx() {
		super("no-spdx", "There is no `SPDX-License-Identifier` statement");
	}

}
