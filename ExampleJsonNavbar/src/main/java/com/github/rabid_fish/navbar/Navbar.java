package com.github.rabid_fish.navbar;

import java.util.List;

public class Navbar {
	
	private String name;
	private String title;
	private String url;
	private List<Navbar> children;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Navbar> getChildren() {
		return children;
	}
	public void setChildren(List<Navbar> children) {
		this.children = children;
	}

}
