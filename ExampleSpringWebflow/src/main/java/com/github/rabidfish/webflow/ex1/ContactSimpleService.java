package com.github.rabidfish.webflow.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class ContactSimpleService {

	private static final int INVALID_INDEX = -1;
	
	AtomicLong contactSequence = new AtomicLong(0);
	List<ContactSimple> contacts = new ArrayList<>();
	
	public List<ContactSimple> list() {
		return contacts;
	}
	
	public ContactSimple findOrInitializeNewInstance(Long id) {
		
		int index = findObjectIndex(id);
		if (index == INVALID_INDEX) {
			ContactSimple contact = new ContactSimple();
			return contact;
		}
		
		ContactSimple contact = contacts.get(index);
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
	
	public void reset() {
		contacts.clear();
	}
	
	public void save(ContactSimple contact) {
		
		if (contact.getId() != null) {
			int index = findObjectIndex(contact.getId());
			if (index == INVALID_INDEX) {
				throw new RuntimeException("Unable to locate contact for update");
			}
			
			contacts.remove(index);
		} else {
			long id = contactSequence.incrementAndGet();
			contact.setId(id);
		}
		
		contacts.add(contact);
		contacts.sort((ContactSimple c1, ContactSimple c2) -> c1.getFirstName().compareTo(c2.getFirstName()));
	}

	int findObjectIndex(Long id) {
		
		int index = INVALID_INDEX;
		
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getId() == id) {
				index = i;
				break;
			}
		}
		
		return index;
	}
}
