package com.github.rabid_fish.example3;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
	
	Contact contact = new Contact(null, "Jane", "Doe", null);

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
