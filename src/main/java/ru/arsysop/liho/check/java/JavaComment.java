package ru.arsysop.liho.check.java;

import ru.arsysop.liho.check.HeadingComment;
import ru.arsysop.liho.file.Extension;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.file.PredefinedExtension;

import java.util.Arrays;

public final class JavaComment extends HeadingComment {
	private final Extension java = new PredefinedExtension("java");

	protected JavaComment() {
		super(Arrays.asList(
				new BlockComment(),
				new LineComment()
		));
	}

	@Override
	public boolean compatibleWith(File file) {
		return java.equals(file.extension());
	}

}
