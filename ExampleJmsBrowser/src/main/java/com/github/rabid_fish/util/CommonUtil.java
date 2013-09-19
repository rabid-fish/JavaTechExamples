package com.github.rabid_fish.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.github.rabid_fish.model.MessageData;

public final class CommonUtil {

	public static final String QUEUE_CONFIG_VIEW_JSON = "/json/queueConfigView.json";
	public static final String QUEUE_CONFIG_LIST_JSON = "/json/queueConfigList.json";
	public static final String QUEUE_LOAD_JSON = "/json/queueLoad.json";

	private CommonUtil() { }
	
	public static void prettifyMessageBody(MessageData messageData) {
		messageData.setBody(prettyPrintXml(messageData.getBody()));
	}
	
	public static String prettyPrintXml(String xmlSource) {

		String xmlResult = null;
		
		try {
			xmlResult = prettyPrintXmlThrowException(xmlSource);
		} catch (TransformerException e) {
			xmlResult = xmlSource;
		}
		
		return xmlResult;
	}

	public static String prettyPrintXmlThrowException(String xmlSource) throws TransformerException {
		
		// Transform code borrowed from:
		// http://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java
		StringReader stringReader = new StringReader(xmlSource);
		Source xmlInput = new StreamSource(stringReader);
		
		StringWriter stringWriter = new StringWriter();
		StreamResult xmlOutput = new StreamResult(stringWriter);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", 2);
		
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(xmlInput, xmlOutput);
		
		return stringWriter.toString();
	}

	
}
