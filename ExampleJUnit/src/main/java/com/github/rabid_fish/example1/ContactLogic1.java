package com.github.rabid_fish.example1;

import com.github.rabid_fish.model.Contact;

public class ContactLogic1 {

	public String getFullName(Contact contact) {
		return contact.getFirstName() + " " + contact.getLastName();
	}
	
}
