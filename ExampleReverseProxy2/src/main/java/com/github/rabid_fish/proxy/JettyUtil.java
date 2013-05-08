package com.github.rabid_fish.proxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.ProxyServlet;

/**
 * http://stackoverflow.com/questions/8172465/usage-examples-for-jettys-
 * proxyservlet-transparent-class
 */
public class JettyUtil {

	public static class HelloServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {

			PrintWriter writer = resp.getWriter();
			writer.print("Hello");
			writer.flush();
			writer.close();

			System.out.println("hello");
		}
	}

	public static class GoogleProxyServlet extends ProxyServlet {

		@Override
		protected HttpURI proxyHttpURI(String scheme, String serverName,
				int serverPort, String uri) throws MalformedURLException {

			// return new HttpURI("http://forum.miata.net:80" + uri);
			// return new HttpURI("http://danandlaurajuliano.com:80/");
			return new HttpURI("http://danandlaurajuliano.com");
		}
	}

	public static class SomewhereTransparentProxyServlet extends
			ProxyServlet.Transparent {

		public SomewhereTransparentProxyServlet() {
			super();
		}

		public SomewhereTransparentProxyServlet(String prefix, String host, int port) {
			super(prefix, host, port);
		}

		public SomewhereTransparentProxyServlet(String prefix, String schema, String host, int port, String path) {
			super(prefix, schema, host, port, path);
		}
	}

	public static void main(String[] args) throws Exception {

		// Servlet servlet = new HelloServlet();
		Servlet servlet = new SomewhereTransparentProxyServlet("/logback", "logback.qos.ch", 80);
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