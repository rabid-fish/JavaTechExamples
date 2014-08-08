package com.github.rabid_fish.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioTableStepDefs {

	class Driver {
		String firstName;
		String lastName;
		String fullName;
		String defaultTime;
	}

	List<Driver> drivers = null;
	String name = null;
	String model = null;
	
	/*
	 * Example Background Given table taken from
	 * http://zsoltfabok.com/blog/2011/12/cucumber-jvm-more-scenarios/
	 */
	@Given("^the following drivers$")
	public void the_following_drivers(List<Driver> drivers) {
		System.out.println("Background Given: the following drivers");
		for (Driver driver : drivers) {
			String fullName = driver.firstName + " " + driver.lastName;
			System.out.println("  driver: " + fullName);
			driver.fullName = fullName;
			
			if (fullName.equals("Jane Doe")) {
				driver.defaultTime = "9s";
			}
		}
		this.drivers = drivers;
	}

	/*
	 * Example table scenario helped by examples from
	 * https://github.com/cucumber/cucumber/wiki/Scenario-Outlines
	 */
	@Given("^car driven by \"([^\"]*)\"$")
	public void car_driven_by(String name) {
		System.out.println("Given: " + name);
		this.name = name;
	}
	
	@When("^that driver drives \"([^\"]*)\"$")
	public void that_driver_drives(String model) {
		System.out.println("When: " + model);
		this.model = model;
	}
	
	@Then("^that driver should get a quarter mile of \"([^\"]*)\"$")
	public void that_driver_should_get(String expectedTime) {
		System.out.println("Then: " + name + " - " + model + " - " + expectedTime);
		
		// Note that we are NOT asserting John Deer's times, only Jane Doe's
		for (Driver driver : drivers) {
			if (name.equals(driver.fullName) && driver.defaultTime != null) {
				assertEquals(expectedTime, driver.defaultTime);
				return;
			}
		}
	}

}
