package com.github.rabid_fish.proxy.mock;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class MockRegexHelperTest {

	public static final String TEST_JSON_PATH = "/soap/example_regex.json";
	public static final String TEST_BODY = "blah\nexample1\nblah\n";
	public static final String TEST_BAD_BODY = "blah\n blah nothing blah\n blah\n";
	
	MockMapRegexHelper helper;

	@Before
	public void setUp() {
		helper = new MockMapRegexHelper(TEST_JSON_PATH);
	}
	
	@Test
	public void getKeyForRequestBody() {
		Pattern key = helper.getKeyForRequestBody(TEST_BODY);
		assertNotNull(key);
	}
	
	@Test
	public void getKeyForRequestBodyWithBadUrl() {
		Pattern key = helper.getKeyForRequestBody(TEST_BAD_BODY);
		assertNull(key);
	}

	@Test
	public void getBodyForKey() throws Exception {
		String body = helper.getMockBodyForBody(TEST_BODY);
		assertNotNull(body);
	}
}
