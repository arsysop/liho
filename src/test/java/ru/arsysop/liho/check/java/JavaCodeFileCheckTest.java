package ru.arsysop.liho.check.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.arsysop.liho.TestResource;
import ru.arsysop.liho.file.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaCodeFileCheckTest {

	@Test
	@DisplayName("applicable to a java source file")
	void applicable() {
		File file = new TestResource("A.java").file();
		JavaCodeFileCheck check = new JavaCodeFileCheck();
		assertTrue(check.applicable(file));
	}


}
