package ru.arsysop.liho.report;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Supplier;

public final class StreamingReport implements Report {

	private final Supplier<PrintStream> stream;
	private Function<Issue, String> row = this::row;

	public StreamingReport(PrintStream stream) {
		this(() -> stream);
	}

	public StreamingReport(Supplier<OutputStream> stream) {
		this.stream = () -> new PrintStream(stream.get());
	}

	public StreamingReport withRow(Function<Issue, String> row) {
		this.row = row;
		return this;
	}

	@Override
	public void issue(IssueType type, IssueLocation location) {
		stream.get().println(row.apply(new Issue(type, location)));
	}

	private String row(Issue issue) {
		return String.format("%s in %s, line %d", issue.type().id(), issue.location().file(), issue.location().line());
	}

}
