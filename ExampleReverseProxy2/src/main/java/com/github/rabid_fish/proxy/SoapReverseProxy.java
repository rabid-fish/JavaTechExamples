package com.github.rabid_fish.proxy;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.ProxyServlet;

import com.github.rabid_fish.proxy.JettyScratch.SomewhereTransparentProxyServlet;

/**
 * Sample SOAP Request and Response with headers may be found at:
 * http://www.w3schools.com/soap/soap_example.asp
 * 
 */
public class SoapReverseProxy {

	public static class SoapTransparentProxyServlet extends
			ProxyServlet.Transparent {

		public SoapTransparentProxyServlet() {
			super();
		}

		public SoapTransparentProxyServlet(String prefix, String host,
				int port) {
			super(prefix, host, port);
		}

		public SoapTransparentProxyServlet(String prefix, String schema,
				String host, int port, String path) {
			super(prefix, schema, host, port, path);
		}

		@Override
		protected void customizeExchange(HttpExchange exchange,
				HttpServletRequest request) {

			// Some headers we might consider adding
			// "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
			// "Accept-Charset:ISO-8859-1,utf-8;q=0.7,*;q=0.3"
			// "Accept-Encoding:gzip,deflate,sdch"
			// "Accept-Language:en-US,en;q=0.8"
			// "Cache-Control:no-cache"
			// "Connection:keep-alive"
			// "Host:logback.qos.ch"
			// "Pragma:no-cache"

			exchange.getRequestFields().clear();
		}
	}

	public static void main(String[] args) throws Exception {

		// Servlet servlet = new HelloServlet();
		Servlet servlet = new SomewhereTransparentProxyServlet("/yahoo", "www.yahoo.com", 80);
		ServletHolder holder = new ServletHolder(servlet);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.addServlet(holder, "/*");

		Server server = new Server(8080);
		server.setHandler(context);
		server.start();
		server.join();
	}

}
