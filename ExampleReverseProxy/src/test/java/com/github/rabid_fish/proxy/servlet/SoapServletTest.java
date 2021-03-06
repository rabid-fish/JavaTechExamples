package com.github.rabid_fish.proxy.servlet;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.github.rabid_fish.proxy.mock.MockMapRegexHelperTest;

public class SoapServletTest {

	SoapServlet servlet = new SoapServlet();
	
	@Test
	public void testGetMathResult() throws IOException {
		String requestBody = MockMapRegexHelperTest.getRequestBody("2", "3");
		int result = servlet.getMathResult(requestBody);
		assertNotNull(result);
	}
	
	@Test
	public void testGetOperand() throws IOException {
		String requestBody = MockMapRegexHelperTest.getRequestBody("2", "3");
		int operand = servlet.getOperand(requestBody, SoapServlet.REGEX_OPERAND_1);
		assertNotNull(operand);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetOperandWithMissingOperands() throws IOException {
		String requestBody = MockMapRegexHelperTest.getRequestBody("", "");
		servlet.getOperand(requestBody, SoapServlet.REGEX_OPERAND_1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetOperandWithNonIntegerOperands() throws IOException {
		String requestBody = MockMapRegexHelperTest.getRequestBody("a", "b");
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
