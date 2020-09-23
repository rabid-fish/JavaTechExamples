package com.github.rabid_fish;

import org.drools.decisiontable.DecisionTableProviderImpl;
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

	public static String getDrlFromDecisionTable(String droolsFilename, Class<?> klazz) {

		DecisionTableConfiguration tableConfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();

		if (droolsFilename.endsWith("xls")) {
			tableConfiguration.setInputType(DecisionTableInputType.XLS);
		} else if (droolsFilename.endsWith("csv")) {
			tableConfiguration.setInputType(DecisionTableInputType.CSV);
		}

		Resource dt = ResourceFactory.newClassPathResource(droolsFilename, klazz);

		DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();

		String drl = decisionTableProvider.loadFromResource(dt, tableConfiguration);
		return drl;
	}
}
