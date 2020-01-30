package ru.arsysop.liho.file;

public final class PredefinedExtension extends Extension{

	private final String extension;

	public PredefinedExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String get() {
		return extension;
	}

}
