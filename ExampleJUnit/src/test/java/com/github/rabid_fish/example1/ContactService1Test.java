package com.github.rabid_fish.example1;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rabid_fish.model.Contact;

public class ContactService1Test {

	@Test
	public void getFullName() {
		
		Contact contact = new Contact();
		contact.setFirstName("Jane");
		contact.setLastName("Doe");
		
		ContactService1 service = new ContactService1();
		String fullName = service.getFullName(contact);
		
		assertEquals("Jane Doe", fullName);
	}
	
	@Test
	public void getFullName_FromNulls() {
		
		Contact contact = new Contact();
		
		ContactService1 service = new ContactService1();
		String fullName = service.getFullName(contact);
		
		assertEquals("null null", fullName);
	}
}
