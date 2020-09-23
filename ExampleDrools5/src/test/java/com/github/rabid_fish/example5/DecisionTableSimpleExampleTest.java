package com.github.rabid_fish.example5;

import org.junit.Test;

public class DecisionTableSimpleExampleTest {

	// A good explanation of syntax:
	// http://docs.codehaus.org/display/DROOLS/Decision+Tables
	// Also have a look at:
	// http://technicalmumbojumbo.wordpress.com/2009/03/28/jboss-drools-decision-tables/
	
	@Test
	public void testCsv() {
		new DecisionTableSimpleExample().process("DecisionTableSimpleExample.csv");
	}
	
	@Test
	public void testXls() {
		new DecisionTableSimpleExample().process("DecisionTableSimpleExample.xls");
	}
}
