package com.github.rabid_fish.example4;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.rabid_fish.example4.ContactConfiguration4;
import com.github.rabid_fish.example4.ContactRepository4;
import com.github.rabid_fish.model.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContactConfiguration4.class)
public class ContactRepository4Test {

	@Autowired
	ContactRepository4 repo;
	
	@Test
	@Transactional
	public void test() {
		List<Contact> contacts = repo.list();
		assertEquals(2, contacts.size());
	}
}
