package ru.arsysop.liho.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface File {

	Path origin();

	Extension extension();

	String name();

	Path folder();

	/**
	 * Lazy guy
	 *
	 * @return
	 * @throws IOException
	 */
	Stream<String> lines() throws IOException;
}
