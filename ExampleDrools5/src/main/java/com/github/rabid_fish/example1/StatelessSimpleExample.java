package com.github.rabid_fish.example1;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;

public class StatelessSimpleExample extends ExampleParent {

	public void executeRules(KnowledgeBase kbase) {

		System.out.println("Instantiating a simple object and passing it into a stateless set of rules");

		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

		Number number = new Number("5");

		ksession.execute(number);
	}

}
