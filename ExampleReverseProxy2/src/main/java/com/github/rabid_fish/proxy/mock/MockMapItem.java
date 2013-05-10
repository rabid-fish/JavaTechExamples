package com.github.rabid_fish.proxy.mock;

public class MockMapItem {

	private String path;
	private String url;
	private String regex;
	
	public String getPath() {
		return path;
	}
	public void setPath(String filename) {
		this.path = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
}
