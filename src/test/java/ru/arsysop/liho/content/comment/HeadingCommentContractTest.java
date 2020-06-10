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
