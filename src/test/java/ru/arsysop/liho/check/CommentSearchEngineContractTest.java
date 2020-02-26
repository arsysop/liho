package ru.arsysop.liho.check;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class CommentSearchEngineContractTest {

	@Test
	void updateOnBlankInputDoesNothing() {
		//given
		CommentSearchEngine engine = new FakeCommentSearchEngine();
		assumeTrue(stateIsInitial(engine));

		// when
		engine.update("", 1);

		//then
		assertTrue(stateIsInitial(engine));
	}

	@Test
	void prohibitsNullSource() {
		Assertions.assertThrows(NullPointerException.class, () -> new FakeCommentSearchEngine().update(null, 1));
	}

	@Test
	void prohibitsUpdateIfComplete() {
		// given
		CommentSearchEngine engine = new FakeCommentSearchEngine();
		engine.update("any line is end line", 1);
		assumeTrue(engine.complete());

		// when -> then
		assertThrows(IllegalStateException.class, () -> engine.update("any further update causes failure", 2));
	}

	private boolean stateIsInitial(CommentSearchEngine engine) {
		return engine.body().isEmpty() && !engine.complete();
	}

}
