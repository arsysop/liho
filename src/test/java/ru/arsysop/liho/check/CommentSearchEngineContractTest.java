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
		engine.update("");

		//then
		assertTrue(stateIsInitial(engine));
	}

	@Test
	void prohibitsNullSource() {
		Assertions.assertThrows(NullPointerException.class, () -> new FakeCommentSearchEngine().update(null));
	}

	@Test
	void prohibitsUpdateIfComplete() {
		// given
		CommentSearchEngine engine = new FakeCommentSearchEngine();
		engine.update("any line is end line");
		assumeTrue(engine.complete());

		// when -> then
		assertThrows(IllegalStateException.class, () -> engine.update("any further update causes failure"));
	}

	private boolean stateIsInitial(CommentSearchEngine engine) {
		return engine.body().isEmpty() && !engine.complete();
	}

}
