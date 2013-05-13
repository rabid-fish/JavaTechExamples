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

import java.net.URL;


public class JettyServerConfig {

	private JettyServerType servletType = null;
	private Integer servletPort = null;
	private String proxyMockConfigPath = null;
	private String proxyUrl = null;
	private String targetHost = null;
	private Integer targetPort = null;
	
	// empty constructor for junit
	JettyServerConfig() {
		super();
	}
	
	public JettyServerConfig(String[] args) {
		validateArgs(args);
		initialize(args);
	}
	
	void initialize(String[] args) {
		
		servletType = JettyServerType.fromString(args[0]);
		servletPort = Integer.parseInt(args[1]);
		
		if (isProxyServletType(servletType)) {
			proxyMockConfigPath = args[2];
			proxyUrl = args[3];
			targetHost = args[4];
			targetPort = Integer.parseInt(args[5]);
		}
	}
	
	void validateArgs(String[] args) throws IllegalArgumentException {
		
		if (args.length < 2) {
			throw new IllegalArgumentException("You must provide a valid SERVLET_TYPE and SERVLET_PORT for the server to start");
		}
		
		String servletName = args[0];
		JettyServerType servletType = JettyServerType.fromString(servletName);
		if (servletType == null) {
			throw new IllegalArgumentException("Servlet name '" + servletName + "' not recognized");
		}
		
		try {
			Integer.parseInt(args[1]);
		} catch (Exception e) {
			throw new IllegalArgumentException("SERVLET_PORT must be of type Integer");
		}

		if (isProxyServletType(servletType)) {
			
			if (args.length < 6) {
				throw new IllegalArgumentException("Missing one or more arguments");
			}
			
			String mockConfigPath = args[2];
			URL mockConfigResource = getClass().getResource(mockConfigPath);
			if (mockConfigResource == null) {
				throw new IllegalArgumentException("PROXY_MOCK_CONFIG_PATH must point to a valid json mock config file");
			}

			if (!args[2].startsWith("/")) {
				throw new IllegalArgumentException("PROXY_MOCK_CONFIG_PATH must start with a forward slash [/]");
			}
			
			if (!args[3].startsWith("/")) {
				throw new IllegalArgumentException("PROXY_URL must start with a forward slash [/]");
			}
			
			try {
				Integer.parseInt(args[5]);
			} catch (Exception e) {
				throw new IllegalArgumentException("PROXY_TARGET_PORT must be of type Integer");
			}
		}
	}

	private boolean isProxyServletType(JettyServerType servletType) {
		return servletType.equals(JettyServerType.PROXY_HTML) || servletType.equals(JettyServerType.PROXY_SOAP);
	}

	public JettyServerType getServletType() {
		return servletType;
	}

	public Integer getServletPort() {
		return servletPort;
	}

	public String getProxyMockConfigPath() {
		return proxyMockConfigPath;
	}

	public String getProxyUrl() {
		return proxyUrl;
	}

	public String getTargetHost() {
		return targetHost;
	}

	public Integer getTargetPort() {
		return targetPort;
	}

}
