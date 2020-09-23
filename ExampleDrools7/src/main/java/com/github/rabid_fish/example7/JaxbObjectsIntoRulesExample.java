package com.github.rabid_fish.example7;

import com.github.rabid_fish.ExampleParent;
import https.github_com.rabid_fish.ContactList;
import https.github_com.rabid_fish.ContactType;
import https.github_com.rabid_fish.ObjectFactory;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.math.BigInteger;

public class JaxbObjectsIntoRulesExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {

		System.out.println("Instantiating jaxb objects and passing them into some rules");

		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

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
		
		kieSession.execute(contacts.getContact());
		
		for (ContactType contact : contacts.getContact()) {
			System.out.println(contact.getName() + " is in risk group '" + contact.getRisk() + "'");
		}
	}

	public static ContactType createContact(ObjectFactory objectFactory, String name, String age, String gender) {
		
		ContactType contact = objectFactory.createContactType();
		contact.setName(name);
		contact.setAge(new BigInteger(age));
		contact.setGender(gender);
		
		return contact;
	}

}
