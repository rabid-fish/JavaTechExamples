package com.github.rabid_fish.example4;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.MathResult;

public class StatefulMultipleItemsExample extends ExampleParent {

	public static void main(String[] args) {
		System.out.println("Instantiating a multiple items and passing them into a stateful set of rules");
		new StatefulMultipleItemsExample().process("StatefulMultipleItemsExample.drl");
	}
	
	public void executeRules(KnowledgeBase kbase) {
		
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		MathResult result1 = new MathResult(new Double("0.1"));
		MathResult result2 = new MathResult(new Double("10"));
		MathResult result3 = new MathResult(new Double("4.999"));

		ksession.insert(result1);
		ksession.insert(result2);
		ksession.insert(result3);
		ksession.fireAllRules();
		
		System.out.println("Result 1 final value: " + result1.getValue());
		System.out.println("Result 2 final value: " + result2.getValue());
		System.out.println("Result 3 final value: " + result3.getValue());
		
		ksession.dispose();
	}
	
}
