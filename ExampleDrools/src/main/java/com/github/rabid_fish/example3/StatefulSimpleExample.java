package com.github.rabid_fish.example3;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.MathResult;

public class StatefulSimpleExample extends ExampleParent {

	public static void main(String[] args) {
		System.out.println("Instantiating a simple object and passing it into a stateful set of rules");
		new StatefulSimpleExample().process("StatefulSimpleExample.drl");
	}
	
	public void executeRules(KnowledgeBase kbase) {
		
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		MathResult result = new MathResult(new Double("0.1"));
		ksession.insert(result);
		ksession.fireAllRules();
		
		System.out.println("Final value: " + result.getValue());
		
		ksession.dispose();
	}
	
}
