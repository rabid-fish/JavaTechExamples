package com.github.rabid_fish.example8;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;
import com.github.rabid_fish.example7.JaxbObjectsIntoRulesExample;
import https.github_com.rabid_fish.ContactType;
import https.github_com.rabid_fish.ObjectFactory;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DifferentObjectsIntoRulesExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {

		System.out.println("Instantiating jaxb objects and passing them into some rules");

		System.out.println(new Date());
		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
		System.out.println(new Date());

		ObjectFactory objectFactory = new ObjectFactory();
		ContactType contact = JaxbObjectsIntoRulesExample.createContact(
				objectFactory, "Jane Doe", "19", "female");

		Number number = new Number("3");

		List<Object> list = new ArrayList<Object>();
		list.add(contact);
		list.add(number);

		kieSession.execute(list);

		System.out.println(contact.getName() + " is in risk group '" + contact.getRisk() + "'");
	}

}
