package com.github.rabidfish.example1;

import com.github.rabidfish.ExampleParent;
import com.github.rabidfish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class StatelessSimpleExample extends ExampleParent {

	public void executeRules(KieContainer kieContainer) {

		System.out.println("Instantiating a simple object and passing it into a stateless set of rules");

		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

		Number number = new Number("5");

		kieSession.execute(number);
	}

}
