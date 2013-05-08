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

import java.nio.ByteBuffer;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.protocol.HttpAsyncExchange;

public class ProxyHttpExchange {

	private final ByteBuffer inBuffer;
	private final ByteBuffer outBuffer;

	private volatile String id;
	private volatile HttpHost target;
	private volatile HttpAsyncExchange responseTrigger;
	private volatile IOControl originIOControl;
	private volatile IOControl clientIOControl;
	private volatile HttpRequest request;
	private volatile boolean requestReceived;
	private volatile HttpResponse response;
	private volatile boolean responseReceived;
	private volatile Exception ex;

	public ProxyHttpExchange() {
		super();
		this.inBuffer = ByteBuffer.allocateDirect(10240);
		this.outBuffer = ByteBuffer.allocateDirect(10240);
	}

	public ByteBuffer getInBuffer() {
		return this.inBuffer;
	}

	public ByteBuffer getOutBuffer() {
		return this.outBuffer;
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public HttpHost getTarget() {
		return this.target;
	}

	public void setTarget(final HttpHost target) {
		this.target = target;
	}

	public HttpRequest getRequest() {
		return this.request;
	}

	public void setRequest(final HttpRequest request) {
		this.request = request;
	}

	public HttpResponse getResponse() {
		return this.response;
	}

	public void setResponse(final HttpResponse response) {
		this.response = response;
	}

	public HttpAsyncExchange getResponseTrigger() {
		return this.responseTrigger;
	}

	public void setResponseTrigger(final HttpAsyncExchange responseTrigger) {
		this.responseTrigger = responseTrigger;
	}

	public IOControl getClientIOControl() {
		return this.clientIOControl;
	}

	public void setClientIOControl(final IOControl clientIOControl) {
		this.clientIOControl = clientIOControl;
	}

	public IOControl getOriginIOControl() {
		return this.originIOControl;
	}

	public void setOriginIOControl(final IOControl originIOControl) {
		this.originIOControl = originIOControl;
	}

	public boolean isRequestReceived() {
		return this.requestReceived;
	}

	public void setRequestReceived() {
		this.requestReceived = true;
	}

	public boolean isResponseReceived() {
		return this.responseReceived;
	}

	public void setResponseReceived() {
		this.responseReceived = true;
	}

	public Exception getException() {
		return this.ex;
	}

	public void setException(final Exception ex) {
		this.ex = ex;
	}

	public void reset() {
		this.inBuffer.clear();
		this.outBuffer.clear();
		this.target = null;
		this.id = null;
		this.responseTrigger = null;
		this.clientIOControl = null;
		this.originIOControl = null;
		this.request = null;
		this.requestReceived = false;
		this.response = null;
		this.responseReceived = false;
		this.ex = null;
	}

}
