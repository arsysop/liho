/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
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
