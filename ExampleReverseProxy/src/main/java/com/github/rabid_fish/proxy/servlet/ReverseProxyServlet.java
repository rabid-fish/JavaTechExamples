/*
	Copyright 2013 Daniel Juliano
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.github.rabid_fish.proxy.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.servlets.ProxyServlet;

/**
 * Abstract class that takes care of a couple things for proxies:
 * <ul>
 * <li>clears the request headers so target servers do no misinterpret the request</li>
 * <li>provides utility methods for child classes</li>
 * </ul>
 */
public abstract class ReverseProxyServlet extends ProxyServlet.Transparent {

	public static final String PROXY_REQUEST_BODY = "proxy-request-body";

	abstract boolean sendMockResponse(HttpServletRequest request, HttpServletResponse response);

	public ReverseProxyServlet(String prefix, String host, int port) {
		super(prefix, host, port);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		if (sendMockResponse(request, response)) {
			return;
		}

		super.service(request, response);
	}
    
	@Override
	protected void customizeExchange(HttpExchange exchange, HttpServletRequest request) {

		// For whatever reason, transparent proxying was broken unless we
		// cleared out some the request headers. Yahoo, microsoft, and your 
		// average Apache run site were behaving as if we are connecting 
		// via telnet and returned something different from the page 
		// being requested.
		HttpFields requestFields = exchange.getRequestFields();
		for (int i = requestFields.size() - 1; i > 0; i--) {
			
			String fieldName = requestFields.getField(i).getName();
			String value = requestFields.getField(i).getValue();
			
			System.out.println(fieldName + " = " + value);
			if (fieldName.startsWith("X-Forwarded") ||
				fieldName.startsWith("Via")) {
				requestFields.remove(fieldName);
			}
		}
		
		// If we read out 'request.getInputStream()' in the overriding servlet,
		// then we have to reset the exchange's requestContentSource to a new
		// stream.
		if (request.getAttribute(PROXY_REQUEST_BODY) != null) {
			String requestBody = (String) request.getAttribute(PROXY_REQUEST_BODY);
			InputStream in = new ByteArrayInputStream(requestBody.getBytes());
			exchange.setRequestContentSource(in);
		}
	}
	
	void writeMockResponse(HttpServletResponse response, String body) throws IOException {
		
		PrintWriter writer = response.getWriter();
		writer.println(body);
		writer.flush();
		writer.close();
	}
}
