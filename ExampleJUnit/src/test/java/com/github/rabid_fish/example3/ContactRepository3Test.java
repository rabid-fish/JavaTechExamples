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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContactConfiguration3.class)
@Transactional(transactionManager = "transactionManager")
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
		
	}
	
	@Test
	public void list_addOneContact() {
		
	}
	
	@Test
	public void list_addAndRemoveContact() {
		
	}
}
