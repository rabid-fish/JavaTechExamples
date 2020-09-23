package com.github.rabid_fish.example1;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class StatelessSimpleExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {

		System.out.println("Instantiating a simple object and passing it into a stateless set of rules");

		StatelessKieSession kSession = kieContainer.newStatelessKieSession();

		Number number = new Number("5");

		kSession.execute(number);
	}

}
