package com.github.rabid_fish.example8;

import org.junit.Test;

import com.github.rabid_fish.example8.DifferentObjectsIntoRulesExample;

public class DifferentObjectsIntoRulesExampleTest {

	@Test
	public void testDrl() {
		new DifferentObjectsIntoRulesExample().process("DifferentObjectsIntoRulesExample.drl");
	}

	@Test
	public void testXls() {
		new DifferentObjectsIntoRulesExample().process("DifferentObjectsIntoRulesExample.xls");
	}
}
