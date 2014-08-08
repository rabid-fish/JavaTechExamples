package com.github.rabid_fish.cucumber;

import static org.junit.Assert.assertEquals;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioStepDefs {

	private String scenarioName = "";

	@Before
	public void get_scenario_name(Scenario scenario) {
		scenarioName = scenario.getName();
		System.out.println("Before: " + scenarioName);
	}

	@Given("^I want to run scenarios$")
	public void i_want_to_run_scenarios() {
		System.out.println("Background Given: want to run scenario");
	}

	@When("^I try to get the scenario name$")
	public void i_try_to_get_the_scenario_name() {
		System.out.println("When: try to get name");
	}

	@Then("^the scenario name is \"(.*?)\"$")
	public void the_scenario_name_is(String scenarioName) {
		System.out.println("Then: assert the scenario name");
		assertEquals(this.scenarioName, scenarioName);
	}
}
