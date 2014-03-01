Imagine if you will having your js code that implements business functionality
separate from any jquery, tinymce, etc display code.  If you can separate things
in this way, you can write js unit tests and use JsTestDriver to run them as part
of a normal Maven build cycle.

This is a very small project to show how you can use the Maven JsTestDriver plugin
to autommate testing of your javascript.  You production javascript may be stuck
inside the webapp directory of a JavaEE app, no big deal.

Here are the files that matter:
- src/test/resources/jsTestDriver.conf  >> tells JsTestDriver where the production and test js files are
- src/main/webapp/js/welcome.js         >> our example production js file
- src/main/webapp/js/welcomeTest.js     >> our example test js file

!!! IMPORTANT !!!

The tests will fail when run if you do not have chrome.exe (or non-exe for Mac and
linux) available and on your PATH env variable.  JsTestDriver relies on being able
to open an instance of chrome to run the tests.

!!! IMPORTANT !!!
