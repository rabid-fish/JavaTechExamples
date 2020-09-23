package com.github.rabidfish;

import org.kie.api.runtime.KieContainer;

public abstract class ExampleParent {

	private static final Boolean DEBUG = false;

	public abstract void executeRules(KieContainer kieContainer);

	public void process(String droolsFilename) {

		System.out.println("\nCompiling rules for file '" + droolsFilename + "'");

		KieContainer kbase = null;

		if (droolsFilename.endsWith("drl")) {
			kbase = createKnowledgeBaseForDrl(droolsFilename);
		} else if (droolsFilename.endsWith("xls") || droolsFilename.endsWith("csv")) {

			if (DEBUG) {
				String generatedCode = DroolsUtil.getDrlFromDecisionTable(droolsFilename, getClass());
				System.out.println(generatedCode);
			}

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
