package com.github.rabid_fish.example7;

import org.junit.Test;

public class JaxbObjectsIntoRulesExampleTest {

	@Test
	public void testDrl() {
		new JaxbObjectsIntoRulesExample().process("/com/github/rabid_fish/example7/JaxbObjectsIntoRulesExample.drl");
	}

	@Test
	public void testXls() {
		new JaxbObjectsIntoRulesExample().process("/com/github/rabid_fish/example7/JaxbObjectsIntoRulesExample.xls");
	}
}
