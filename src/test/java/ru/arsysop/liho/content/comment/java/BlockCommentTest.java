/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package ru.arsysop.liho.content.comment.java;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.content.ContentLine;
import ru.arsysop.liho.content.comment.CommentSearchEngine;

import java.util.Collections;
import java.util.List;

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

		engine.update(source[0], 1);
		assertFalse(engine.complete());
		assertTrue(engine.body().isEmpty());

		engine.update(source[1], 2);
		assertFalse(engine.complete());

		List<ContentLine> found = Collections.singletonList(new ContentLine("separate line", 2));
		assertEquals(found, engine.body());

		engine.update(source[2], 3);
		assertTrue(engine.complete());
		assertEquals(found, engine.body());
	}

	@Test
	void singleLine() {
		CommentSearchEngine engine = new BlockComment();
		engine.update(" \t /*  single line  */  ", 1);
		assertTrue(engine.complete());
		assertEquals(Collections.singletonList(new ContentLine("single line", 1)), engine.body());
	}

	@Test
	void lineComment() {
		CommentSearchEngine engine = new BlockComment();
		engine.update(" \t //  unexpected format  ", 1);
		assertTrue(engine.complete());
		assertTrue(engine.body().isEmpty());
	}

}
