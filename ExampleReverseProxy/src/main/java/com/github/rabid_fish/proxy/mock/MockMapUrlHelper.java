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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Extends MockMapHelper to cause only two fields in the config file
 * to be 'seen': path and regex.
 */
public class MockMapUrlHelper extends MockMapHelper<String> {

	private Map<String, String> mapUrlToFilename = new HashMap<String, String>();

	public MockMapUrlHelper(String resourcePath) {
		super(resourcePath);
		fillUrlMap(mockMapItems);
	}

	void fillUrlMap(MockMapItem[] mockMapItems) {
		
		for (MockMapItem item : mockMapItems) {
			String url = item.getUrl();
			if (url != null && url.length() > 0) {
				mapUrlToFilename.put(url, item.getPath());
			}
		}
	}

	public String getMockBodyForUrl(String url) throws Exception {
		
		String key = getKeyForRequestUrl(url);
		if (key == null) return null;
		
		String path = mapUrlToFilename.get(key);
		String mock = readFileFromResourcePath(path);
		return mock;
	}

	String getKeyForRequestUrl(String url) {
		
		Iterator<String> keys = mapUrlToFilename.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			if (url.startsWith(key)) {
				return key;
			}
		}
		
		return null;
	}
}
