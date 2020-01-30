package ru.arsysop.liho.file;

import ru.arsysop.liho.Cashed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class FileFromPath implements File {

	private final Path path;
	private final Cashed<Path, Extension> extension;
	private final Cashed<Path, String> name;
	private final Cashed<Path, Path> folder;

	public FileFromPath(Path path) {
		this.path = path;
		extension = new Cashed<>(path, this::extensionFromPath);
		name = new Cashed<>(path, this::nameFromPath);
		folder = new Cashed<>(path, this::folderFromPath);
	}

	@Override
	public Path origin() {
		return path;
	}

	@Override
	public Extension extension() {
		return extension.get();
	}

	@Override
	public String name() {
		return name.get();
	}

	@Override
	public Path folder() {
		return folder.get();
	}

	@Override
	public Stream<String> lines() throws IOException {
		return Files.lines(path);
	}

	private Extension extensionFromPath(Path source) {
		return new FileExtension(source.getFileName().toString());
	}

	private String nameFromPath(Path source) {
		String full = source.getFileName().toString();
		int comma = full.lastIndexOf('.');
		if (comma < 0) {
			return full;
		} else {
			return full.substring(0, comma);
		}
	}

	private Path folderFromPath(Path source) {
		return source.getParent();
	}

}
