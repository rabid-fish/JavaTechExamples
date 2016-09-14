package com.github.rabid_fish.example2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.rabid_fish.model.Contact;

public class ContactRepository2 {

	public List<Contact> contacts = new ArrayList<>();
	
	public void save(Contact contact) {
		contacts.add(contact);
	}
	
	public void delete(Contact contact) {
		contacts.remove(contact);
	}
	
	public List<Contact> list() {
		ArrayList<Contact> list = new ArrayList<>(contacts);
		return Collections.unmodifiableList(list);
	}
}
