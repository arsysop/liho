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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 * Orthodox OOP construction.
 * </p>
 *
 * <p>
 * Seat of mutability caused by prohibition of any calculation in a ctor and cashing.
 * </p>
 *
 * <p>
 * Typical use case:
 * </p>
 * <ul>
 * <li>you have a final field which, of course, must be initialized immediately
 * in ctor</li>
 * <li>you follow OOOP rule for light-weight ctor: no calculations, period</li>
 * </ul>
 *
 * <p>
 * Hence, you initialize a field with
 * </p>
 * <ul>
 * <li>a source of data and</li>
 * <li>a memorized way of retrieving the value from this source.</li>
 * </ul>
 *
 * <p>
 * The actual calculation is performed lazily, at the first {@code get} call.
 * </p>
 *
 * <p>
 * Then the result is cashed (forever) and all further {@code get}s do not cause
 * any calculation.
 * </p>
 *
 * <h3>Sample</h3>
 * <h4>1: final field initialization</h4>
 * <p>Say, you want to get a default user name out of an e-mail address. You want to do this ones,
 * but also strive to follow the rule <i>no work in constructor</i>.</p>
 * <pre>
 * Cached&lt;String, String&gt; user = new Cached&lt;&gt;(
 * 	"john.doe@somewhere.com",
 * 	email -&gt; email.substring(0, email.indexOf("@")));
 * </pre>
 *
 * <h4>2: value retrieval</h4>
 * <p>The retrieval causes actual calculation only for the first call and your code is free of cashing details. </p>
 * <pre>
 * user.get();
 * </pre>
 *
 * @param <S> type of data source object
 * @param <T> type of data retrieved and cashed
 * @since 0.1
 */
public final class Cached<S, T> implements Supplier<T> {

	private final S source;
	private final Function<S, T> retrieve;
	private final List<T> value = new ArrayList<T>(1);

	public Cached(S source, Function<S, T> retrieve) {
		this.source = source;
		this.retrieve = retrieve;
	}

	public T get() {
		if (value.isEmpty()) {
			value.add(retrieve.apply(source));
		}
		return value.get(0);
	}

}
