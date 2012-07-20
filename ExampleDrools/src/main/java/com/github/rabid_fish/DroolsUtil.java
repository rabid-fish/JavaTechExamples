package com.github.rabid_fish;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;

public class DroolsUtil {

	public static KnowledgeBase createKnowledgeBaseForDecisionTables(
			String droolsFilename, Class<?> clazz) {
		
		DecisionTableConfiguration tableConfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		
		if (droolsFilename.endsWith("xls")) {
			tableConfiguration.setInputType(DecisionTableInputType.XLS);
		} else if (droolsFilename.endsWith("csv")) {
			tableConfiguration.setInputType(DecisionTableInputType.CSV);
		}
	
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource resource = ResourceFactory.newClassPathResource(droolsFilename, clazz);
	
		kbuilder.add(resource, ResourceType.DTABLE, tableConfiguration);
	
		outputGeneratedDRL(droolsFilename, tableConfiguration, resource);
		
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
			throw new RuntimeException("One or more errors were encountered when compiling file");
		}
		
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	public static KnowledgeBase createKnowledgeBaseForDrl(
			String droolsFilename, Class<?> clazz) {
		
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource resource = ResourceFactory.newClassPathResource(droolsFilename, clazz);
		
		kbuilder.add(resource, ResourceType.DRL);
	
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
			throw new RuntimeException("One or more errors were encountered when compiling file");
		}
	
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	public static void outputGeneratedDRL(String droolsFilename, DecisionTableConfiguration tableConfiguration, Resource resource) {
		
	    SpreadsheetCompiler compiler = new SpreadsheetCompiler();
	    String drl = null;
	
		try {
			if (droolsFilename.endsWith("xls")) {
				drl = compiler.compile(resource.getInputStream(), InputType.XLS);
			} else if (droolsFilename.endsWith("csv")) {
				drl = compiler.compile(resource.getInputStream(), InputType.CSV);
			}
	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		System.out.println("===========================================");
		System.out.println("Generated DRL\n");
		System.out.println(drl);
	}

}
