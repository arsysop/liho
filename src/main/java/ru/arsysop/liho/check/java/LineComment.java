package ru.arsysop.liho.check.java;

import ru.arsysop.liho.check.CommentSearchEngine;

final class LineComment extends CommentSearchEngine {

	@Override
	protected boolean start(String line) {
		return line.startsWith("//");
	}

	@Override
	protected boolean end(String line) {
		return !start(line);
	}

	@Override
	protected boolean includeLast() {
		return false;
	}

	@Override
	protected String strip(String line) {
		return line.substring(2).trim();
	}

}
