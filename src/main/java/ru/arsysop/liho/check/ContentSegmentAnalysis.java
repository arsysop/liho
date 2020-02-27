/********************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   ArSysOp - initial API and implementation
 ********************************************************************************/
package ru.arsysop.liho.check;

import ru.arsysop.liho.file.File;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Owning a particular {@code segment validator}, participates in {@code content} analysis by validating each {@code
 * content line} against the given {@code segment validator} and collecting the results.
 */
public abstract class ContentSegmentAnalysis implements ContentAnalysis {

	private final File file;
	private final SegmentValidation validation;
	private final Map<ContentLine, ValidationResult> results = new HashMap<>();

	protected ContentSegmentAnalysis(File file, SegmentValidation validation) {
		this.file = file;
		this.validation = validation;
	}

	public void update(ContentLine line) {
		results.put(line, validation.apply(line.content()));
	}

	public void reportIssues(Report report) {
		if (results.values().stream().noneMatch(ValidationResult::found)) {
			notFound(report);
			return;
		}
		Map<Integer, ValidationResult> findings = orderedDuplicates();
		int first = findings.keySet().iterator().next();
		addFirstDetailed(findings.remove(first), first, report);
		addDuplicates(findings, report);
	}

	private void addDuplicates(Map<Integer, ValidationResult> duplicates, Report report) {
		duplicates.keySet().forEach(line -> report.issue(duplicate(), location(line)));
	}

	private void addFirstDetailed(ValidationResult result, int position, Report report) {
		result.issues().forEach(type -> report.issue(type, location(position)));
	}

	private TreeMap<Integer, ValidationResult> orderedDuplicates() {
		return new TreeMap<>(
				results.keySet().stream()
						.filter(key -> results.get(key).found())
						.collect(Collectors.toMap(ContentLine::position, results::get)));
	}

	private void notFound(Report report) {
		report.issue(
				notFound(),
				location(results.keySet().stream()
						.mapToInt(ContentLine::position)
						.min()
						.getAsInt() // stream is never empty -> min always exist
				)
		);
	}

	private IssueLocation location(int line) {
		return new IssueLocation(file.origin(), line);
	}

}
