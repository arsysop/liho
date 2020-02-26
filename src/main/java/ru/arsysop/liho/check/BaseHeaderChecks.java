package ru.arsysop.liho.check;

import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

abstract class BaseHeaderChecks implements SegmentChecks {

	private final File file;
	private final SegmentCheck segment;
	private final Map<CommentLine, AnalysisResult> results = new HashMap<>();

	BaseHeaderChecks(File file, SegmentCheck segment) {
		this.file = file;
		this.segment = segment;
	}

	public void update(CommentLine line) {
		results.put(line, segment.analyze(line.content()));
	}

	public void reportIssues(Report report) {
		if (results.values().stream().noneMatch(AnalysisResult::found)) {
			notFound(report);
			return;
		}
		Map<Integer, AnalysisResult> findings = orderedDuplicates();
		int first = findings.keySet().iterator().next();
		addWhole(findings.remove(first), first, report);
		addDuplicates(findings, report);
	}

	private void addDuplicates(Map<Integer, AnalysisResult> duplicates, Report report) {
		duplicates.keySet().forEach(line -> report.issue(duplicate(), location(line)));
	}

	private void addWhole(AnalysisResult result, int position, Report report) {
		result.issues().forEach(type -> report.issue(type, location(position)));
	}

	private TreeMap<Integer, AnalysisResult> orderedDuplicates() {
		return new TreeMap<>(
				results.keySet().stream()
						.filter(key -> results.get(key).found())
						.collect(Collectors.toMap(CommentLine::position, results::get)));
	}

	private void notFound(Report report) {
		report.issue(
				no(),
				location(results.keySet().stream()
						.mapToInt(CommentLine::position)
						.min()
						.getAsInt()
				)
		);
	}

	private IssueLocation location(int line) {
		return new IssueLocation(file.origin(), line);
	}

}
