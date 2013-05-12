package com.github.rabid_fish.proxy.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.github.rabid_fish.proxy.mock.MockMapRegexHelper;

public class ProxySoapServlet extends ReverseProxyServlet {

	String mockPath;
	MockMapRegexHelper mockMapRegexHelper;
	
//	public ProxySoapServlet() {
//		super();
//	}

	public ProxySoapServlet(String prefix, String host, int port, String mockPath) {
		super(prefix, host, port);
		this.mockPath = mockPath;
	}

//	public ProxySoapServlet(String prefix, String schema, String host, int port, String path) {
//		super(prefix, schema, host, port, path);
//	}

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