package ru.arsysop.liho.check.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.arsysop.liho.check.CommentSearchEngine;
import ru.arsysop.liho.check.HeadingComment;
import ru.arsysop.liho.file.File;

import java.util.Collections;
import java.util.List;

class HeadingCommentContractTest {

	@Test
	void forbidsNullAsEngines() {
		Assertions.assertThrows(NullPointerException.class, () -> new Blind(null));
	}

	@Test
	void forbidsNoEngines() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Blind(Collections.emptyList()));
	}

	private static class Blind extends HeadingComment {

		Blind(List<CommentSearchEngine> engines) {
			super(engines);
		}

		@Override
		public boolean compatibleWith(File file) {
			throw new UnsupportedOperationException();
		}
	}

}