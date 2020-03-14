package ru.arsysop.liho.bulk;

import ru.arsysop.liho.content.comment.CommentType;
import ru.arsysop.liho.file.File;

import java.util.List;

final class CommentTypes {
	private final List<CommentType> types;

	CommentTypes(List<CommentType> types) {
		this.types = types;
	}

	boolean supports(File file) {
		return types.stream().anyMatch(type -> type.compatibleWith(file));
	}

	/**
	 * It is mandatory to ask if the given file is {@linkplain #supports(File)} prior.
	 */
	CommentType type(File file) {
		//noinspection OptionalGetWithoutIsPresent
		return types.stream().filter(type -> type.compatibleWith(file)).findFirst().get();
	}
}
