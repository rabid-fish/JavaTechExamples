package com.github.rabid_fish.example4;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.rabid_fish.model.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContactConfiguration4.class)
@Transactional(transactionManager = "transactionManager")
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ContactRepository4Test {

	@Autowired
	ContactRepository4 repo;
	
	@Autowired
	private SessionFactory sessionFactory;

	Contact contact = new Contact(null, "Jane", "Doe", null);

	@Before
	public void setup() {
		sessionFactory.openSession();
	}
	
	@After
	public void teardown() {
		sessionFactory.getCurrentSession().close();
	}
	
	@Test
	public void list_initializedToTwo() {
		
		List<Contact> results = repo.list();
		
		assertEquals(2, results.size());
	}
	
	@Test
	public void list_addOneContact() {
		
		repo.save(contact);
		
		List<Contact> results = repo.list();
		
		assertEquals(3, results.size());
		assertEquals(contact, results.get(2));
	}
	
	@Test
	public void list_addAndRemoveContact() {
		
		repo.save(contact);
		repo.delete(contact);
		
		List<Contact> results = repo.list();
		
		assertEquals(2, results.size());
	}
}
