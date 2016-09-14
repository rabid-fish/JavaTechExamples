package com.github.rabid_fish.example2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.rabid_fish.model.Contact;

public class ContactService2 {

	public List<Contact> contacts = new ArrayList<>();
	
	public String getFullName(Contact contact) {
		return contact.getFirstName() + " " + contact.getLastName();
	}

	public void add(Contact contact) {
		contacts.add(contact);
	}
	
	public void remove(Contact contact) {
		contacts.remove(contact);
	}
	
	public List<Contact> list() {
		ArrayList<Contact> list = new ArrayList<>(contacts);
		return Collections.unmodifiableList(list);
	}
}
