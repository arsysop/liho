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
package ru.arsysop.liho.content.comment;

final class FakeCommentSearchEngine extends CommentSearchEngine {

	@Override
	protected boolean start(String line) {
		return true;
	}

	@Override
	protected boolean end(String line) {
		return true;
	}

	@Override
	protected boolean includeLast() {
		throw new UnsupportedOperationException("Is not expected to be invoked");
	}

	@Override
	protected String strip(String line) {
		throw new UnsupportedOperationException("Is not expected to be invoked");
	}

}
