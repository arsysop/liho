package ru.arsysop.liho.check.java;

import ru.arsysop.liho.check.FileCheck;
import ru.arsysop.liho.check.RuntimeFileIssue;
import ru.arsysop.liho.file.Extension;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.file.PredefinedExtension;
import ru.arsysop.liho.report.IssueLocation;
import ru.arsysop.liho.report.Report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JavaCodeFileCheck implements FileCheck {

	private final Extension java;

	public JavaCodeFileCheck() {
		this.java = new PredefinedExtension("java");
	}

	@Override
	public boolean applicable(File file) {
		return java.equals(file.extension());
	}

	@Override
	public void analyze(File file, Report report) {
		try {
			process(file.lines());
		} catch (IOException e) {
			report.issue(new RuntimeFileIssue().get(), new IssueLocation(file.origin(), -1));
		}

	}

	private void process(Stream<String> lines) {
		comment(lines);
	}

	private List<String> comment(Stream<String> lines) {
		List<String> comment = new ArrayList<>();
		lines
				.filter(this::comment)
				.peek(comment::add)
				.anyMatch(this::foreign); // stop stream processing to avoid the whole file reading
		return comment;
	}

	private boolean inane(String line) {
		String trim = line.trim();
		return trim.isEmpty() || trim.equals("*");
	}

	private boolean comment(String line) {
		return line.startsWith("/**") || line.startsWith(" *");
	}

	private boolean foreign(String line) {
		return !inane(line) && !comment(line);
	}

}
