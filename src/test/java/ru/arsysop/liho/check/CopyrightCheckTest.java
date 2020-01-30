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
