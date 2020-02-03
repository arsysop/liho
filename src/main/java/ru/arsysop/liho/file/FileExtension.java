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

import ru.arsysop.liho.Cashed;

public final class FileExtension extends Extension {
	private final Cashed<String, String> extension;

	public FileExtension(String path) {
		this.extension = new Cashed<>(path, this::extension);
	}

	@Override
	public String get() {
		return extension.get();
	}

	private String extension(String path) {
		int comma = path.lastIndexOf('.');
		if (comma < 0) { // empty extension file
			return "";
		}
		return path.substring(comma + 1);
	}

}
