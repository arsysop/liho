package ru.arsysop.liho.check.comment.issues;

import ru.arsysop.liho.report.IssueType;

public final class CopyrightWhiteSpacePrequel extends IssueType {

	public CopyrightWhiteSpacePrequel() {
		super("copyright-whitespace-prequel", "`Copyright (c)` statement should not have heading whitespace characters.");
	}

}
