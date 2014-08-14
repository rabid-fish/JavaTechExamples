package com.github.rabid_fish;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactServiceTest {

	private static ContactService contactService;

	@BeforeClass
	public static void setup() {
		contactService = new ContactService();
	}

	@AfterClass
	public static void teardown() {
		contactService = null;
	}

	@Test
	public void test01CreateContactTable() {
		contactService.createContactTable();
	}

	@Test
	public void test02InsertContact() {

		Contact contact = new Contact();
		contact.setFirstName("John");
		contact.setLastName("Deer");
		contact.setPhoneNumber("1234567");

		contactService.insertContact(contact);
		Assert.assertTrue(contact.getContactId() != 0);
	}

	@Test
	public void test03GetContacts() {

		List<Contact> contacts = contactService.getContacts();
		Assert.assertTrue(contacts.size() > 0);
		System.out.println(contacts.get(0));
	}

}
