package com.github.rabid_fish.example6;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Arrays;
import java.util.List;

public class DecisionTableManyConditionsAndActionsExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {
		
		System.out.println("Instantiating multiple items and passing them into a Decision Table with many conditions and actions");

		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
		
		Number[] numbers = {
			new Number("3"),
			new Number("4"),
			new Number("5.25"),
			new Number("100.3"),
		};

		List<Number> list = Arrays.asList(numbers);

		System.out.println("Executing rules - you can only 'execute' once with a stateless session!");
		kieSession.execute(list);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("Number " + (i + 1) + " message: " + list.get(i).getMessage());
		}
	}
	
}
