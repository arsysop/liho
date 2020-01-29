package ru.arsysop.liho.report;

import java.nio.file.Path;

public final class IssueLocation {

	private final Path file;
	private final int line;

	public IssueLocation(Path file, int line) {
		this.file = file;
		this.line = line;
	}

	public Path file() {
		return file;
	}

	public int line() {
		return line;
	}

}
