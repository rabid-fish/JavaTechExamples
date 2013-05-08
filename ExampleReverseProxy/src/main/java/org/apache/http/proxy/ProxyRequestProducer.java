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

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.protocol.HttpContext;

public class ProxyRequestProducer implements HttpAsyncRequestProducer {

	private final ProxyHttpExchange httpExchange;

	public ProxyRequestProducer(final ProxyHttpExchange httpExchange) {
		super();
		this.httpExchange = httpExchange;
	}

	public void close() throws IOException {
	}

	public HttpHost getTarget() {
		synchronized (this.httpExchange) {
			return this.httpExchange.getTarget();
		}
	}

	public HttpRequest generateRequest() {
		synchronized (this.httpExchange) {
			HttpRequest request = this.httpExchange.getRequest();
			System.out.println("[proxy->origin] "
					+ this.httpExchange.getId() + " "
					+ request.getRequestLine());
			// Rewrite request!!!!
			if (request instanceof HttpEntityEnclosingRequest) {
				BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest(
						request.getRequestLine());
				r.setEntity(((HttpEntityEnclosingRequest) request)
						.getEntity());
				return r;
			} else {
				return new BasicHttpRequest(request.getRequestLine());
			}
		}
	}

	public void produceContent(final ContentEncoder encoder,
			final IOControl ioctrl) throws IOException {
		synchronized (this.httpExchange) {
			this.httpExchange.setOriginIOControl(ioctrl);
			// Send data to the origin server
			ByteBuffer buf = this.httpExchange.getInBuffer();
			buf.flip();
			int n = encoder.write(buf);
			buf.compact();
			System.out.println("[proxy->origin] "
					+ this.httpExchange.getId() + " " + n
					+ " bytes written");
			// If there is space in the buffer and the message has not been
			// transferred, make sure the client is sending more data
			if (buf.hasRemaining()
					&& !this.httpExchange.isRequestReceived()) {
				if (this.httpExchange.getClientIOControl() != null) {
					this.httpExchange.getClientIOControl().requestInput();
					System.out.println("[proxy->origin] "
							+ this.httpExchange.getId()
							+ " request client input");
				}
			}
			if (buf.position() == 0) {
				if (this.httpExchange.isRequestReceived()) {
					encoder.complete();
					System.out.println("[proxy->origin] "
							+ this.httpExchange.getId()
							+ " content fully written");
				} else {
					// Input buffer is empty. Wait until the client fills up
					// the buffer
					ioctrl.suspendOutput();
					System.out.println("[proxy->origin] "
							+ this.httpExchange.getId()
							+ " suspend origin output");
				}
			}
		}
	}

	public void requestCompleted(final HttpContext context) {
		synchronized (this.httpExchange) {
			System.out.println("[proxy->origin] "
					+ this.httpExchange.getId() + " request completed");
		}
	}

	public boolean isRepeatable() {
		return false;
	}

	public void resetRequest() {
	}

	public void failed(final Exception ex) {
		System.out.println("[proxy->origin] " + ex.toString());
	}

}
