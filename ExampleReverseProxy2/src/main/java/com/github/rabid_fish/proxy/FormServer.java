package com.github.rabid_fish.proxy;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class FormServer {

	public static void main(String[] args) throws Exception {
		
		Server server = new Server(6002);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new FormServlet()), "/*");

		server.start();
		server.join();
	}
}
