package com.github.rabid_fish.config;

public class QueueConfigColumn {
	
	private String title;
	private String property;
	private String regex;
	
	public QueueConfigColumn() {
		super();
	}
	
	public QueueConfigColumn(String title, String property, String regex) {
		super();
		this.title = title;
		this.property = property;
		this.regex = regex;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
}
