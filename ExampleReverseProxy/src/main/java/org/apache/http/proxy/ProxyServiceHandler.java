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

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.nio.NHttpServerConnection;
import org.apache.http.nio.protocol.HttpAsyncRequestHandlerResolver;
import org.apache.http.nio.protocol.HttpAsyncService;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpProcessor;

public class ProxyServiceHandler extends HttpAsyncService {

	public ProxyServiceHandler(final HttpProcessor httpProcessor,
			final ConnectionReuseStrategy reuseStrategy,
			final HttpAsyncRequestHandlerResolver handlerResolver,
			final HttpParams params) {
		super(httpProcessor, reuseStrategy, handlerResolver, params);
	}

	@Override
	protected void log(final Exception ex) {
		ex.printStackTrace();
	}

	@Override
	public void connected(final NHttpServerConnection conn) {
		System.out.println("[client->proxy] connection open " + conn);
		super.connected(conn);
	}

	@Override
	public void closed(final NHttpServerConnection conn) {
		System.out.println("[client->proxy] connection closed " + conn);
		super.closed(conn);
	}

}
