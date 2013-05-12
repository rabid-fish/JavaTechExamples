package com.github.rabid_fish.proxy.mock;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * The developer
 */
public abstract class MockMapHelper<A> {
	
	protected MockMapItem[] mockMapItems;

	public MockMapHelper(String resourcePath) {
		setMockMapItems(resourcePath);
	}

	protected void setMockMapItems(String resourcePath) {

		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		
		if (stream == null) {
			throw new RuntimeException("Unable to locate json file at path " + resourcePath);
		}
		
		try {
			mockMapItems = mapper.readValue(stream, MockMapItem[].class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected String readFileFromResourcePath(String filename) throws URISyntaxException, IOException {
		
		URL resource = getClass().getResource(filename);
		File file = new File(resource.toURI());
		String body = FileUtils.readFileToString(file);
		return body;
	}
	
}
