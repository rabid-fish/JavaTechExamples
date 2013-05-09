package com.github.rabid_fish.proxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class FormServer {

	public static class FormServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException,
				IOException {

			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			PrintWriter writer = response.getWriter();

			writer.println("<html>");
			writer.println("<body>");

			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = request.getParameter(name);
				writer.println("\t<p>" + name + " = " + value + "</p>");
			}

			writer.println("\t<br>");
			writer.println("\t" + new Date().toString());
			writer.println("\t<br>");

			writer.println("</body>");
			writer.println("</html>");

			writer.flush();
			writer.close();
		}
	}

	public static void main(String[] args) throws Exception {
		
		Server server = new Server(6001);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new FormServlet()), "/*");

		server.start();
		server.join();
	}
}
