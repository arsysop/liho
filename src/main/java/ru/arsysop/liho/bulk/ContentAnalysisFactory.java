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
package ru.arsysop.liho.bulk;

import ru.arsysop.liho.content.analysis.ContentAnalysis;
import ru.arsysop.liho.file.File;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class ContentAnalysisFactory implements Function<File, List<ContentAnalysis>> {
	private final List<Function<File, ContentAnalysis>> analyzers;

	ContentAnalysisFactory(List<Function<File, ContentAnalysis>> analyzers) {
		this.analyzers = analyzers;
	}

	@Override
	public List<ContentAnalysis> apply(File file) {
		return analyzers.stream().map(supplier -> supplier.apply(file)).collect(Collectors.toList());
	}
}
