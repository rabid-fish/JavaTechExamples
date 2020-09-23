package com.github.rabid_fish.example8;

import com.github.rabid_fish.example8.DifferentObjectsIntoRulesExample;
import org.junit.Test;

public class DifferentObjectsIntoRulesExampleTest {

	@Test
	public void testDrl() {
		new DifferentObjectsIntoRulesExample().process("/com/github/rabid_fish/example8/DifferentObjectsIntoRulesExample.drl");
	}

	@Test
	public void testXls() {
		new DifferentObjectsIntoRulesExample().process("/com/github/rabid_fish/example8/DifferentObjectsIntoRulesExample.xls");
	}
}
