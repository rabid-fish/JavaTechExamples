package com.github.rabid_fish.example7;

import java.math.BigInteger;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import com.github.rabid_fish.ExampleParent;

import rabid_fish.github.com.exampledroolsandjaxb.ContactList;
import rabid_fish.github.com.exampledroolsandjaxb.ContactType;
import rabid_fish.github.com.exampledroolsandjaxb.ObjectFactory;

public class JaxbObjectsIntoRules extends ExampleParent {

	public void executeRules(KnowledgeBase kbase) {

		System.out.println("Instantiating jaxb objects and passing them into some rules");

		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

		ObjectFactory objectFactory = new ObjectFactory();
		ContactList contacts = objectFactory.createContactList();
		
		contacts.getContact().add(createContact(objectFactory, "John Deer", "16", "male"));
		contacts.getContact().add(createContact(objectFactory, "John Smith", "19", "male"));
		contacts.getContact().add(createContact(objectFactory, "John Johnson", "25", "male"));
		contacts.getContact().add(createContact(objectFactory, "John Jones", "40", "male"));
		
		contacts.getContact().add(createContact(objectFactory, "Jane Deer", "16", "female"));
		contacts.getContact().add(createContact(objectFactory, "Jane Smith", "19", "female"));
		contacts.getContact().add(createContact(objectFactory, "Jane Johnson", "25", "female"));
		contacts.getContact().add(createContact(objectFactory, "Jane Jones", "40", "female"));
		
		ksession.execute(contacts.getContact());
		
		for (ContactType contact : contacts.getContact()) {
			System.out.println(contact.getName() + " is in risk group '" + contact.getRisk() + "'");
		}
	}

	private ContactType createContact(ObjectFactory objectFactory, String name, String age, String gender) {
		
		ContactType contact = objectFactory.createContactType();
		contact.setName(name);
		contact.setAge(new BigInteger(age));
		contact.setGender(gender);
		
		return contact;
	}

}
