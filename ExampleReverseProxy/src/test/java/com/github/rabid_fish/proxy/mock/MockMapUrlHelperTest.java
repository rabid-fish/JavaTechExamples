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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class MockMapUrlHelperTest {

	public static final String TEST_JSON_PATH = "/html/example_url.json";
	public static final String TEST_URL = "/form/example1";
	public static final String TEST_BAD_URL = "/form/bad";
	
	MockMapUrlHelper helper;

	@Before
	public void setUp() {
		helper = new MockMapUrlHelper(TEST_JSON_PATH);
	}
	
	@Test
	public void getKeyForRequestUrl() {
		String key = helper.getKeyForRequestUrl(TEST_URL);
		assertNotNull(key);
	}
	
	@Test
	public void getKeyForRequestUrlWithBadUrl() {
		String key = helper.getKeyForRequestUrl(TEST_BAD_URL);
		assertNull(key);
	}

	@Test
	public void getBodyForKey() throws Exception {
		String body = helper.getMockBodyForUrl(TEST_URL);
		assertNotNull(body);
	}

}
