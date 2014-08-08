Feature: Scenario information is available in table form during step execution

Background:
	Given the following drivers
		|firstName	|lastName	|
		|Jane		|Deer		|
		|John		|Doe		|

	@TableDriven
	Scenario Outline: Drag Race scenario
		Given car driven by "<name>"
		When that driver drives "<model>"
		Then that driver should get a quarter mile of "<time>"
		
		Examples:
			|name		|model			|time	|
			|John Deer	|Ford Mustang	|10s	|
			|John Deer	|Dodge Charger	|11s	|
			|Jane Doe	|Ford Mustange	|9s		|
