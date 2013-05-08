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
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.nio.protocol.BasicAsyncResponseProducer;
import org.apache.http.nio.protocol.HttpAsyncExchange;
import org.apache.http.nio.protocol.HttpAsyncRequestConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestHandler;
import org.apache.http.nio.protocol.HttpAsyncRequester;
import org.apache.http.protocol.HttpContext;

public class ProxyRequestHandler implements
		HttpAsyncRequestHandler<ProxyHttpExchange> {

	private final HttpHost target;
	private final HttpAsyncRequester executor;
	private final BasicNIOConnPool connPool;
	private final AtomicLong counter;

	public ProxyRequestHandler(final HttpHost target,
			final HttpAsyncRequester executor, final BasicNIOConnPool connPool) {
		
		super();
		this.target = target;
		this.executor = executor;
		this.connPool = connPool;
		this.counter = new AtomicLong(1);
	}

	public HttpAsyncRequestConsumer<ProxyHttpExchange> processRequest(
			final HttpRequest request, final HttpContext context) {
		
		ProxyHttpExchange httpExchange = (ProxyHttpExchange) context
				.getAttribute("http-exchange");
		
		if (httpExchange == null) {
			httpExchange = new ProxyHttpExchange();
			context.setAttribute("http-exchange", httpExchange);
		}
		
		synchronized (httpExchange) {
			httpExchange.reset();
			String id = String.format("%08X", this.counter.getAndIncrement());
			httpExchange.setId(id);
			httpExchange.setTarget(this.target);
			
			return new ProxyRequestConsumer(httpExchange, this.executor,
					this.connPool);
		}
	}

	public void handle(final ProxyHttpExchange httpExchange,
			final HttpAsyncExchange responseTrigger, final HttpContext context)
			throws HttpException, IOException {
		
		synchronized (httpExchange) {
			
			Exception ex = httpExchange.getException();
			if (ex != null) {
				System.out.println("[client<-proxy] " + httpExchange.getId()
						+ " " + ex);
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
				System.out.println("[client<-proxy] " + httpExchange.getId()
						+ " error response triggered");
			}
			
			HttpResponse response = httpExchange.getResponse();
			if (response != null) {
				responseTrigger.submitResponse(new ProxyResponseProducer(
						httpExchange));
				System.out.println("[client<-proxy] " + httpExchange.getId()
						+ " response triggered");
			}
			
			// No response yet.
			httpExchange.setResponseTrigger(responseTrigger);
		}
	}

}
