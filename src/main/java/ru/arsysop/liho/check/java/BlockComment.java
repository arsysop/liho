package ru.arsysop.liho.check.java;

import ru.arsysop.liho.check.CommentSearchEngine;

final class BlockComment extends CommentSearchEngine {

	@Override
	protected boolean start(String line) {
		return line.trim().startsWith("/*");
	}

	@Override
	protected boolean end(String line) {
		return line.trim().endsWith("*/");
	}

	@Override
	protected boolean includeLast() {
		return true;
	}

	@Override
	protected String strip(String line) {
		String content = line;
		if (content.startsWith("/*")) {
			content = content.substring(2).trim();
		}
		if (content.endsWith("*/")) {
			content = content.substring(0, content.length() - 2).trim();
		}
		if (content.startsWith("*")) {
			content = content.substring(1).trim();
		}
		return content;
	}

}
