package com.github.rabid_fish.example2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.rabid_fish.model.Contact;

public class ContactService2Test {

	// Note: start with coding these properties in the method, then move them out
	ContactService2 service;
	Contact contact;

	@Before
	public void setUp() {
		service = new ContactService2();
		contact = new Contact("Jane", "Doe");
	}
	
	@Test
	public void list_initializedToEmpty() {
		
		List<Contact> results = service.list();
		
		assertEquals(0, results.size());
	}
	
	@Test
	public void list_addOneContact() {
		
		service.add(contact);
		
		List<Contact> results = service.list();
		
		assertEquals(1, results.size());
		assertEquals(contact, results.get(0));
	}
	
	@Test
	public void list_addAndRemoveContact() {
		
		service.add(contact);
		service.remove(contact);
		
		List<Contact> results = service.list();
		
		assertEquals(0, results.size());
	}
	
}
