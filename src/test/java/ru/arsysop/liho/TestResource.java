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
package ru.arsysop.liho;

import ru.arsysop.liho.file.File;
import ru.arsysop.liho.file.FileFromPath;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public final class TestResource {

	private final Cashed<String, File> file;
	private final Cashed<String, Path> path;

	public TestResource(String location) {
		file = new Cashed<>(location, this::file);
		path = new Cashed<>(location, this::path);
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
