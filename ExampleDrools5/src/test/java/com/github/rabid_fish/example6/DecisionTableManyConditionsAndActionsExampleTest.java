package com.github.rabid_fish.example6;

import org.junit.Test;

public class DecisionTableManyConditionsAndActionsExampleTest {

	// A good explanation of syntax:
	// http://docs.codehaus.org/display/DROOLS/Decision+Tables
	// Also have a look at:
	// http://technicalmumbojumbo.wordpress.com/2009/03/28/jboss-drools-decision-tables/
	
	@Test
	public void testCsv() {
		new DecisionTableManyConditionsAndActionsExample().process("DecisionTableManyConditionsAndActionsExample.csv");
	}
	
	@Test
	public void testXls() {
		new DecisionTableManyConditionsAndActionsExample().process("DecisionTableManyConditionsAndActionsExample.xls");
	}
	
}
