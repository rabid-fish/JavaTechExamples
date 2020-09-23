package com.github.rabid_fish.example6;

import java.util.Arrays;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;

public class DecisionTableManyConditionsAndActionsExample extends ExampleParent {

	public void executeRules(KnowledgeBase kbase) {
		
		System.out.println("Instantiating multiple items and passing them into a Decision Table with many conditions and actions");

		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		
		Number[] numbers = {
			new Number("3"),
			new Number("4"),
			new Number("5.25"),
			new Number("100.3"),
		};

		List<Number> list = Arrays.asList(numbers);

		System.out.println("Executing rules - you can only 'execute' once with a stateless session!");
		ksession.execute(list);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("Number " + (i + 1) + " message: " + list.get(i).getMessage());
		}
	}
	
}
