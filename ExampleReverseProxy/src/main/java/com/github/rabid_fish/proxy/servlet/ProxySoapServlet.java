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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.github.rabid_fish.proxy.mock.MockMapRegexHelper;

/**
 * Proxies a SOAP service.
 */
public class ProxySoapServlet extends ReverseProxyServlet {

	String mockPath;
	MockMapRegexHelper mockMapRegexHelper;
	
	public ProxySoapServlet(String prefix, String host, int port, String mockPath) {
		super(prefix, host, port);
		this.mockPath = mockPath;
	}

    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    	
		mockMapRegexHelper = new MockMapRegexHelper(mockPath);
	}

	boolean sendMockResponse(HttpServletRequest request, HttpServletResponse response) {

		String method = request.getMethod();
		String url = request.getRequestURI();

		// Don't break parent functionality
		if ("CONNECT".equalsIgnoreCase(method)) {
			return false;
		}

		if (url.startsWith("/favicon.ico")) {
			return false;
		}

		try {
			String mockBody;

			if (method.equals("POST")) {
				
				ServletInputStream stream = request.getInputStream();
				String requestBody = IOUtils.toString(stream);
				
				// Need to save off the body so that the input stream can be reset later
				// by the parent class.
				request.setAttribute(ReverseProxyServlet.PROXY_REQUEST_BODY, requestBody);
				
				mockBody = mockMapRegexHelper.getMockBodyForBody(requestBody);
				if (mockBody != null) {
					writeMockResponse(response, mockBody);
					return true;
				}
			}
			
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
}