package com.github.rabid_fish.example1;

import com.github.rabid_fish.DroolsUtil;
import com.github.rabid_fish.Number;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;

public class StatelessSimpleExampleTest {

	@Test
	public void test() {
		new StatelessSimpleExample().process("/com/github/rabid_fish/example1/StatelessSimpleExample.drl");
	}

}
