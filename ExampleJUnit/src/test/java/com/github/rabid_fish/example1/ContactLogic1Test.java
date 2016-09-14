package com.github.rabid_fish.example1;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rabid_fish.model.Contact;

public class ContactLogic1Test {

	@Test
	public void getFullName() {
		
		Contact contact = new Contact();
		contact.setFirstName("Jane");
		contact.setLastName("Doe");
		
		ContactLogic1 logic = new ContactLogic1();
		String fullName = logic.getFullName(contact);
		
		assertEquals("Jane Doe", fullName);
	}
	
	@Test
	public void getFullName_FromNulls() {
		
		Contact contact = new Contact();
		
		ContactLogic1 logic = new ContactLogic1();
		String fullName = logic.getFullName(contact);
		
		assertEquals("null null", fullName);
	}
}
