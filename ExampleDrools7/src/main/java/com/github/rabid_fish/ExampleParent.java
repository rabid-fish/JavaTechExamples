package com.github.rabid_fish;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

public abstract class ExampleParent {

	public abstract void executeRules(KieContainer kieContainer);

	public void process(String droolsFilename) {

		System.out.println("\nCompiling rules for file '" + droolsFilename + "'");

		KieContainer kbase = null;

		if (droolsFilename.endsWith("drl")) {
			kbase = createKnowledgeBaseForDrl(droolsFilename);
		} else if (droolsFilename.endsWith("xls") || droolsFilename.endsWith("csv")) {
			kbase = createKnowledgeBaseForDecisionTables(droolsFilename);
		} else {
			throw new RuntimeException("Rules filename does not have a recognized extension");
		}

		executeRules(kbase);
	}

	private KieContainer createKnowledgeBaseForDrl(String droolsFilename) {

		Class<? extends ExampleParent> clazz = getClass();

		return DroolsUtil.createKieContainerForDrl(droolsFilename, clazz);
	}

	private KieContainer createKnowledgeBaseForDecisionTables(String droolsFilename) {

		Class<? extends ExampleParent> clazz = getClass();
		return DroolsUtil.createKieContainerForDrl(droolsFilename, clazz);
	}
}
