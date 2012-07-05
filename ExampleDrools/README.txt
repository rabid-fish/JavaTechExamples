Example Drools
---

This project contains a number of examples using the Drools rules engine. The examples
are contained in numbered folders (example1, example2, etc) with each example building
on the previous one and adding a bit more complexity.  I felt it was necessary to
author these rules as the examples included with the Drools download do not have an
obvious starting point.  I also ran into many issues trying to get Decision Tables
working, so I've placed some working ones here.

Each of the examples is run through Java and is hooked up via maven.  Simply run:

  mvn clean compile test

at the command line (assuming Java and Maven are installed) to get the tests to run.

IMPORTANT: if you are using Eclipse, you will have errors in the project until you
add target/generated-sources/xsd to the build path.  Right-click on that folder and
select "Build Path" > "Use as source folder".

To read more about Drools or download the libraries, check out:
http://www.jboss.org/drools/

Documentation for the "rules" part of Drools may be found at:
http://docs.jboss.org/drools/release/5.4.0.Final/drools-expert-docs/pdf/
