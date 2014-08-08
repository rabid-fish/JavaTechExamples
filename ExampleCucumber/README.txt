Cucumber is a tool for describing your use cases in a way that is
testable.  You can read more about Cucumber at:
http://cukes.info/

Cucumber is implemeted by many languages, you can read more about
the Java implementation at:
https://github.com/cucumber/cucumber-jvm

The basic premise of Cucumber is a less technical person may be
involved in writing the .feature file that describes test
scenarios.  The developer then write a StepDef Java file which
binds the test scenario data in the .feature file to production
code.

To run the test scenarios, run CucumberExampleTest.java as a junit
test.

In this project, I have three examples found in src/test/cucumber:

1) HelloWorld.feature

   A very simple test scenario tied to HelloWorldStepDefs.java.

2) Scenario.feature

   A pair of test scenarios for one feature, reusing the same StepDefs.

3) ScenarioTable.feature

   An example of using a data table in the feature file which
   serializes to an object list in the StepDefs.
