package com.github.rabidfish.webflow.ex4;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.rabidfish.webflow.ex2.ContactComplex;

public class CarSelectorCommand implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ContactComplex> contacts;
	private Map<Long, List<Long>> mapContactIdToCarIds;

	public CarSelectorCommand() {
		super();
	}

	public List<ContactComplex> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactComplex> contacts) {
		this.contacts = contacts;
	}

	public Map<Long, List<Long>> getMapContactIdToCarIds() {
		return mapContactIdToCarIds;
	}

	public void setMapContactIdToCarIds(Map<Long, List<Long>> mapContactIdToCarIds) {
		this.mapContactIdToCarIds = mapContactIdToCarIds;
	}
}
