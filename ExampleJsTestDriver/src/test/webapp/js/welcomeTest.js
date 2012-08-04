TestCase("WelcomeTest", {
	"test buildMessage" : function() {
		var message = functions.buildMessage("Dan");
		assertEquals("Hello Dan!", message);
	}
});

functions.displayMessage = function() {
	// do nothing
};
