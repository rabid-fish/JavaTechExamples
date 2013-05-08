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

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.protocol.HttpAsyncResponseProducer;
import org.apache.http.protocol.HttpContext;

public class ProxyResponseProducer implements HttpAsyncResponseProducer {

	private final ProxyHttpExchange httpExchange;

	public ProxyResponseProducer(final ProxyHttpExchange httpExchange) {
		super();
		this.httpExchange = httpExchange;
	}

	public void close() throws IOException {
		this.httpExchange.reset();
	}

	public HttpResponse generateResponse() {
		synchronized (this.httpExchange) {
			HttpResponse response = this.httpExchange.getResponse();
			System.out.println("[client<-proxy] " + this.httpExchange.getId()
					+ " " + response.getStatusLine());
			// Rewrite response!!!!
			BasicHttpResponse r = new BasicHttpResponse(
					response.getStatusLine());
			r.setEntity(response.getEntity());
			return r;
		}
	}

	public void produceContent(final ContentEncoder encoder,
			final IOControl ioctrl) throws IOException {
		synchronized (this.httpExchange) {
			this.httpExchange.setClientIOControl(ioctrl);
			// Send data to the client
			ByteBuffer buf = this.httpExchange.getOutBuffer();
			buf.flip();
			int n = encoder.write(buf);
			buf.compact();
			System.out.println("[client<-proxy] " + this.httpExchange.getId()
					+ " " + n + " bytes written");
			// If there is space in the buffer and the message has not been
			// transferred, make sure the origin is sending more data
			if (buf.hasRemaining() && !this.httpExchange.isResponseReceived()) {
				if (this.httpExchange.getOriginIOControl() != null) {
					this.httpExchange.getOriginIOControl().requestInput();
					System.out.println("[client<-proxy] "
							+ this.httpExchange.getId()
							+ " request origin input");
				}
			}
			if (buf.position() == 0) {
				if (this.httpExchange.isResponseReceived()) {
					encoder.complete();
					System.out.println("[client<-proxy] "
							+ this.httpExchange.getId()
							+ " content fully written");
				} else {
					// Input buffer is empty. Wait until the origin fills up
					// the buffer
					ioctrl.suspendOutput();
					System.out.println("[client<-proxy] "
							+ this.httpExchange.getId()
							+ " suspend client output");
				}
			}
		}
	}

	public void responseCompleted(final HttpContext context) {
		synchronized (this.httpExchange) {
			System.out.println("[client<-proxy] " + this.httpExchange.getId()
					+ " response completed");
		}
	}

	public void failed(final Exception ex) {
		System.out.println("[client<-proxy] " + ex.toString());
	}

}
