package com.github.rabid_fish.config;

import java.util.List;

public class QueueConfig {
	
	private String name;
	private int maxMessageCount;
	private List<QueueConfigColumn> columns;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<QueueConfigColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<QueueConfigColumn> columns) {
		this.columns = columns;
	}
	public int getMaxMessageCount() {
		return maxMessageCount;
	}
	public void setMaxMessageCount(int maxMessageCount) {
		this.maxMessageCount = maxMessageCount;
	}
}
