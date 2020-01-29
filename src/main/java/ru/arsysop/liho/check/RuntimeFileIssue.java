package ru.arsysop.liho.check;

import ru.arsysop.liho.report.IssueType;

import java.util.function.Supplier;

public class RuntimeFileIssue implements Supplier<IssueType> {

	@Override
	public IssueType get() {
		return  new IssueType("runtime-error", "Failed to process file.");
	}

}
