package com.github.rabid_fish.example4;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.rabid_fish.model.Contact;

@Repository
public class ContactRepository4 {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Contact contact) {
		Session session = sessionFactory.getCurrentSession();
		session.save(contact);
	}

	public void delete(Contact contact) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(contact);
	}

	public Contact get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		Contact contact = (Contact) session.byId(Contact.class).load(id);
		return contact;
	}

	public List<Contact> list() {
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = session.createQuery("from Contact").list();
		
		return contacts;
	}

}
