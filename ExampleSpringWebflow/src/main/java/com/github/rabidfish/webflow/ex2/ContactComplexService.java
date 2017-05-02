package com.github.rabidfish.webflow.ex2;

import org.springframework.stereotype.Service;

import com.github.rabidfish.AbstractRepository;

@Service
public class ContactComplexService extends AbstractRepository<ContactComplex> {

	public ContactComplex findOrInitializeNewInstance(Long id) {
		
		int index = findObjectIndex(id);
		if (index == INVALID_INDEX) {
			ContactComplex contact = new ContactComplex();
			contact.setPhone(new PhoneComplex());
			return contact;
		}
		
		ContactComplex contact = list.get(index);
		return contact;
	}
	
	public boolean initializeTestData() {
		reset();
		save(createContact("Jane", "Doe", "515", "555-1212"));
		save(createContact("John", "Deer", "515", "555-1234"));
		save(createContact("John Jr", "Deer", "515", "555-4321"));
		save(createContact("Joe", "Public", "123", "456-7890"));
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
	
	public void save(ContactComplex contact) {
		super.save(contact);
		list.sort((ContactComplex c1, ContactComplex c2) -> c1.getFirstName().compareTo(c2.getFirstName()));
	}

}
