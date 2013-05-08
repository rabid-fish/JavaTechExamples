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
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.nio.protocol.BasicAsyncResponseProducer;
import org.apache.http.nio.protocol.HttpAsyncExchange;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.apache.http.protocol.HttpContext;

public class ProxyResponseConsumer implements
		HttpAsyncResponseConsumer<ProxyHttpExchange> {

	private final ProxyHttpExchange httpExchange;

	private volatile boolean completed;

	public ProxyResponseConsumer(final ProxyHttpExchange httpExchange) {
		super();
		this.httpExchange = httpExchange;
	}

	public void close() throws IOException {
	}

	public void responseReceived(final HttpResponse response) {
		synchronized (this.httpExchange) {
			System.out.println("[proxy<-origin] " + this.httpExchange.getId()
					+ " " + response.getStatusLine());
			this.httpExchange.setResponse(response);
			HttpAsyncExchange responseTrigger = this.httpExchange
					.getResponseTrigger();
			if (responseTrigger != null && !responseTrigger.isCompleted()) {
				System.out.println("[client<-proxy] "
						+ this.httpExchange.getId() + " response triggered");
				responseTrigger.submitResponse(new ProxyResponseProducer(
						this.httpExchange));
			}
		}
	}

	public void consumeContent(final ContentDecoder decoder,
			final IOControl ioctrl) throws IOException {
		synchronized (this.httpExchange) {
			this.httpExchange.setOriginIOControl(ioctrl);
			// Receive data from the origin
			ByteBuffer buf = this.httpExchange.getOutBuffer();
			int n = decoder.read(buf);
			System.out.println("[proxy<-origin] " + this.httpExchange.getId()
					+ " " + n + " bytes read");
			if (decoder.isCompleted()) {
				System.out.println("[proxy<-origin] "
						+ this.httpExchange.getId() + " content fully read");
			}
			// If the buffer is full, suspend origin input until there is
			// free
			// space in the buffer
			if (!buf.hasRemaining()) {
				ioctrl.suspendInput();
				System.out.println("[proxy<-origin] "
						+ this.httpExchange.getId() + " suspend origin input");
			}
			// If there is some content in the input buffer make sure client
			// output is active
			if (buf.position() > 0) {
				if (this.httpExchange.getClientIOControl() != null) {
					this.httpExchange.getClientIOControl().requestOutput();
					System.out.println("[proxy<-origin] "
							+ this.httpExchange.getId()
							+ " request client output");
				}
			}
		}
	}

	public void responseCompleted(final HttpContext context) {
		synchronized (this.httpExchange) {
			if (this.completed) {
				return;
			}
			this.completed = true;
			System.out.println("[proxy<-origin] " + this.httpExchange.getId()
					+ " response completed");
			this.httpExchange.setResponseReceived();
			if (this.httpExchange.getClientIOControl() != null) {
				this.httpExchange.getClientIOControl().requestOutput();
				System.out.println("[proxy<-origin] "
						+ this.httpExchange.getId() + " request client output");
			}
		}
	}

	public void failed(final Exception ex) {
		synchronized (this.httpExchange) {
			if (this.completed) {
				return;
			}
			this.completed = true;
			this.httpExchange.setException(ex);
			HttpAsyncExchange responseTrigger = this.httpExchange
					.getResponseTrigger();
			if (responseTrigger != null && !responseTrigger.isCompleted()) {
				System.out.println("[client<-proxy] "
						+ this.httpExchange.getId() + " " + ex);
				int status = HttpStatus.SC_INTERNAL_SERVER_ERROR;
				HttpResponse response = new BasicHttpResponse(
						HttpVersion.HTTP_1_0, status,
						EnglishReasonPhraseCatalog.INSTANCE.getReason(status,
								Locale.US));
				String message = ex.getMessage();
				if (message == null) {
					message = "Unexpected error";
				}
				response.setEntity(new NStringEntity(message,
						ContentType.DEFAULT_TEXT));
				responseTrigger.submitResponse(new BasicAsyncResponseProducer(
						response));
			}
		}
	}

	public boolean cancel() {
		synchronized (this.httpExchange) {
			if (this.completed) {
				return false;
			}
			failed(new InterruptedIOException("Cancelled"));
			return true;
		}
	}

	public ProxyHttpExchange getResult() {
		return this.httpExchange;
	}

	public Exception getException() {
		return null;
	}

	public boolean isDone() {
		return this.completed;
	}

}
