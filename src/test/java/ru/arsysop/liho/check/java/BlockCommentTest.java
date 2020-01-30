package ru.arsysop.liho.check.java;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.check.CommentSearchEngine;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BlockCommentTest {

	@Test
	void separateLine() {
		String[] source = new String[]{
				"\t/*",
				" * separate line  	",
				" */\t"
		};
		CommentSearchEngine engine = new BlockComment();

		engine.update(source[0]);
		assertFalse(engine.complete());
		assertTrue(engine.body().isEmpty());

		engine.update(source[1]);
		assertFalse(engine.complete());
		assertEquals(Collections.singletonList("separate line"), engine.body());

		engine.update(source[2]);
		assertTrue(engine.complete());
		assertEquals(Collections.singletonList("separate line"), engine.body());
	}

	@Test
	void singleLine() {
		CommentSearchEngine engine = new BlockComment();
		engine.update(" \t /*  single line  */  ");
		assertTrue(engine.complete());
		assertEquals(Collections.singletonList("single line"), engine.body());
	}

	@Test
	void lineComment() {
		CommentSearchEngine engine = new BlockComment();
		engine.update(" \t //  unexpected format  ");
		assertTrue(engine.complete());
		assertTrue(engine.body().isEmpty());
	}

}
