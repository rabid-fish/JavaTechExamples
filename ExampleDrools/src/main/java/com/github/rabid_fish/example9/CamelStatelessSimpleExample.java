package com.github.rabid_fish.example9;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.stereotype.Component;

import com.github.rabid_fish.DroolsUtil;
import com.github.rabid_fish.Number;

@Component("camelExampleBean")
public class CamelStatelessSimpleExample {
	
	private KnowledgeBase kbase = DroolsUtil.createKnowledgeBaseForDrl("/com/github/rabid_fish/example1/StatelessSimpleExample.drl", getClass());
	
	public void executeRules(Object object) {
		
		Number number = (Number) object;
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		ksession.execute(number);
	}

}
