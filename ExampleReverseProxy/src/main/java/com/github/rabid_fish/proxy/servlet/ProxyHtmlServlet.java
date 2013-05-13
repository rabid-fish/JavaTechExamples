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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.rabid_fish.proxy.mock.MockMapUrlHelper;

/**
 * Proxies an html 'GET' service.
 */
public class ProxyHtmlServlet extends ReverseProxyServlet {

	String mockPath;
	MockMapUrlHelper mockMapUrlHelper;
	
	public ProxyHtmlServlet(String prefix, String host, int port, String mockPath) {
		super(prefix, host, port);
		this.mockPath = mockPath;
	}

    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    	
		mockMapUrlHelper = new MockMapUrlHelper(mockPath);
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
			
			mockBody = mockMapUrlHelper.getMockBodyForUrl(url);
			if (mockBody != null) {
				writeMockResponse(response, mockBody);
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
}