package com.github.rabidfish.webflow.ex4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.AssertTrue;

import com.github.rabidfish.webflow.ex2.ContactComplex;

public class CarSelectorCommand implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ContactComplex> contacts = new ArrayList<>();
	private List<Car> cars = new ArrayList<>();
	private Map<Long, List<Long>> mapContactIdToCarIds = new HashMap<>();

	public CarSelectorCommand() {
		super();
	}
	
	@AssertTrue(message = "There must be at least one car chosen per contact")
	public boolean isVerifyOneCarPerContact() {
		
		for (Long contact : mapContactIdToCarIds.keySet()) {
			if (mapContactIdToCarIds.get(contact) == null || mapContactIdToCarIds.get(contact).size() == 0) {
				return false;
			}
		}
		
		return true;
	}

	public List<ContactComplex> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactComplex> contacts) {
		this.contacts = contacts;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Map<Long, List<Long>> getMapContactIdToCarIds() {
		return mapContactIdToCarIds;
	}

	public void setMapContactIdToCarIds(Map<Long, List<Long>> mapContactIdToCarIds) {
		this.mapContactIdToCarIds = mapContactIdToCarIds;
	}
}
