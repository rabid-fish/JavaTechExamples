package com.github.rabid_fish.util;

import static org.junit.Assert.*;

import javax.xml.transform.TransformerException;

import org.junit.Test;

import com.github.rabid_fish.model.MessageData;

public class CommonUtilTest {
	
	private static final String STRING_XML = "<root><child>test</child></root>";
	private static final String STRING_NOTXML = "test";
	
	@Test
	public void testPrettifyMessageBody() {
		
		MessageData messageData = new MessageData();
		messageData.setBody(STRING_XML);
		CommonUtil.prettifyMessageBody(messageData);
		
		String result = messageData.getBody(); 
		assertTrue(result.contains(" <child>"));
		assertTrue(result.contains(System.getProperty("line.separator")));
	}
	
	@Test
	public void testPrettyPrintXml() {
		
		String result = CommonUtil.prettyPrintXml(STRING_XML);

		assertTrue(result.contains(" <child>"));
		assertTrue(result.contains(System.getProperty("line.separator")));
	}

	@Test
	public void testPrettyPrintXmlWithNotXml() {
		
		String result = CommonUtil.prettyPrintXml(STRING_NOTXML);
		assertEquals(result, STRING_NOTXML);
	}

	@Test(expected = TransformerException.class)
	public void testPrettyPrintXmlThrowExceptionThrowsException() throws TransformerException {
		
		CommonUtil.prettyPrintXmlThrowException("");
	}

	@Test
	public void testPrettyPrintXmlThrowException() throws TransformerException {
		
		String result = CommonUtil.prettyPrintXmlThrowException(STRING_XML);
		
		assertTrue(result.contains(" <child>"));
		assertTrue(result.contains(System.getProperty("line.separator")));
	}

}
