package com.github.rabid_fish.json.jira.timesheet;

import java.util.List;

public class Worklog {

	private String key;
	private String summary;
	
	private List<WorklogEntry> entries;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<WorklogEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<WorklogEntry> entries) {
		this.entries = entries;
	} 
}
