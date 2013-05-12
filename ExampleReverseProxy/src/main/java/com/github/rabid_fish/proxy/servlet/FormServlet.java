package com.github.rabid_fish.proxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {

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
		writer.println("<h1>Form Servlet as of " + new Date().toString() + "</h1>");
		writer.println("\t<br>");

		writer.println("</body>");
		writer.println("</html>");

		writer.flush();
		writer.close();
	}
}