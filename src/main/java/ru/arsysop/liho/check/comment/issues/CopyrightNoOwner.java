package ru.arsysop.liho.check.comment.issues;

import ru.arsysop.liho.report.IssueType;

public final class CopyrightNoOwner extends IssueType {

	public CopyrightNoOwner() {
		super("copyright-no-owner", "Copyright statement must end with `owner` mention.");
	}

}
