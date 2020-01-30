package ru.arsysop.liho.check;

import ru.arsysop.liho.report.IssueType;

import java.util.List;

public interface SegmentCheck {

	List<IssueType> analyze(String source);

}
