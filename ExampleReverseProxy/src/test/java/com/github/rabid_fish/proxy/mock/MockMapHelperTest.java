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
