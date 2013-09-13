package com.github.rabid_fish.config;

import java.util.List;

public class ConfigQueue {
	
	private String name;
	private int maxMessageCount;
	private List<ConfigColumn> columns;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ConfigColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<ConfigColumn> columns) {
		this.columns = columns;
	}
	public int getMaxMessageCount() {
		return maxMessageCount;
	}
	public void setMaxMessageCount(int maxMessageCount) {
		this.maxMessageCount = maxMessageCount;
	}
}
