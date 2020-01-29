package ru.arsysop.liho.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.arsysop.liho.TestResource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileFromPathTest {
	private final TestResource complete = new TestResource("src/org/eclipse/B.java");
	private final TestResource noName = new TestResource("res/test/foreign/.rc");
	private final TestResource noExtension = new TestResource("res/test/foreign/gradlew");

	@Test
	@DisplayName("file extension is parsed correctly")
	void parseExtension(){
		assertEquals("java", complete.file().extension().get());
	}

	@Test
	@DisplayName("file name is parsed correctly")
	void parseName(){
		assertEquals("B", complete.file().name());
	}

	@Test
	@DisplayName("file parent folder is parsed correctly")
	void parseFolder(){
		assertEquals(complete.path().getParent(), complete.file().folder());
	}

	@Test
	@DisplayName("name is parsed correctly for no-name file")
	void parseNoNameFileForName(){
		assertTrue(noName.file().name().isEmpty());
	}

	@Test
	@DisplayName("extension is parsed correctly for no-name file")
	void parseNoNameFileForExtension(){
		assertEquals("rc", noName.file().extension().get());
	}

	@Test
	@DisplayName("name is parsed correctly for no-extension file")
	void parseNoExtensionFileForName(){
		assertEquals("gradlew", noExtension.file().name());
	}

	@Test
	@DisplayName("extension is parsed correctly for no-extension file")
	void parseNoExtensionFileForExtension(){
		assertTrue(noExtension.file().extension().get().isEmpty());
	}

}
