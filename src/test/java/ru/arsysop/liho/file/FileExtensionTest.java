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
package ru.arsysop.liho.file;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.TestResource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileExtensionTest {
	@Test
	void dataValueClass() {
		assertEquals(new PredefinedExtension("java"),
				new TestResource("A.java").file().extension());
	}
}
