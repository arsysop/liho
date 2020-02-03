package ru.arsysop.liho.check;

import ru.arsysop.liho.file.File;

import java.io.IOException;
import java.util.List;

public interface Comment  {

	/**
	 * @param file description of a file to check. Cannot be {@code null}
	 * @return a sign if the {@linkplain Comment} can extract header from the file described or not
	 */
	boolean compatibleWith(File file);

	List<String> get(File file) throws IOException;
}
