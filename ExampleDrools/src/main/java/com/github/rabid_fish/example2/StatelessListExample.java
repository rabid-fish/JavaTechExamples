package com.github.rabid_fish.example2;

import java.util.Arrays;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.MathResult;

public class StatelessListExample extends ExampleParent {

	public static void main(String[] args) {
		System.out.println("Instantiating a list of objects and passing it into a stateless set of rules");
		new StatelessListExample().process("StatelessListExample.drl");
	}

	public void executeRules(KnowledgeBase kbase) {

		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

		MathResult[] results = {
			new MathResult(new Double("5.01111")),
			new MathResult(new Double("1.5")),
			new MathResult(new Double("5")),
		};
		List<MathResult> list = Arrays.asList(results);

		System.out.println("Executing rules");
		ksession.execute(list); // you can only 'execute' once!
		System.out.println("Done executing rules");
		
		for (int i = 0; i < results.length; i++) {
			System.out.println("Result " + i + ": " + results[i].getMessage());
		}
	}

}
