package com.github.rabid_fish;

import org.drools.KnowledgeBase;

public abstract class ExampleParent {

	public abstract void executeRules(KnowledgeBase kbase);

	public void process(String droolsFilename) {

		System.out.println("\nCompiling rules for file '" + droolsFilename + "'");

		KnowledgeBase kbase = null;

		if (droolsFilename.endsWith("drl")) {
			kbase = createKnowledgeBaseForDrl(droolsFilename);
		} else if (droolsFilename.endsWith("xls") || droolsFilename.endsWith("csv")) {
			kbase = createKnowledgeBaseForDecisionTables(droolsFilename);
		} else {
			throw new RuntimeException("Rules filename does not have a recognized extension");
		}

		executeRules(kbase);
	}

	private KnowledgeBase createKnowledgeBaseForDrl(String droolsFilename) {
		
		Class<? extends ExampleParent> clazz = getClass();
		
		return DroolsUtil.createKnowledgeBaseForDrl(droolsFilename, clazz);
	}

	private KnowledgeBase createKnowledgeBaseForDecisionTables(String droolsFilename) {
		
		Class<? extends ExampleParent> clazz = getClass();
		return DroolsUtil.createKnowledgeBaseForDecisionTables(droolsFilename, clazz);
	}
}
