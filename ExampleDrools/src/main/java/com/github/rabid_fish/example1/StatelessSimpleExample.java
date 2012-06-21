package com.github.rabid_fish.example1;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.MathResult;

public class StatelessSimpleExample extends ExampleParent {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Instantiating a simple object and passing it into a stateless set of rules");
		new StatelessSimpleExample().process("ExampleParent.drl");
	}

	public void executeRules(KnowledgeBase kbase) {

		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

		System.out.println("Executing rules");
		ksession.execute(new MathResult(new Double("5.01111")));
		System.out.println("Done executing rules");
	}

}
