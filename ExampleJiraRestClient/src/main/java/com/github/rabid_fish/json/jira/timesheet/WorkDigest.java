package com.github.rabid_fish.json.jira.timesheet;

import java.util.List;

public class WorkDigest {

	private List<Worklog> worklog;
	private String startDate;
	private String endDate;

	public List<Worklog> getWorklog() {
		return worklog;
	}
	public void setWorklog(List<Worklog> worklog) {
		this.worklog = worklog;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
