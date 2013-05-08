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

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.http.HttpRequest;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.protocol.HttpAsyncRequestConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequester;
import org.apache.http.protocol.HttpContext;

public class ProxyRequestConsumer implements
		HttpAsyncRequestConsumer<ProxyHttpExchange> {

	private final ProxyHttpExchange httpExchange;
	private final HttpAsyncRequester executor;
	private final BasicNIOConnPool connPool;

	private volatile boolean completed;

	public ProxyRequestConsumer(final ProxyHttpExchange httpExchange,
			final HttpAsyncRequester executor, final BasicNIOConnPool connPool) {
		super();
		this.httpExchange = httpExchange;
		this.executor = executor;
		this.connPool = connPool;
	}

	public void close() throws IOException {
	}

	public void requestReceived(final HttpRequest request) {
		synchronized (this.httpExchange) {
			System.out.println("[client->proxy] " + this.httpExchange.getId()
					+ " " + request.getRequestLine());
			this.httpExchange.setRequest(request);
			this.executor
					.execute(new ProxyRequestProducer(this.httpExchange),
							new ProxyResponseConsumer(this.httpExchange),
							this.connPool);
		}
	}

	public void consumeContent(final ContentDecoder decoder,
			final IOControl ioctrl) throws IOException {
		synchronized (this.httpExchange) {
			this.httpExchange.setClientIOControl(ioctrl);
			// Receive data from the client
			ByteBuffer buf = this.httpExchange.getInBuffer();
			int n = decoder.read(buf);
			System.out.println("[client->proxy] " + this.httpExchange.getId()
					+ " " + n + " bytes read");
			if (decoder.isCompleted()) {
				System.out.println("[client->proxy] "
						+ this.httpExchange.getId() + " content fully read");
			}
			// If the buffer is full, suspend client input until there is
			// free
			// space in the buffer
			if (!buf.hasRemaining()) {
				ioctrl.suspendInput();
				System.out.println("[client->proxy] "
						+ this.httpExchange.getId() + " suspend client input");
			}
			// If there is some content in the input buffer make sure origin
			// output is active
			if (buf.position() > 0) {
				if (this.httpExchange.getOriginIOControl() != null) {
					this.httpExchange.getOriginIOControl().requestOutput();
					System.out.println("[client->proxy] "
							+ this.httpExchange.getId()
							+ " request origin output");
				}
			}
		}
	}

	public void requestCompleted(final HttpContext context) {
		synchronized (this.httpExchange) {
			this.completed = true;
			;
			System.out.println("[client->proxy] " + this.httpExchange.getId()
					+ " request completed");
			this.httpExchange.setRequestReceived();
			if (this.httpExchange.getOriginIOControl() != null) {
				this.httpExchange.getOriginIOControl().requestOutput();
				System.out.println("[client->proxy] "
						+ this.httpExchange.getId() + " request origin output");
			}
		}
	}

	public Exception getException() {
		return null;
	}

	public ProxyHttpExchange getResult() {
		return this.httpExchange;
	}

	public boolean isDone() {
		return this.completed;
	}

	public void failed(final Exception ex) {
		System.out.println("[client->proxy] " + ex.toString());
	}

}
