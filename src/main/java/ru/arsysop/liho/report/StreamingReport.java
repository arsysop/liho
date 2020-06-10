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
package ru.arsysop.liho.report;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Supplier;

public final class StreamingReport implements Report {

	private final Supplier<PrintStream> stream;
	private Function<Issue, String> row = this::row;

	public StreamingReport(PrintStream stream) {
		this(() -> stream);
	}

	public StreamingReport(Supplier<OutputStream> stream) {
		this.stream = () -> new PrintStream(stream.get());
	}

	public StreamingReport withRow(Function<Issue, String> row) {
		this.row = row;
		return this;
	}

	@Override
	public void issue(IssueType type, IssueLocation location) {
		stream.get().println(row.apply(new Issue(type, location)));
	}

	private String row(Issue issue) {
		return String.format("%s in %s, line %d", issue.type().id(), issue.location().file(), issue.location().line());
	}

}
