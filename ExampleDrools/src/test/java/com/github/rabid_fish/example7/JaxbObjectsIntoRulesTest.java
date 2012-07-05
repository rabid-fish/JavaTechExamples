package com.github.rabid_fish.example7;

import org.junit.Test;

public class JaxbObjectsIntoRulesTest {

	@Test
	public void testDrl() {
		new JaxbObjectsIntoRules().process("JaxbObjectsIntoRules.drl");
	}

	@Test
	public void testXls() {
		new JaxbObjectsIntoRules().process("JaxbObjectsIntoRules.xls");
	}
}
