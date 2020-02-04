package ru.arsysop.liho.check;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.arsysop.liho.file.File;

import java.util.Collections;
import java.util.List;

class HeadingCommentContractTest {

	@Test
	void requiresNotNullEngines() {
		Assertions.assertThrows(NullPointerException.class, () -> new Blind(null));
	}

	@Test
	void requiresNotEmptyEngines() {
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
