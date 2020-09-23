package com.github.rabid_fish;

import com.github.rabid_fish.example1.StatelessSimpleExample;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.io.Resource;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class DroolsUtil {

	public static KieContainer createKieContainerForDrl(
			String droolsFilename, Class<?> clazz) {

		KieServices kieServices = KieServices.Factory.get();
		KieRepository kieRepository = kieServices.getRepository();
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

		Resource kieResource = ResourceFactory.newClassPathResource(droolsFilename, clazz);
		kieFileSystem.write(kieResource);

		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);

		kieBuilder.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.
		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}

		KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
		return kContainer;
	}

//	public static KieContainer createKieContainerForDecisionTables(
//			String droolsFilename, Class<?> clazz) {
//
//		DecisionTableConfiguration tableConfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
//
//		if (droolsFilename.endsWith("xls")) {
//			tableConfiguration.setInputType(DecisionTableInputType.XLS);
//		} else if (droolsFilename.endsWith("csv")) {
//			tableConfiguration.setInputType(DecisionTableInputType.CSV);
//		}
//
//		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//		Resource resource = ResourceFactory.newClassPathResource(droolsFilename, clazz);
//
//		kbuilder.add(resource, ResourceType.DTABLE, tableConfiguration);
//
//		outputGeneratedDRL(droolsFilename, tableConfiguration, resource);
//
//		if (kbuilder.hasErrors()) {
//			System.err.println(kbuilder.getErrors().toString());
//			throw new RuntimeException("One or more errors were encountered when compiling file");
//		}
//
//		KieContainer kbase = KieContainerFactory.newKieContainer();
//		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
//		return kbase;
//	}
//
//	public static void outputGeneratedDRL(String droolsFilename, DecisionTableConfiguration tableConfiguration, Resource resource) {
//
//	    SpreadsheetCompiler compiler = new SpreadsheetCompiler();
//	    String drl = null;
//
//		try {
//			if (droolsFilename.endsWith("xls")) {
//				drl = compiler.compile(resource.getInputStream(), InputType.XLS);
//			} else if (droolsFilename.endsWith("csv")) {
//				drl = compiler.compile(resource.getInputStream(), InputType.CSV);
//			}
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//		System.out.println("===========================================");
//		System.out.println("Generated DRL\n");
//		System.out.println(drl);
//	}
//
////	public static void outputGeneratedDRL(String droolsFilename, DecisionTableConfiguration tableConfiguration, Resource resource) {
////
////	    SpreadsheetCompiler compiler = new SpreadsheetCompiler();
////	    String drl = null;
////
////		try {
////			if (droolsFilename.endsWith("xls")) {
////				drl = compiler.compile(resource.getInputStream(), InputType.XLS);
////			} else if (droolsFilename.endsWith("csv")) {
////				drl = compiler.compile(resource.getInputStream(), InputType.CSV);
////			}
////
////		} catch (Exception e) {
////			throw new RuntimeException(e);
////		}
////
////		System.out.println("===========================================");
////		System.out.println("Generated DRL\n");
////		System.out.println(drl);
////	}

}
