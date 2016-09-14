package com.github.rabid_fish.example3;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.rabid_fish.example3.ContactRepository3;
import com.github.rabid_fish.model.Contact;

public class ContactRepository3Test {

	ContactRepository3 repo;
	Contact contact;

	@Before
	public void setUp() {
		repo = new ContactRepository3();
		contact = new Contact(null, "Jane", "Doe");
	}
	
	@Test
	public void list_initializedToEmpty() {
		
		List<Contact> results = repo.list();
		
		assertEquals(0, results.size());
	}
	
	@Test
	public void list_addOneContact() {
		
		repo.save(contact);
		
		List<Contact> results = repo.list();
		
		assertEquals(1, results.size());
		assertEquals(contact, results.get(0));
	}
	
	@Test
	public void list_addAndRemoveContact() {
		
		repo.save(contact);
		repo.delete(contact);
		
		List<Contact> results = repo.list();
		
		assertEquals(0, results.size());
	}
	
}
