/********************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   ArSysOp - initial API and implementation
 ********************************************************************************/
package ru.arsysop.liho.check.java;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.check.CommentLine;
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
		List<CommentLine> expectedBody = Collections.singletonList(new CommentLine("separate line", 2));
		CommentSearchEngine engine = new LineComment();

		engine.update(source[0], 1);
		assertFalse(engine.complete());
		assertTrue(engine.body().isEmpty());

		engine.update(source[1], 2);
		assertFalse(engine.complete());
		assertEquals(expectedBody, engine.body());

		engine.update(source[2], 3);
		assertFalse(engine.complete());
		assertEquals(expectedBody, engine.body());

		engine.update(source[3], 4);
		assertTrue(engine.complete());
		assertEquals(expectedBody, engine.body());
	}

	@Test
	void singleLine() {
		CommentSearchEngine engine = new LineComment();
		engine.update("\t//  single line  \t  ", 1);
		assertFalse(engine.complete());
		assertEquals(Collections.singletonList(new CommentLine("single line", 1)), engine.body());
	}

	@Test
	void blockComment() {
		CommentSearchEngine engine = new LineComment();
		engine.update(" \t /*  unexpected format  */", 1);
		assertTrue(engine.complete());
		assertTrue(engine.body().isEmpty());
	}

}
