package ru.arsysop.liho.check;

import ru.arsysop.liho.check.issues.NoCopyright;
import ru.arsysop.liho.report.Issue;
import ru.arsysop.liho.report.IssueType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class CopyrightHeaderChecks {
	private final CopyrightSegmentCheck copyright;
	private final List<Issue> issues = new ArrayList<>();

	public CopyrightHeaderChecks() {
		this.copyright = new CopyrightSegmentCheck();
	}

	void update(CommentLine line){
		AnalysisResult result = copyright.analyze(line.content());
		if(result.found()){

		}
	}

	public boolean satisfied() {
		return false;
	}


}
