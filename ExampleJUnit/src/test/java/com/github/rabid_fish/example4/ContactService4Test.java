package com.github.rabid_fish.example4;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.rabid_fish.model.Contact;

@RunWith(EasyMockRunner.class)
public class ContactService4Test {

	@TestSubject
	private ContactService4 service = new ContactService4();
	
	@Mock
	ContactRepository4 repository;
	
	@Test
	public void updateContactFirstName() {
		
		Contact contact = new Contact();
		contact.setId(1l);
		contact.setFirstName("Jane");
		contact.setLastName("Doe");
		
		EasyMock.expect(repository.get(1l)).andReturn(contact).once();
		repository.save(EasyMock.anyObject());
		EasyMock.replay(repository);
		
		service.updateContactFirstName(1l, "John");
		
		assertEquals("John", contact.getFirstName());
		assertEquals("Doe", contact.getLastName());
	}
}
