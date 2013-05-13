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

/**
 * A list of servlet types that JettyServer may be started with.
 */
enum JettyServerType {
	PROXY_HTML("proxy_html"), 
	PROXY_SOAP("proxy_soap"), 
	FORM("form"), 
	;

	private String name;

	private JettyServerType(String name) {
		this.name = name;
	}

	public static JettyServerType fromString(String s) {

		if (s != null) {
			for (JettyServerType j : JettyServerType.values()) {
				if (s.equals(j.name)) {
					return j;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static String getPrettyPrintList() {
		
		String servletNames = "";
		for (JettyServerType servletType : values()) {
			if (servletNames.length() == 0) {
				servletNames += servletType.toString();
			} else {
				servletNames += ", " + servletType.toString();
			}
		}
		
		return servletNames;
	}
}