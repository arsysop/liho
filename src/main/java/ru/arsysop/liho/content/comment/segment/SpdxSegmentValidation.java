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
package ru.arsysop.liho.content.comment.segment;

import ru.arsysop.liho.Cashed;
import ru.arsysop.liho.content.analysis.SegmentValidation;
import ru.arsysop.liho.content.analysis.ValidationResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//todo: obsolete EPL version checker
final class SpdxSegmentValidation implements SegmentValidation {

	private final Cashed<String, Pattern> pattern;

	SpdxSegmentValidation() {
		pattern = new Cashed<>("(.*)SPDX-License-Identifier: (.*)", Pattern::compile);
	}

	@Override
	public ValidationResult apply(String source) {
		Matcher matcher = pattern.get().matcher(source);
		if (!matcher.matches()) {
			return new ValidationResult.NotFound();
		}
		return new ValidationResult.Ok();
	}

}
