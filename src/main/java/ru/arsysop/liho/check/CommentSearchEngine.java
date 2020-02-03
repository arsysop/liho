package ru.arsysop.liho.check;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public abstract class CommentSearchEngine {
	private final AtomicBoolean found = new AtomicBoolean(false);
	private final AtomicBoolean completed = new AtomicBoolean(false);
	private final List<String> content = new ArrayList<>();

	/**
	 * @param source not {@code null}
	 */
	public void update(String source) {
		String line = source.trim();
		if (line.isEmpty()) {
			return;
		}
		if (completed.get()) {
			throw new IllegalStateException("Cannot update state after it's completed");
		}
		updateFound(line); // redesign to avoid temporal coupling here
		updateContent(line);
		updateCompleted(line);
	}

	public boolean complete() {
		return completed.get();
	}

	public List<String> body() {
		return trimLast(
				content.stream()
						.map(String::trim)
						.map(this::strip)
						.filter(line -> !line.isEmpty())
						.collect(Collectors.toList()));
	}

	private List<String> trimLast(List<String> body) {
		if (found.get() && complete() && !includeLast()) {
			body.remove(body.size() - 1);
		}
		return body;
	}

	private void updateCompleted(String line) {
		if (end(line)) {
			completed.set(true);
		} else if (!found.get()) { // foreign line
			completed.set(true);
		}
	}

	private void updateFound(String line) {
		if (!found.get() && start(line)) {
			found.set(true);
		}
	}

	private void updateContent(String line) {
		if (found.get() && !completed.get()) {
			content.add(line);
		}
	}

	protected abstract boolean start(String line);

	protected abstract boolean end(String line);

	protected abstract boolean includeLast();

	protected abstract String strip(String line);

}
