package com.github.rabid_fish.example7;

import org.junit.Test;

public class JaxbObjectsIntoRulesExampleTest {

	@Test
	public void testDrl() {
		new JaxbObjectsIntoRulesExample().process("JaxbObjectsIntoRulesExample.drl");
	}

	@Test
	public void testXls() {
		new JaxbObjectsIntoRulesExample().process("JaxbObjectsIntoRulesExample.xls");
	}
}
