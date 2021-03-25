package com.github.rabid_fish.example1;

import com.github.rabid_fish.*;
import com.github.rabid_fish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.List;

public class StatelessSimpleExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {

		System.out.println("Instantiating a simple object and passing it into a stateless set of rules");

		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

		Contact contact = new Contact();
		contact.setFirstName("John");
		contact.setLastName("Doe");
		contact.setAge(64L);

		Make make = new Make();
		make.setName("the make");

		Model model = new Model();
		model.setName("the model");

		List<Object> list = new ArrayList();
		list.add(contact);
		list.add(make);
		list.add(model);

		kieSession.execute(list);
	}

}
