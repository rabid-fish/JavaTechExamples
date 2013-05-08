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
import java.net.InetSocketAddress;
import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.impl.nio.DefaultHttpClientIODispatch;
import org.apache.http.impl.nio.DefaultHttpServerIODispatch;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.DefaultListeningIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.protocol.HttpAsyncRequestHandlerRegistry;
import org.apache.http.nio.protocol.HttpAsyncRequester;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOEventDispatch;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.nio.reactor.ListeningIOReactor;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;

/**
 * The code below was copied from the following source and refactored:
 * http://hc.apache.org/httpcomponents-core-ga/examples.html
 * http://hc.apache.org/httpcomponents-core-ga/httpcore-nio/examples/org/apache/http/examples/nio/NHttpReverseProxy.java
 * 
 * @author djuliano
 */
public class HttpReverseProxy {

	public static void main(String[] args) throws Exception {
		
		if (args.length < 1) {
			System.out.println("Usage: HttpReverseProxy <targetUri> [sourcePort]");
			System.exit(1);
		}
		
		URI targetUri = new URI(args[0]);
		
		int sourcePort = 8080;
		if (args.length > 1) {
			sourcePort = Integer.parseInt(args[1]);
		}

		HttpHost targetHost = getHostFromUri(targetUri);
		System.out.println("Reverse proxy to " + targetHost);
		
		new HttpReverseProxy().startProxy(targetHost, sourcePort);
	}
	
	private static HttpHost getHostFromUri(URI targetUri) {
		
		int targetPort = targetUri.getPort() > 0 ? targetUri.getPort() : 80;
		String targetScheme = targetUri.getScheme() != null ? targetUri.getScheme() : "http";
		HttpHost targetHost = new HttpHost(targetUri.getHost(), targetPort, targetScheme);
		
		return targetHost;
	}

	public void startProxy(HttpHost targetHost, int sourcePort) throws IOReactorException {
		
		HttpParams connectionParameters = new SyncBasicHttpParams();
		connectionParameters
			.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 30000)
			.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8 * 1024)
			.setParameter(CoreProtocolPNames.ORIGIN_SERVER, "Test/1.1")
			.setParameter(CoreProtocolPNames.USER_AGENT, "Test/1.1");

		IOReactorConfig reactorConfig = new IOReactorConfig();
		reactorConfig.setIoThreadCount(1);
		final ConnectingIOReactor targetIOReactor = new DefaultConnectingIOReactor(reactorConfig);
		final ListeningIOReactor sourceIOReactor = new DefaultListeningIOReactor(reactorConfig);

		ProxyServiceHandler sourceHandler = getProxyServiceHandler(
				connectionParameters,	targetHost, targetIOReactor);
		
		final IOEventDispatch sourceEventDispatch = new DefaultHttpServerIODispatch(
				sourceHandler, connectionParameters);
		
		ProxyClientProtocolHandler targetHandler = new ProxyClientProtocolHandler();

		final IOEventDispatch targetEventDispatch = new DefaultHttpClientIODispatch(
				targetHandler, connectionParameters);

		startTargetReactor(targetIOReactor, sourceIOReactor, targetEventDispatch);
		startSourceReactor(targetIOReactor, sourceIOReactor, sourceEventDispatch, sourcePort);
	}

	private ProxyServiceHandler getProxyServiceHandler(
			HttpParams connectionParameters,
			HttpHost targetHost,
			final ConnectingIOReactor targetIOReactor) {
		
		HttpProcessor httpProcessorIn = getHttpProcessorIn();
		HttpProcessor httpProcessorOut = getHttpProcessorOut();
		
		ProxyConnPool connectionPool = getConnectionPool(connectionParameters, targetIOReactor);
		HttpAsyncRequester executor = new HttpAsyncRequester(httpProcessorOut, new ProxyOutgoingConnectionReuseStrategy(), connectionParameters);
		ProxyRequestHandler proxyRequestHandler = new ProxyRequestHandler(targetHost, executor, connectionPool);
		
		HttpAsyncRequestHandlerRegistry handlerRegistry = new HttpAsyncRequestHandlerRegistry();
		handlerRegistry.register("*", proxyRequestHandler);
		
		ProxyServiceHandler serviceHandler = new ProxyServiceHandler(
				httpProcessorIn, 
				new ProxyIncomingConnectionReuseStrategy(),
				handlerRegistry, 
				connectionParameters);
		
		return serviceHandler;
	}

	private HttpProcessor getHttpProcessorIn() {
		
		// Set up HTTP protocol processor for incoming connections
		HttpResponseInterceptor[] responseInterceptors = new HttpResponseInterceptor[] { 
				new ResponseDate(),
				new ResponseServer(), 
				new ResponseContent(),
				new ResponseConnControl() 
		};
		
		HttpProcessor inHttpProcessor = new ImmutableHttpProcessor(responseInterceptors);
		return inHttpProcessor;
	}

	private HttpProcessor getHttpProcessorOut() {
		
		// Set up HTTP protocol processor for outgoing connections
		HttpRequestInterceptor[] requestInterceptors = new HttpRequestInterceptor[] { 
				new RequestContent(),
				new RequestTargetHost(), 
				new RequestConnControl(),
				new RequestUserAgent(), 
				new RequestExpectContinue() 
		};
		
		HttpProcessor outHttpProcessor = new ImmutableHttpProcessor(requestInterceptors);
		return outHttpProcessor;
	}

	private ProxyConnPool getConnectionPool(HttpParams connectionParameters,
			final ConnectingIOReactor connectingIOReactor) {
		
		ProxyConnPool connectionPool = new ProxyConnPool(connectingIOReactor, connectionParameters);
		connectionPool.setMaxTotal(100);
		connectionPool.setDefaultMaxPerRoute(20);
		return connectionPool;
	}

	private void startTargetReactor(
			final ConnectingIOReactor targetIOReactor,
			final ListeningIOReactor sourceIOReactor,
			final IOEventDispatch targetEventDispatch) {

		// Target reactor starts in a separate thread
		Thread t = new Thread(new Runnable() {

			public void run() {
				try {
					targetIOReactor.execute(targetEventDispatch);
				} catch (InterruptedIOException ex) {
					System.err.println("Interrupted");
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					try {
						sourceIOReactor.shutdown();
					} catch (IOException ex2) {
						ex2.printStackTrace();
					}
				}
			}

		});

		t.start();
	}

	private void startSourceReactor(
			final ConnectingIOReactor targetIOReactor,
			final ListeningIOReactor sourceIOReactor,
			final IOEventDispatch sourceEventDispatch,
			int sourcePort) {
		
		try {
			sourceIOReactor.listen(new InetSocketAddress(sourcePort));
			sourceIOReactor.execute(sourceEventDispatch);
			
		} catch (InterruptedIOException ex) {
			System.err.println("Interrupted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				targetIOReactor.shutdown();
			} catch (IOException ex2) {
				ex2.printStackTrace();
			}
		}
	}

}