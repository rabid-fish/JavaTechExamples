package com.github.rabid_fish.model;

import java.util.ArrayList;
import java.util.List;

public class MessageData {

	private String messageId;
	private List<String> dataTitleList = new ArrayList<String>();
	private List<String> dataValueList = new ArrayList<String>();
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
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
