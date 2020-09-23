package com.github.rabidfish.example4;

import com.github.rabidfish.ExampleParent;
import com.github.rabidfish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class StatefulMultipleItemsExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {
		
		System.out.println("Instantiating multiple items and passing them into a stateful set of rules");

		KieSession kieSession = kieContainer.newKieSession();
		
		Number number1 = new Number("0.1");
		Number number2 = new Number("5.2");
		Number number3 = new Number("4.3");

		kieSession.insert(number1);
		kieSession.insert(number2);
		kieSession.insert(number3);
		kieSession.fireAllRules();
		
		System.out.println("Number 1 final value: " + number1.getValue());
		System.out.println("Number 2 final value: " + number2.getValue());
		System.out.println("Number 3 final value: " + number3.getValue());
		
		kieSession.dispose();
	}
	
}
