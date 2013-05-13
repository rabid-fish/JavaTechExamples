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
