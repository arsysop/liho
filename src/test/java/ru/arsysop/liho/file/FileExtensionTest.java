package ru.arsysop.liho.file;

import org.junit.jupiter.api.Test;
import ru.arsysop.liho.TestResource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileExtensionTest {
	@Test
	void dataValueClass(){
		assertEquals(new PredefinedExtension("java"),
				new TestResource("A.java").file().extension());
	}
}
