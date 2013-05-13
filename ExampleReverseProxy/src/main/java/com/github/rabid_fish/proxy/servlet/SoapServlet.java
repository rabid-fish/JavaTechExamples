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

package com.github.rabid_fish.proxy.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Returns a SOAP response.
 * 
 * If using an http GET request, if you have '?wsdl' on the end of your url, 
 * this servlet will return the math.wsdl file.
 * 
 * If using an http POST request containing a valid MathRequest SOAP request,
 * this will add the two operands and return the result in a valid MathResponse
 * envelope.
 */
public class SoapServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final Pattern REGEX_OPERAND_1 = Pattern.compile("^.*operand1\\>(\\d+)\\<.*$", Pattern.MULTILINE);
	public static final Pattern REGEX_OPERAND_2 = Pattern.compile("^.*operand2\\>(\\d+)\\<.*$", Pattern.MULTILINE);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {

		PrintWriter writer = response.getWriter();

		if (request.getQueryString() != null && request.getQueryString().equals("wsdl")) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/xml");
			writeWsdl(writer);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setContentType("text/html");
			writer.append("404: page not found");
		}

		writer.flush();
		writer.close();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {

		PrintWriter writer = response.getWriter();
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/xml");
		
		try {
			ServletInputStream stream = request.getInputStream();
			String requestBody = IOUtils.toString(stream);
			
			Integer mathResult = getMathResult(requestBody);
			writeMathResult(writer, mathResult);
		} catch (Exception e) {
			writeSoapError(writer, e.getMessage());
		}

		writer.flush();
		writer.close();
	}

	Integer getMathResult(String requestBody) throws IOException {
		
		int operand1 = getOperand(requestBody, REGEX_OPERAND_1);
		int operand2 = getOperand(requestBody, REGEX_OPERAND_2);
		final int result = operand1 + operand2;
		return result;
	}
	
	Integer getOperand(String requestBody, Pattern regex) throws IOException {
		
		Integer operand = null;
		Matcher operandMatcher = regex.matcher(requestBody);
		
		if (operandMatcher.find()) {
			String operandString = operandMatcher.group(1);
			operand = Integer.parseInt(operandString);
		} else {
			throw new IllegalArgumentException("Unable to locate operand");
		}
		
		return operand;
	}

	void writeWsdl(Writer writer) throws IOException {

		final String path = "/wsdl/math.wsdl";
		File file = FileUtils.toFile(getClass().getResource(path));
		String wsdl = FileUtils.readFileToString(file);
		writer.append(wsdl);
	}
	
	void writeMathResult(Writer writer, int mathResult) throws IOException {

		writer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mat=\"http://www.github.com/rabid-fish/math2/\">\n");
		writer.append("   <soapenv:Header/>\n");
		writer.append("   <soapenv:Body>\n");
		writer.append("      <mat:MathResponse>\n");
		writer.append("         <result>" + mathResult + "</result>\n");
		writer.append("      </mat:MathResponse>\n");
		writer.append("   </soapenv:Body>\n");
		writer.append("</soapenv:Envelope>\n");
	}

	void writeSoapError(Writer writer, String message) throws IOException {
		
		writer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mat=\"http://www.github.com/rabid-fish/math2/\">\n");
		writer.append("   <soapenv:Header/>\n");
		writer.append("   <soapenv:Body>\n");
		writer.append("       <soapenv:Fault>\n");
		writer.append("           <faultcode>1</faultcode>\n");
		writer.append("           <faultstring>" + message + "</faultstring>\n");
		writer.append("       </soapenv:Fault>\n");
		writer.append("   </soapenv:Body>\n");
		writer.append("</soapenv:Envelope>\n");
	}
}
