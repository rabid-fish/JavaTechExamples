package com.github.rabid_fish.example4;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;

public class StatefulMultipleItemsExample extends ExampleParent {

	public void executeRules(KnowledgeBase kbase) {
		
		System.out.println("Instantiating multiple items and passing them into a stateful set of rules");

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		Number number1 = new Number("0.1");
		Number number2 = new Number("5.2");
		Number number3 = new Number("4.3");

		ksession.insert(number1);
		ksession.insert(number2);
		ksession.insert(number3);
		ksession.fireAllRules();
		
		System.out.println("Number 1 final value: " + number1.getValue());
		System.out.println("Number 2 final value: " + number2.getValue());
		System.out.println("Number 3 final value: " + number3.getValue());
		
		ksession.dispose();
	}
	
}
