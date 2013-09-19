package com.github.rabid_fish.config;

public class QueueConfigList extends Configuration {
	
	private String name;
	private int maxMessageCount;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxMessageCount() {
		return maxMessageCount;
	}
	public void setMaxMessageCount(int maxMessageCount) {
		this.maxMessageCount = maxMessageCount;
	}
}
