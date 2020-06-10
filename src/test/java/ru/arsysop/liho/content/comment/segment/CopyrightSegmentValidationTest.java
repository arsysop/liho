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
package ru.arsysop.liho.content.comment.segment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.arsysop.liho.content.analysis.ValidationResult;
import ru.arsysop.liho.content.comment.issues.*;
import ru.arsysop.liho.report.IssueType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CopyrightSegmentValidationTest {

	@Test
	void noCopyrightOnEmpty() {
		Assertions.assertEquals(new ValidationResult.NotFound(),
				new CopyrightSegmentValidation().apply(""));
	}

	@Test
	void noCopyrightOnForeignString() {
		assertEquals(new ValidationResult.NotFound(),
				new CopyrightSegmentValidation().apply("Do not trouble trouble until trouble troubles you"));
	}

	@Test
	void validCopyright() {
		assertEquals(new ValidationResult.Ok(),
				new CopyrightSegmentValidation().apply("Copyright (c) 2019, 2020 ArSysOp"));
	}

	@Test
	void prequelWhitespace() {
		assertEquals(only(new CopyrightWhiteSpacePrequel()),
				new CopyrightSegmentValidation().apply("\tCopyright (c) 2020 ArSysOp"));
	}

	@Test
	void prequel() {
		assertEquals(only(new CopyrightPrequel()),
				new CopyrightSegmentValidation().apply("And here is Copyright (c) 2020 ArSysOp"));
	}

	@Test
	void inceptionYearTooEarly() {
		assertEquals(only(new CopyrightSuspiciousInceptionYear()),
				new CopyrightSegmentValidation().apply("Copyright (c) 1492 ArSysOp"));
	}

	@Test
	void inceptionYearTooFar() {
		int nextYear = LocalDateTime.now().getYear() + 1;
		assertEquals(only(new CopyrightSuspiciousInceptionYear()),
				new CopyrightSegmentValidation().apply("Copyright (c) " + nextYear + " ArSysOp"));
	}

	@Test
	void updateYearTooEarly() {
		assertTrue(new CopyrightSegmentValidation().apply("Copyright (c) 2020, 1492 ArSysOp").issues()
				.contains(new CopyrightSuspiciousUpdateYear()));
	}

	@Test
	void updateYearTooFar() {
		int nextYear = LocalDateTime.now().getYear() + 1;
		assertEquals(only(new CopyrightSuspiciousUpdateYear()),
				new CopyrightSegmentValidation().apply("Copyright (c) 2020, " + nextYear + " ArSysOp"));
	}

	@Test
	void lateUpdateYear() {
		assertEquals(only(new CopyrightLateUpdateYear()),
				new CopyrightSegmentValidation().apply("Copyright (c) 2020, 2019 ArSysOp"));
	}

	@Test
	void noOwner() {
		assertEquals(only(new CopyrightNoOwner()),
				new CopyrightSegmentValidation().apply("Copyright (c) 2020 and others"));
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
				new CopyrightSegmentValidation().apply(
						"\tyep, it's a Copyright (c)  " + future + ",  \t" + (future - 1) + "\t and others")
						.issues());
	}

	private ValidationResult only(IssueType type) {
		return new ValidationResult.Issues(Collections.singleton(type));
	}

}
