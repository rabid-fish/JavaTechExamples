package com.github.rabid_fish.example3;

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

import com.github.rabid_fish.example3.ContactConfiguration3;
import com.github.rabid_fish.example3.ContactRepository3;
import com.github.rabid_fish.model.Contact;

/*
 * When you have the class being run with SpringJUnit4ClassRunner and you
 * put @Transactional at the class level, then default behavior is to
 * rollback on each method.  If you do not want the method level rollback,
 * use a @Rollback(value = false) annotation on the methods you don't want 
 * to rollback.
 * 
 * http://stackoverflow.com/questions/24829823/interrogation-about-springjunit4classrunners-default-rollback-behavior-for-inte
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContactConfiguration3.class)
@Transactional(transactionManager = "transactionManager")
public class ContactRepository3Test {

	@Autowired
	ContactRepository3 repo;
	
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
