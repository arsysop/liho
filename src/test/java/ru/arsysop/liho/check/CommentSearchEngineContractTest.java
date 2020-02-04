package ru.arsysop.liho.check;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class CommentSearchEngineContractTest {
	@Test
	void updateOnBlankInputDoesNothing() {
		//given
		CommentSearchEngine engine = new DefaultEngine();
		assumeTrue(stateIsInitial(engine));

		// when
		engine.update("");

		//then
		assertTrue(stateIsInitial(engine));
	}

	@Test
	void prohibitsNullSource() {
		Assertions.assertThrows(NullPointerException.class, () -> new DefaultEngine().update(null));
	}

	@Test
	void prohibitsUpdateIfComplete() {
		// given
		CommentSearchEngine engine = new DefaultEngine();
		engine.update("any line is end line");
		assumeTrue(engine.complete());

		// when -> then
		assertThrows(IllegalStateException.class, () -> engine.update("any further string causes failure"));
	}

	private boolean stateIsInitial(CommentSearchEngine engine) {
		return engine.body().isEmpty() && !engine.complete();
	}

	private static class DefaultEngine extends CommentSearchEngine {

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
}
