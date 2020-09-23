package com.github.rabid_fish.example5;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class DecisionTableSimpleExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {
		
		System.out.println("Instantiating a simple object and passing it into a Decision Table");

		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
		
		Number number = new Number("3");
		kieSession.execute(number);

		System.out.println("Number 1 message: " + number.getMessage());
	}
	
}
