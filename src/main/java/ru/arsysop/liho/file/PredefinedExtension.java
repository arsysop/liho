package ru.arsysop.liho.file;

public class PredefinedExtension implements Extension{
	private final String extension;

	public PredefinedExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String get() {
		return extension;
	}
}
