package com.github.rabid_fish.xalan;

import static org.testng.AssertJUnit.assertNotNull;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XalanTransformerHelperTest {
	
	@Test
	public void test() throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		
		DOMSource xmlSource = getXmlSource();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		new XalanTransformerHelper().transform(xmlSource, outputStream);
		
		String result = outputStream.toString();
		System.out.println(result);
		assertNotNull(result);
	}
	
	
	DOMSource getXmlSource() throws ParserConfigurationException, SAXException, IOException, UnsupportedEncodingException {
		
		String xml = 
				  "<root>\n"
				+ "\t<contact>\n"
				+ "\t\t<firstName>Jane</firstName>\n"
				+ "\t\t<lastName>Doe</lastName>\n"
				+ "\t</contact>\n"
				+ "\t<contact>\n"
				+ "\t\t<firstName>John Deer</firstName>\n"
				+ "\t\t<lastName>Deer</lastName>\n"
				+ "\t</contact>\n"
				+ "</root>\n";
		
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setValidating(false);
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document xmlDocument = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		DOMSource xmlSource = new DOMSource(xmlDocument);
		
		return xmlSource;
	}

}
