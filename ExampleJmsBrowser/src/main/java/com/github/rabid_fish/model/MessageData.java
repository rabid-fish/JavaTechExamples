package com.github.rabid_fish.model;

import java.util.ArrayList;
import java.util.List;

public class MessageData {

	private List<String> dataTitleList = new ArrayList<String>();
	private List<String> dataValueList = new ArrayList<String>();

	public List<String> getDataTitleList() {
		return dataTitleList;
	}
	public void setDataTitleList(List<String> dataTitleList) {
		this.dataTitleList = dataTitleList;
	}
	public List<String> getDataValueList() {
		return dataValueList;
	}
	public void setDataValueList(List<String> dataValueList) {
		this.dataValueList = dataValueList;
	}
}
