From https://code.google.com/p/refit/

	What is reFit?
	reFit is an enhanced version of the Framework for Integrated Test (Fit) created by Ward Cunningham.

That wasn't very helpful, was it?  Here's a better explanation:

	Fit is an integration testing framework whereby a developer creates a test 
	harnesses for their class under test called a 'Fixture', then provides test
	data and expected result via html or csv files.  The 'Fit' tool will run
	the source data through the fixture, causing the class under test to be
	executed, then collect the result data and run the comparison against the
	expected results.  If expected result do not match, the tests fail.

That better?

To run this project, simply run 'mvn clean install' at the command line.  Then 
open a browser and navigate to the output html test results found in the 
target/fit folder.
