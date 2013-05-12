package com.github.rabid_fish.proxy.server;

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