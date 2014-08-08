package com.github.rabid_fish.cucumber;

import cucumber.api.java.en.Given;

public class GlobalStepDefinitions {

	@Given("^I am running a scenario$")
	public void i_am_running_a_scenario() {
		System.out.println("Given: running a scenario");
	}

}
