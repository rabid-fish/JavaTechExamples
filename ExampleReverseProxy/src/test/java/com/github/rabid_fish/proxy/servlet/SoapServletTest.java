package com.github.rabid_fish.proxy.servlet;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

public class SoapServletTest {

	SoapServlet servlet = new SoapServlet();
	
	private String getRequestBody(String operand1, String operand2) {
		
		String requestBody = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mat=\"http://www.github.com/rabid-fish/math2/\">" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <mat:MathRequest>\n" +
			"         <operand1>" + operand1 + "</operand1>\n" +
			"         <operand2>" + operand2 + "</operand2>\n" +
			"      </mat:MathRequest>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>\n"
		;
		
		return requestBody;
	}
	
	@Test
	public void testGetMathResult() throws IOException {
		String requestBody = getRequestBody("2", "3");
		int result = servlet.getMathResult(requestBody);
		assertNotNull(result);
	}
	
	@Test
	public void testGetOperand() throws IOException {
		String requestBody = getRequestBody("2", "3");
		int operand = servlet.getOperand(requestBody, SoapServlet.REGEX_OPERAND_1);
		assertNotNull(operand);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetOperandWithMissingOperands() throws IOException {
		String requestBody = getRequestBody("", "");
		servlet.getOperand(requestBody, SoapServlet.REGEX_OPERAND_1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetOperandWithNonIntegerOperands() throws IOException {
		String requestBody = getRequestBody("a", "b");
		servlet.getOperand(requestBody, SoapServlet.REGEX_OPERAND_1);
	}
	
	@Test
	public void testWriteWsdl() throws IOException {
		StringWriter writer = new StringWriter();
		servlet.writeWsdl(writer);
		assertNotNull(writer.toString());
	}
	
	@Test
	public void testWriteMathResult() throws IOException {
		StringWriter writer = new StringWriter();
		servlet.writeMathResult(writer, 1);
		assertNotNull(writer.toString());
	}
	
	@Test
	public void testWriteSoapError() throws IOException {
		StringWriter writer = new StringWriter();
		servlet.writeSoapError(writer, "boom");
		assertNotNull(writer.toString());
	}
}
