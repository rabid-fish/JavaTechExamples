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

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

public class MockMapHelperTest {

	class MockHelperMock extends MockMapHelper<String> {

		public MockHelperMock(String resourcePath) {
			super(resourcePath);
		}

	}

	MockMapHelper<String> helper;

	@Before
	public void setUp() {
		helper = new MockHelperMock(MockMapUrlHelperTest.TEST_JSON_PATH);
	}
	
	@Test
	public void construct() {
		assertNotNull(helper);
	}
	
	@Test(expected = RuntimeException.class)
	public void constructorWithBadPath() {
		helper = new MockHelperMock("not_a_json_file");
		assertNull(helper);
	}
	
	@Test
	public void readFileFromResourcePath() throws URISyntaxException, IOException {
		String contents = helper.readFileFromResourcePath(MockMapUrlHelperTest.TEST_JSON_PATH);
		assertNotNull(contents);
	}

}
