package com.github.rabid_fish.example3;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class StatefulSimpleExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {
		
		System.out.println("Instantiating a simple object and passing it into a stateful set of rules");

		KieSession kieSession = kieContainer.newKieSession();

		Number number = new Number("0.1");

		kieSession.insert(number);
		kieSession.fireAllRules();
		
		System.out.println("Final number: " + number.getValue());

		kieSession.dispose();
	}
	
}
