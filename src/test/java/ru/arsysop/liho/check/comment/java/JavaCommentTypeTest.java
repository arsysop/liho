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
package ru.arsysop.liho.check.comment.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.arsysop.liho.TestResource;
import ru.arsysop.liho.check.ContentLine;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JavaCommentTypeTest {

	private final String fullFeatherComment = "*****************************************************************************\n" +
			"Copyright (c) 2018, 2020 ArSysOp\n" +
			"This program and the accompanying materials are made available under the\n" +
			"terms of the Eclipse Public License 2.0 which is available at\n" +
			"https://www.eclipse.org/legal/epl-2.0/.\n" +
			"SPDX-License-Identifier: EPL-2.0\n" +
			"Contributors:\n" +
			"ArSysOp - initial API and implementation\n" +
			"*****************************************************************************";

	@Test
	@DisplayName("applicable to a java source file")
	void applicable() {
		assertTrue(new JavaCommentType().compatibleWith(
				new TestResource("A.java").file()));
	}

	@Test
	@DisplayName("full feather block comment")
	void blockComment() throws IOException {
		assertEquals(fullFeatherComment, commentFromFile("A.java"));
	}

	@Test
	@DisplayName("full feather line comment")
	void lineComment() throws IOException {
		assertEquals(fullFeatherComment, commentFromFile("ALine.java"));
	}

	private String commentFromFile(String file) throws IOException {
		return new JavaCommentType().comment(
				new TestResource(file).file()).get().stream()
				.map(ContentLine::content)
				.collect(Collectors.joining("\n"));
	}
}
