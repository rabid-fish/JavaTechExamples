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

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class MockMapRegexHelperTest {

	public static final String TEST_JSON_PATH = "/soap/example_regex.json";
	public static final String TEST_BODY = getRequestBody("99999", "1");
	public static final String TEST_BAD_BODY = getRequestBody("", "");

	MockMapRegexHelper helper;

	public static String getRequestBody(String operand1, String operand2) {
		
		String requestBody = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mat=\"http://www.github.com/rabid-fish/math2/\">" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <mat:MathRequest>\n" +
			"         <operand1>" + operand1 + "</operand1>\n" +
			"         <operand2>" + operand2 + "</operand2>\n" +
			"      </mat:MathRequest>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>\n"
		;
		
		return requestBody;
	}
	
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
