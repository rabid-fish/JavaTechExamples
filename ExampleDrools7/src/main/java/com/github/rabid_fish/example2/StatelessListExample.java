package com.github.rabid_fish.example2;

import com.github.rabid_fish.ExampleParent;
import com.github.rabid_fish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Arrays;
import java.util.List;

public class StatelessListExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {

		System.out.println("Instantiating a list of objects and passing it into a stateless set of rules");

		StatelessKieSession ksession = kieContainer.newStatelessKieSession();

		Number[] numbers = {
			new Number("5"),
			new Number("15"),
			new Number("4.999"),
		};

		List<Number> list = Arrays.asList(numbers);

		System.out.println("Executing rules - you can only 'execute' once with a stateless session!");
		ksession.execute(list);
		System.out.println("Done executing rules");
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.println("Number " + i + ": " + numbers[i].getMessage());
		}
	}

}
