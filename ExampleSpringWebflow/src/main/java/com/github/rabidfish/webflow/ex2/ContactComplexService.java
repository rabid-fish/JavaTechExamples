package com.github.rabidfish.webflow.ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class ContactComplexService {

	private static final int INVALID_INDEX = -1;
	
	AtomicLong contactSequence = new AtomicLong(0);
	List<ContactComplex> contacts = new ArrayList<>();
	
	public List<ContactComplex> list() {
		return contacts;
	}
	
	public ContactComplex findOrInitializeNewInstance(Long id) {
		
		int index = findObjectIndex(id);
		if (index == INVALID_INDEX) {
			ContactComplex contact = new ContactComplex();
			contact.setPhone(new PhoneComplex());
			return contact;
		}
		
		ContactComplex contact = contacts.get(index);
		return contact;
	}
	
	public boolean initializeTestData() {
		reset();
		save(createContact("Jane", "Doe", "515", "555-1212"));
		save(createContact("John", "Deer", "515", "555-1234"));
		return true;
	}

	public ContactComplex createContact(String firstName, String lastName, String areaCode, String number) {
		
		ContactComplex contact = new ContactComplex();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		
		PhoneComplex phone = new PhoneComplex();
		phone.setAreaCode(areaCode);
		phone.setNumber(number);
		contact.setPhone(phone);
		
		return contact;
	}
	
	public void reset() {
		contacts.clear();
	}
	
	public void save(ContactComplex contact) {
		
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
		contacts.sort((ContactComplex c1, ContactComplex c2) -> c1.getFirstName().compareTo(c2.getFirstName()));
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
