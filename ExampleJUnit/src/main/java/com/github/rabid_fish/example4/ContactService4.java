package com.github.rabid_fish.example4;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.rabid_fish.model.Contact;

public class ContactService4 {

	@Autowired
	ContactRepository4 repository;
	
	public void updateContactFirstName(Long id, String firstName) {

		Contact contact = repository.get(id);
		contact.setFirstName(firstName);
		
		repository.save(contact);
	}
	
}
