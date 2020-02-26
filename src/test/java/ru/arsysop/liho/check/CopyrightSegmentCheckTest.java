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
package ru.arsysop.liho.check;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.check.issues.*;
import ru.arsysop.liho.report.IssueType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CopyrightSegmentCheckTest {

	@Test
	void noCopyrightOnEmpty() {
		assertEquals(new AnalysisResult.NotFound(),
				new CopyrightSegmentCheck().analyze(""));
	}

	@Test
	void noCopyrightOnForeignString() {
		assertEquals(new AnalysisResult.NotFound(),
				new CopyrightSegmentCheck().analyze("Do not trouble trouble until trouble troubles you"));
	}

	@Test
	void validCopyright() {
		assertEquals(new AnalysisResult.Ok(),
				new CopyrightSegmentCheck().analyze("Copyright (c) 2019, 2020 ArSysOp"));
	}

	@Test
	void prequelWhitespace() {
		assertEquals(only(new CopyrightWhiteSpacePrequel()),
				new CopyrightSegmentCheck().analyze("\tCopyright (c) 2020 ArSysOp"));
	}

	@Test
	void prequel() {
		assertEquals(only(new CopyrightPrequel()),
				new CopyrightSegmentCheck().analyze("And here is Copyright (c) 2020 ArSysOp"));
	}

	@Test
	void inceptionYearTooEarly() {
		assertEquals(only(new CopyrightSuspiciousInceptionYear()),
				new CopyrightSegmentCheck().analyze("Copyright (c) 1492 ArSysOp"));
	}

	@Test
	void inceptionYearTooFar() {
		int nextYear = LocalDateTime.now().getYear() + 1;
		assertEquals(only(new CopyrightSuspiciousInceptionYear()),
				new CopyrightSegmentCheck().analyze("Copyright (c) " + nextYear + " ArSysOp"));
	}

	@Test
	void updateYearTooEarly() {
		assertTrue(new CopyrightSegmentCheck().analyze("Copyright (c) 2020, 1492 ArSysOp").issues()
				.contains(new CopyrightSuspiciousUpdateYear()));
	}

	@Test
	void updateYearTooFar() {
		int nextYear = LocalDateTime.now().getYear() + 1;
		assertEquals(only(new CopyrightSuspiciousUpdateYear()),
				new CopyrightSegmentCheck().analyze("Copyright (c) 2020, " + nextYear + " ArSysOp"));
	}

	@Test
	void lateUpdateYear() {
		assertEquals(only(new CopyrightLateUpdateYear()),
				new CopyrightSegmentCheck().analyze("Copyright (c) 2020, 2019 ArSysOp"));
	}

	@Test
	void noOwner() {
		assertEquals(only(new CopyrightNoOwner()),
				new CopyrightSegmentCheck().analyze("Copyright (c) 2020 and others"));
	}

	@Test
	void multipleComplains() {
		int future = LocalDateTime.now().getYear() + 10;
		assertEquals(
				new HashSet<>(Arrays.asList(
						new CopyrightNoOwner(),
						new CopyrightPrequel(),
						new CopyrightSuspiciousInceptionYear(),
						new CopyrightSuspiciousUpdateYear(),
						new CopyrightLateUpdateYear())),
				new CopyrightSegmentCheck().analyze(
						"\tyep, it's a Copyright (c)  " + future + ",  \t" + (future - 1) + "\t and others")
						.issues());
	}

	private AnalysisResult only(IssueType type) {
		return new AnalysisResult.Issues(Collections.singleton(type));
	}

}
