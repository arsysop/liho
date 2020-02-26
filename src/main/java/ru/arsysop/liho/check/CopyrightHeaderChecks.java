package ru.arsysop.liho.check;

import ru.arsysop.liho.check.issues.NoCopyright;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.IssueType;

final class CopyrightHeaderChecks extends BaseHeaderChecks {

	CopyrightHeaderChecks(File file) {
		super(file, new CopyrightSegmentCheck());
	}

	@Override
	public IssueType no() {
		return new NoCopyright();
	}

	@Override
	public IssueType duplicate() {
		return null;
	}
}
