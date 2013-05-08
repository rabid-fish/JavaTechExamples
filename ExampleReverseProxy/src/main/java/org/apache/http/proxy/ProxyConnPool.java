/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.proxy;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.impl.nio.pool.BasicNIOPoolEntry;
import org.apache.http.nio.NHttpClientConnection;
import org.apache.http.nio.pool.NIOConnFactory;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.PoolStats;

public class ProxyConnPool extends BasicNIOConnPool {

	public ProxyConnPool(final ConnectingIOReactor ioreactor,
			final HttpParams params) {
		super(ioreactor, params);
	}

	public ProxyConnPool(final ConnectingIOReactor ioreactor,
			final NIOConnFactory<HttpHost, NHttpClientConnection> connFactory,
			final HttpParams params) {
		super(ioreactor, connFactory, params);
	}

	@Override
	public void release(final BasicNIOPoolEntry entry, boolean reusable) {
		System.out.println("[proxy->origin] connection released "
				+ entry.getConnection());
		super.release(entry, reusable);
		StringBuilder buf = new StringBuilder();
		PoolStats totals = getTotalStats();
		buf.append("[total kept alive: ").append(totals.getAvailable())
				.append("; ");
		buf.append("total allocated: ").append(
				totals.getLeased() + totals.getAvailable());
		buf.append(" of ").append(totals.getMax()).append("]");
		System.out.println("[proxy->origin] " + buf.toString());
	}

}
