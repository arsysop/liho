package ru.arsysop.liho.content.comment.issues;

import ru.arsysop.liho.report.IssueType;

public final class CopyrightPrequel extends IssueType {

	public CopyrightPrequel() {
		super("copyright-prequel", "`Copyright (c)` statement must not have heading characters.");
	}

}
