package com.github.rabid_fish;

import java.io.Serializable;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private Long age;

	public Contact() {
		super();
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
}
