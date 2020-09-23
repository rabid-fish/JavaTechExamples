package com.github.rabid_fish.example3;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;

public class StatefulSimpleExample extends ExampleParent {

	public void executeRules(KnowledgeBase kbase) {
		
		System.out.println("Instantiating a simple object and passing it into a stateful set of rules");

		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		Number number = new Number("0.1");
		
		ksession.insert(number);
		ksession.fireAllRules();
		
		System.out.println("Final number: " + number.getValue());
		
		ksession.dispose();
	}
	
}
