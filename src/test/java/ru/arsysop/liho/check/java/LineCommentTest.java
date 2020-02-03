package ru.arsysop.liho.check.java;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.check.CommentSearchEngine;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineCommentTest {

	@Test
	void separateLine() {
		String[] source = new String[]{
				"\t//",
				" // separate line  	",
				" //\t",
				"package p; "
		};
		List<String> expectedBody = Collections.singletonList("separate line");
		CommentSearchEngine engine = new LineComment();

		engine.update(source[0]);
		assertFalse(engine.complete());
		assertTrue(engine.body().isEmpty());

		engine.update(source[1]);
		assertFalse(engine.complete());
		assertEquals(expectedBody, engine.body());

		engine.update(source[2]);
		assertFalse(engine.complete());
		assertEquals(expectedBody, engine.body());

		engine.update(source[3]);
		assertTrue(engine.complete());
		assertEquals(expectedBody, engine.body());
	}

	@Test
	void singleLine() {
		CommentSearchEngine engine = new LineComment();
		engine.update("\t//  single line  \t  ");
		assertFalse(engine.complete());
		assertEquals(Collections.singletonList("single line"), engine.body());
	}

	@Test
	void blockComment() {
		CommentSearchEngine engine = new LineComment();
		engine.update(" \t /*  unexpected format  */");
		assertTrue(engine.complete());
		assertTrue(engine.body().isEmpty());
	}

}