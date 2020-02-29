package ru.arsysop.liho.content.comment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.arsysop.liho.TestResource;

import java.util.Collections;

class HeadingCommentContractTest {

	@Test
	void requiresNotNullSource() {
		Assertions.assertThrows(NullPointerException.class, () ->
				new HeadingComment(null, Collections.singletonList(new FakeCommentSearchEngine())));
	}

	@Test
	void requiresNotNullEngines() {
		Assertions.assertThrows(NullPointerException.class, () ->
				new HeadingComment(new TestResource("A.java").file(), null));
	}

	@Test
	void requiresNotEmptyEngines() {
		Assertions.assertThrows(IllegalArgumentException.class, () ->
				new HeadingComment(new TestResource("A.java").file(), Collections.emptyList()));
	}

}
