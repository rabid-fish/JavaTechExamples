package com.github.rabid_fish.xalan;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XalanTransformerHelper {

    public class XsltURIResolver implements URIResolver {
        public Source resolve(String href, String base) throws TransformerException {
			try {
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(href);
				return new StreamSource(inputStream);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
        }
    }
    
	public void transform(Source xmlSource, OutputStream outputStream) throws TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = getTransformer();
	    Result result = new StreamResult(outputStream);
		transformer.transform(xmlSource, result);
	}

	Transformer getTransformer() throws TransformerFactoryConfigurationError, TransformerConfigurationException {
		
		TransformerFactory transformerFactory = new org.apache.xalan.xsltc.trax.TransformerFactoryImpl();
		transformerFactory.setAttribute("destination-directory", "target/classes");
		transformerFactory.setAttribute("generate-translet", Boolean.TRUE);
		transformerFactory.setAttribute("translet-name", "example");
		System.out.println(new File(".").getAbsolutePath());
		
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setURIResolver(new XsltURIResolver());
		InputStream fileStream = XalanTransformerHelper.class.getResourceAsStream("/example-parent.xsl");
		StreamSource streamSource = new StreamSource(fileStream); // or example.xsl?
		Templates templates = transformerFactory.newTemplates(streamSource);
		Transformer transformer = templates.newTransformer();
		
		return transformer;
	}

}
