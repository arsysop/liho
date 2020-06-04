/********************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   ArSysOp - initial API and implementation
 ********************************************************************************/
package ru.arsysop.liho.file;

import ru.arsysop.lang.function.CachingFunction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class FileFromPath implements File {

	private final Path path;
	private final CachingFunction<Path, Extension> extension;
	private final CachingFunction<Path, String> name;
	private final CachingFunction<Path, Path> folder;

	public FileFromPath(Path path) {
		this.path = path;
		extension = new CachingFunction<>(path, this::extensionFromPath);
		name = new CachingFunction<>(path, this::nameFromPath);
		folder = new CachingFunction<>(path, this::folderFromPath);
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
