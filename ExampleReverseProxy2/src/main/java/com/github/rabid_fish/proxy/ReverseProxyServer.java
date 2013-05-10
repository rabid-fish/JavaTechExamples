package com.github.rabid_fish.proxy;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ReverseProxyServer {

	public static final int PROXY_PORT = 6001;
	public static final String PROXY_URL = "/form";
	public static final int TARGET_PORT = 6002;
	public static final String TARGET_HOST = "localhost";

	public static void main(String[] args) throws Exception {

		// Servlet servlet = new HelloServlet();
		Servlet servlet = new ReverseProxyMockResponseServlet(PROXY_URL, TARGET_HOST, TARGET_PORT);
		ServletHolder holder = new ServletHolder(servlet);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.addServlet(holder, "/*");

		Server server = new Server(PROXY_PORT);
		server.setHandler(context);
		server.start();
		server.join();
	}

}
