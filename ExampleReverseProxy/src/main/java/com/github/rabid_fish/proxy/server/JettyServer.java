package com.github.rabid_fish.proxy.server;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.github.rabid_fish.proxy.servlet.FormServlet;
import com.github.rabid_fish.proxy.servlet.ProxyHtmlServlet;
import com.github.rabid_fish.proxy.servlet.ProxySoapServlet;

/**
 * Useful links to read
 * 
 * "I want to send an XML file as a request to a SOAP server"...
 * http://stackoverflow.com/questions/12827900/why-is-this-simple-soap-client-not-working-org-apache-http
 */
public class JettyServer {

	public static final String CONTEXT_PATH = "/";

	public static void main(String[] args) throws Exception {
		
		try {
			JettyServerConfig config = new JettyServerConfig(args);
			new JettyServer().runServer(config);
		} catch (IllegalArgumentException e) {
			printUsage(e);
		}
	}
	
	public static void printUsage(IllegalArgumentException e) {
		
		String list = JettyServerType.getPrettyPrintList();
		
		String message = 
				"Error: " + e.getMessage() + "\n\n" +
				"Usage:\n" +
				"	java JettyServer SERVLET_TYPE SERVLET_PORT [PROXY_MOCK_CONFIG_PATH PROXY_URL TARGET_HOST TARGET_PORT]\n" +
				"\n" +
				"	SERVLET_TYPE:        one of " + list + "\n" +
				"	Parameters after SERVLET_TYPE apply to proxy server servlets only.\n" +
				"\n" +
				"	SERVLET_PORT:        an integer value\n" +
				"	PROXY_MOCK_CONFIG_PATH:    path to json file which maps mock file to intercept points\n" +
				"\n";
		
		System.out.println(message);
	}

	public void runServer(JettyServerConfig config) throws Exception {
		
		Servlet servlet = getServlet(config);
		ServletHolder holder = new ServletHolder(servlet);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath(CONTEXT_PATH);
		context.addServlet(holder, "/*");

		Server server = new Server(config.getServletPort());
		server.setHandler(context);
		server.start();
		server.join();
	}
	
	
	Servlet getServlet(JettyServerConfig config) {
		
		
		Servlet servlet = null;
		
		switch (config.getServletType()) {
		case FORM:
			servlet = getFormServlet(config);
			break;
		case PROXY_HTML:
			servlet = getReverseProxyHtmlServlet(config);
			break;
		case PROXY_SOAP:
			servlet = getReverseProxySoapServlet(config);
			break;
		}
		
		return servlet;
	}
	
	Servlet getFormServlet(JettyServerConfig config) {

		FormServlet servlet = new FormServlet();
		return servlet;
	}

	Servlet getReverseProxyHtmlServlet(JettyServerConfig config) {
		
		Servlet servlet = new ProxyHtmlServlet(config.getProxyUrl(), config.getTargetHost(), config.getTargetPort(), config.getProxyMockConfigPath());
		return servlet;
	}

	Servlet getReverseProxySoapServlet(JettyServerConfig config) {

		Servlet servlet = new ProxySoapServlet(config.getProxyUrl(), config.getTargetHost(), config.getTargetPort(), config.getProxyMockConfigPath());
		return servlet;
	}
	
}
