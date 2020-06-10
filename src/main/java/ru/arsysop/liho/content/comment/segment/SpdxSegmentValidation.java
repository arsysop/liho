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
package ru.arsysop.liho.content.comment.segment;

import ru.arsysop.lang.function.CachingFunction;
import ru.arsysop.liho.content.analysis.SegmentValidation;
import ru.arsysop.liho.content.analysis.ValidationResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//todo: obsolete EPL version checker
final class SpdxSegmentValidation implements SegmentValidation {

	private final CachingFunction<String, Pattern> pattern;

	SpdxSegmentValidation() {
		pattern = new CachingFunction<>("(.*)SPDX-License-Identifier: (.*)", Pattern::compile);
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
