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
package ru.arsysop.liho.content.comment;

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
