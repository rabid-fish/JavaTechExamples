package com.github.rabid_fish.example5;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;

public class DecisionTableSimpleExample extends ExampleParent {

	public void executeRules(KnowledgeBase kbase) {
		
		System.out.println("Instantiating a simple object and passing it into a Decision Table");

		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		
		Number number = new Number("3");
		ksession.execute(number);
		
		System.out.println("Number 1 message: " + number.getMessage());
	}
	
}
