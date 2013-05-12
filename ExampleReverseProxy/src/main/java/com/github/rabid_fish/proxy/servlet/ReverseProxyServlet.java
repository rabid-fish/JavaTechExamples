package com.github.rabid_fish.proxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.servlets.ProxyServlet;

public abstract class ReverseProxyServlet extends ProxyServlet.Transparent {

	abstract boolean sendMockResponse(HttpServletRequest request, HttpServletResponse response);

//	public ReverseProxyServlet() {
//		super();
//	}
//
	public ReverseProxyServlet(String prefix, String host, int port) {
		super(prefix, host, port);
	}

//	public ReverseProxyServlet(String prefix, String schema, String host, int port, String path) {
//		super(prefix, schema, host, port, path);
//	}

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

	void writeMockResponse(HttpServletResponse response, String body) throws IOException {
		
		PrintWriter writer = response.getWriter();
		writer.println(body);
		writer.flush();
		writer.close();
	}
}