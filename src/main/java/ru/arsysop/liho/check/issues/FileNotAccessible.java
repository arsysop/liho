package ru.arsysop.liho.check.issues;

import ru.arsysop.liho.report.IssueType;

public final class FileNotAccessible extends IssueType {

	public FileNotAccessible() {
		super("runtime-error", "Failed to process file.");
	}

}
