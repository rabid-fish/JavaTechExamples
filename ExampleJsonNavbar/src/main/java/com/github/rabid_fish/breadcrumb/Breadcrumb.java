package com.github.rabid_fish.breadcrumb;

import java.util.List;

public class Breadcrumb {
	
	private String name;
	private String title;
	private String url;
	private int depth;
	private List<Breadcrumb> children;
	
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
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public List<Breadcrumb> getChildren() {
		return children;
	}
	public void setChildren(List<Breadcrumb> children) {
		this.children = children;
	}

}
