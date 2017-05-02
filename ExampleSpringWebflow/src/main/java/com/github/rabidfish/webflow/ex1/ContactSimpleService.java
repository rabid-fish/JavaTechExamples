package com.github.rabidfish.webflow.ex1;

import org.springframework.stereotype.Service;

import com.github.rabidfish.AbstractRepository;

@Service
public class ContactSimpleService extends AbstractRepository<ContactSimple> {

	private static final int INVALID_INDEX = -1;
	
	public ContactSimple findOrInitializeNewInstance(Long id) {
		
		int index = findObjectIndex(id);
		if (index == INVALID_INDEX) {
			ContactSimple contact = new ContactSimple();
			return contact;
		}
		
		ContactSimple contact = list.get(index);
		return contact;
	}
	
	public boolean initializeTestData() {
		reset();
		save(createContact("Jane", "Doe", "515-555-1212"));
		save(createContact("John", "Deer", "515-555-1234"));
		return true;
	}

	public ContactSimple createContact(String firstName, String lastName, String phone) {
		ContactSimple contact = new ContactSimple();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPhone(phone);
		return contact;
	}
	
	public void save(ContactSimple contact) {
		super.save(contact);
		list.sort((ContactSimple c1, ContactSimple c2) -> c1.getFirstName().compareTo(c2.getFirstName()));
	}
}
