Feature: Scenario information is available during step execution

Background:
	Given I want to run scenarios

	Scenario: My first scenario
		Given I am running a scenario
		When I try to get the scenario name
		Then the scenario name is "My first scenario"

	Scenario: My second scenario
		Given I am running a scenario
		When I try to get the scenario name
		Then the scenario name is "My second scenario"
