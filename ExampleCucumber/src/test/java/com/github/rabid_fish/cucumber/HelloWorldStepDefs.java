package com.github.rabid_fish.cucumber;

import cucumber.api.java.en.Given;

public class HelloWorldStepDefs {

	@Given("^print message ([a-zA-Z]+) to the user$")
	public void print_message_to_the_user(String message) {
		System.out.println(message);
	}
}
