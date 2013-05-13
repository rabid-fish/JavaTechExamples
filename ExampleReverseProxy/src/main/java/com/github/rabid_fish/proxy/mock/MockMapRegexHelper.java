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
import java.util.regex.Pattern;

public class MockMapRegexHelper extends MockMapHelper<Pattern> {

	private Map<Pattern, String> mapRegexToFilename = new HashMap<Pattern, String>();

	public MockMapRegexHelper(String resourcePath) {
		super(resourcePath);
		fillRegexMap(mockMapItems);
	}

	void fillRegexMap(MockMapItem[] mockMapItems) {

		for (MockMapItem item : mockMapItems) {
			String regex = item.getRegex();
			if (regex != null && regex.length() > 0) {
				Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
				mapRegexToFilename.put(pattern, item.getPath());
			}
		}
	}
	
	public String getMockBodyForBody(String body) throws Exception {
		
		Pattern key = getKeyForRequestBody(body);
		if (key == null) return null;
		
		String path = mapRegexToFilename.get(key);
		String mock = readFileFromResourcePath(path);
		return mock;
	}

	Pattern getKeyForRequestBody(String body) {

		Iterator<Pattern> keys = mapRegexToFilename.keySet().iterator();
		while (keys.hasNext()) {
			Pattern key = keys.next();
			if (key.matcher(body).find()) {
				return key;
			}
		}

		return null;
	}
}
