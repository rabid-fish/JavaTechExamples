package com.github.rabid_fish;

public class Contact {

	private Integer contactId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	@Override
	public String toString() {
		return "contactId: " + contactId +
				", firstName: " + firstName + 
				", lastName: " + lastName + 
				", phoneNumber: " + phoneNumber;
	}
	
	public Integer getContactId() {
		return contactId;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
