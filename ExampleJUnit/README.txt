# ExampleJUnit

This project was developed as a basis for a presentation on 'An Intro to Unit Testing'.


## Notes for the presentation

To start the presentation, the contents of most of the methods in the src/test/java 
directory have been hidden in the 'master' branch and are only visible in the 'answer' 
branch.  Have attendees for the presentation download the example code.  We will walk 
thru creating tests for each of the services as part of the presentation.


### ContactLogic1

We have a single method we're going to test here.  What kinds of tests will we write? Let's
start with a single positive test and a single negative test.

For the positive test, instantiate a Contact with a firstName and lastName, pass it into
getFullName(), and assert the result.

For the negative test, instantiate a Contact but leave firstName and lastName null.  Assert
the result.  What should the result be?  Probably should return a single null value maybe,
not a string containing the word null? Should it intentially blow up instead and how to test
for that?

Of note: I like using whitespace to separate 1) assumptions, 2) execution, 3) assertions.
Of note: I like to have the test name be the method under test name plus any additional
changes to how I'm testing the method.  So getFullName_FromNulls for example.


### ContacLogic2

We have a series of methods we're going to test.  Instead of writing tests which exercise
the methods in isolation, we'll attempt to test the class as a whole.

Let's first write a test that does things the hard way.  Instantiate a Contact and call
its setters, pass it into an instance of ContactLogic2.validateContact(), and assert the
results.

Now let's try to shorten the test by compacting down the Contact initialization into just 
a constructor call.  Is test setup short enough to be able to rip through many scenarios
and still have a high amount of readability?

Lastly we're going to spend time writing test helper methods so we can boil an assertion
down to a single method call where we pass in the setup values and the expected results.
This will take some doing, so we're going to provide the assertContactLogicValidate()
method so we don't spend the entire presentation writing this guy out.


### ContactRepository3

We have a faux database repository we want to test

Tests we'll want to write to exercise the service at a higher level:
- assert the initial state of the service
- assert the service after loading up some state

Of note: one you start having objects that you need to have set up across multiple test
methods, it makes sense to create a setup method that has an @Before annotation.



