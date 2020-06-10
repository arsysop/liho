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
package ru.arsysop.liho;

import ru.arsysop.lang.function.CachingFunction;
import ru.arsysop.liho.file.File;
import ru.arsysop.liho.file.FileFromPath;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public final class TestResource {

	private final CachingFunction<String, File> file;
	private final CachingFunction<String, Path> path;

	public TestResource(String location) {
		file = new CachingFunction<>(location, this::file);
		path = new CachingFunction<>(location, this::path);
	}

	public File file() {
		return file.get();
	}

	public Path path() {
		return path.get();
	}

	private File file(String location) {
		return new FileFromPath(path.get());
	}

	private Path path(String location) {
		try {
			return Paths.get(url(location).toURI());
		} catch (URISyntaxException e) {
			fail(e);
			throw new IllegalStateException("unreachable");
		}
	}

	private URL url(String location) {
		URL resource = this.getClass().getClassLoader().getResource(location);
		assumeTrue(resource != null);
		return resource;
	}

}
