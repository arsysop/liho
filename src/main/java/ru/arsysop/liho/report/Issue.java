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

import java.util.Objects;

public final class Issue {

	private final IssueType type;
	private final IssueLocation location;

	public Issue(IssueType type, IssueLocation location) {
		this.type = type;
		this.location = location;
	}

	public IssueType type() {
		return type;
	}

	public IssueLocation location() {
		return location;
	}

	@Override
	public boolean equals(Object obj){
		if(!getClass().isInstance(obj)){
			return  false;
		}
		Issue issue = (Issue) obj;
		return  type.equals(issue.type) && location.equals(issue.location);
	}

	@Override
	public int hashCode(){
		return Objects.hash(type, location);
	}

	@Override
	public String toString(){
		return type + "\r\nat " + location;
	}

}
