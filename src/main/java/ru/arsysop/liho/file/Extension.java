package ru.arsysop.liho.file;

import java.util.function.Supplier;

public abstract class Extension implements Supplier<String> {

	@Override
	public int hashCode() {
		return get().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!Extension.class.isInstance(obj)) {
			return false;
		}
		return get().equals(((FileExtension) obj).get());
	}

}
