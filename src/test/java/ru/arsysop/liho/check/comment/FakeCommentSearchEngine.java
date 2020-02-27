package ru.arsysop.liho.check.comment;

import ru.arsysop.liho.check.comment.CommentSearchEngine;

final class FakeCommentSearchEngine extends CommentSearchEngine {

	@Override
	protected boolean start(String line) {
		return true;
	}

	@Override
	protected boolean end(String line) {
		return true;
	}

	@Override
	protected boolean includeLast() {
		throw new UnsupportedOperationException("Is not expected to be invoked");
	}

	@Override
	protected String strip(String line) {
		throw new UnsupportedOperationException("Is not expected to be invoked");
	}

}
