package com.github.rabid_fish.example9;

import com.github.rabid_fish.DroolsUtil;
import com.github.rabid_fish.Number;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Component;

@Component("camelExampleBean")
public class CamelStatelessSimpleExample {
	
	private KieContainer kieContainer = DroolsUtil.createKieContainerForDrl("/com/github/rabid_fish/example1/StatelessSimpleExample.drl", getClass());
	
	public void executeRules(Object object) {
		
		Number number = (Number) object;
		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
		kieSession.execute(number);
	}

}
