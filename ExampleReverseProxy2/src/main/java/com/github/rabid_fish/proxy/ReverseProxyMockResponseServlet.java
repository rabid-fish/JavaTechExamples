package com.github.rabid_fish.proxy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.servlets.ProxyServlet;

import com.github.rabid_fish.proxy.mock.MockMapRegexHelper;
import com.github.rabid_fish.proxy.mock.MockMapUrlHelper;

public class ReverseProxyMockResponseServlet extends ProxyServlet.Transparent {

	MockMapUrlHelper mockMapUrlHelper;
	MockMapRegexHelper mockMapRegexHelper;
	
	public ReverseProxyMockResponseServlet() {
		super();
	}

	public ReverseProxyMockResponseServlet(String prefix, String host, int port) {
		super(prefix, host, port);
	}

	public ReverseProxyMockResponseServlet(String prefix, String schema, String host, int port, String path) {
		super(prefix, schema, host, port, path);
	}

	@Override
	protected void customizeExchange(HttpExchange exchange, HttpServletRequest request) {

		// Some headers we might consider adding
		// "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
		// "Accept-Charset:ISO-8859-1,utf-8;q=0.7,*;q=0.3"
		// "Accept-Encoding:gzip,deflate,sdch"
		// "Accept-Language:en-US,en;q=0.8"
		// "Cache-Control:no-cache"
		// "Connection:keep-alive"
		// "Host:logback.qos.ch"
		// "Pragma:no-cache"

		// For whatever reason, transparent proxying was broken unless we
		// cleared out the request headers. Yahoo, microsoft, and your 
		// average Apache run site were behaving as if we are connecting 
		// via telnet and returned something different from the page 
		// being requested.

		exchange.getRequestFields().clear();
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

	/**
	 * Allows easy overriding of initialization
	 */
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    	
		mockMapUrlHelper = new MockMapUrlHelper("/html/example_url.json");
		mockMapRegexHelper = new MockMapRegexHelper("/soap/example_regex.json");
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

	void writeMockResponse(HttpServletResponse response, String body) throws IOException {
		
		PrintWriter writer = response.getWriter();
		writer.println(body);
		writer.flush();
		writer.close();
	}
}