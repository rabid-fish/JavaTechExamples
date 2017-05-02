package com.github.rabidfish.webflow.ex1;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.rabidfish.HasId;

public class ContactSimple implements Serializable, HasId {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String lastName;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String phone;
	
	public ContactSimple() {
		super();
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
