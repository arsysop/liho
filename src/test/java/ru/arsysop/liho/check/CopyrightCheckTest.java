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
import ru.arsysop.liho.check.issues.NoCopyright;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CopyrightCheckTest {

	@Test
	void noCopyright() {
		assertEquals(Collections.singletonList(new NoCopyright()), new CopyrightSegmentCheck().analyze(""));
	}
}
