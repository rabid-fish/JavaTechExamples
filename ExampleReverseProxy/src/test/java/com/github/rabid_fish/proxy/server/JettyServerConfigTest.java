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

package com.github.rabid_fish.proxy.server;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rabid_fish.proxy.server.JettyServerConfig;
import com.github.rabid_fish.proxy.server.JettyServerType;

public class JettyServerConfigTest {

	public static final String SERVLET_PORT = "6000";
	public static final String BAD_PARAMETER = "bad";
	public static final String PROXY_MOCK_CONFIG_PATH  = "/html/example_url.json";
	public static final String PROXY_URL = "/";
	public static final String PROXY_TARGET_HOST = "localhost";
	public static final String PROXY_TARGET_PORT = "6001";

	JettyServerConfig config = new JettyServerConfig();
	
	// The following tests have nothing to assert.  Either an exception was thrown or it wasn't.
	
	@Test
	public void testValidateArgsForHtmlServlet() {
		String[] args = { JettyServerType.HTML.toString(), SERVLET_PORT };
		config.validateArgs(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateArgsForHtmlServletMissingArg() {
		String[] args = { JettyServerType.HTML.toString() };
		config.validateArgs(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateArgsForHtmlServletNonIntegerPort() {
		String[] args = { JettyServerType.HTML.toString(), BAD_PARAMETER };
		config.validateArgs(args);
	}
	
	@Test
	public void testValidateArgsForProxyServlet() {
		String[] args = { JettyServerType.PROXY_HTML.toString(), SERVLET_PORT, PROXY_MOCK_CONFIG_PATH, PROXY_URL, PROXY_TARGET_HOST, PROXY_TARGET_PORT };
		config.validateArgs(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateArgsForProxyServletMissingArg() {
		String[] args = { JettyServerType.PROXY_HTML.toString(), SERVLET_PORT, PROXY_MOCK_CONFIG_PATH, PROXY_URL, PROXY_TARGET_HOST };
		config.validateArgs(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateArgsForProxyServletBadProxyUrl() {
		String[] args = { JettyServerType.PROXY_HTML.toString(), SERVLET_PORT, PROXY_MOCK_CONFIG_PATH, BAD_PARAMETER, PROXY_TARGET_HOST, PROXY_TARGET_PORT };
		config.validateArgs(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateArgsForProxyServletBadMockConfigPath() {
		String[] args = { JettyServerType.PROXY_HTML.toString(), SERVLET_PORT, BAD_PARAMETER, PROXY_URL, PROXY_TARGET_HOST, PROXY_TARGET_PORT };
		config.validateArgs(args);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateArgsForProxyServletNonIntegerPort() {
		String[] args = { JettyServerType.PROXY_HTML.toString(), SERVLET_PORT, PROXY_MOCK_CONFIG_PATH, PROXY_URL, PROXY_TARGET_HOST, BAD_PARAMETER };
		config.validateArgs(args);
	}
	
	// Back to having asserts.
	
	@Test
	public void testInitializeForHtmlServlet() {
		String[] args = { JettyServerType.HTML.toString(), SERVLET_PORT };
		config.initialize(args);
		assertNotNull(config.getServletType());
		assertNotNull(config.getServletPort());
		assertNull(config.getProxyUrl());
		assertNull(config.getTargetHost());
		assertNull(config.getTargetPort());
	}
	
	@Test
	public void testInitializeForProxyServlet() {
		String[] args = { JettyServerType.PROXY_HTML.toString(), SERVLET_PORT, PROXY_MOCK_CONFIG_PATH, PROXY_URL, PROXY_TARGET_HOST, PROXY_TARGET_PORT };
		config.initialize(args);
		assertNotNull(config.getServletType());
		assertNotNull(config.getServletPort());
		assertNotNull(config.getProxyUrl());
		assertNotNull(config.getTargetHost());
		assertNotNull(config.getTargetPort());
	}
}
