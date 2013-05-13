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

package com.github.rabid_fish.proxy.server;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.github.rabid_fish.proxy.servlet.HtmlServlet;
import com.github.rabid_fish.proxy.servlet.ProxyHtmlServlet;
import com.github.rabid_fish.proxy.servlet.ProxySoapServlet;
import com.github.rabid_fish.proxy.servlet.SoapServlet;

/**
 * Only class with a main method, JettyServer stands up a single-servlet
 * Jetty server instance based on the arguments passed in.  The servlet
 * types are listed in the enum JettyServerType.
 * 
 * See the README.txt file for a list of command line examples of how
 * to interact with this class' main().
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
		case HTML:
			servlet = new HtmlServlet();
			break;
		case SOAP:
			servlet = new SoapServlet();
			break;
		case PROXY_HTML:
			servlet = new ProxyHtmlServlet(config.getProxyUrl(), config.getTargetHost(), config.getTargetPort(), config.getProxyMockConfigPath());
			break;
		case PROXY_SOAP:
			servlet = new ProxySoapServlet(config.getProxyUrl(), config.getTargetHost(), config.getTargetPort(), config.getProxyMockConfigPath());
			break;
		}
		
		return servlet;
	}
}
