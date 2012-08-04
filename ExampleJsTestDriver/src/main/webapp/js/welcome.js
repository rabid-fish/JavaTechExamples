
var functions = {
	"welcome" : function(name) {
		displayMessage(buildMessage(name));
	},
	"buildMessage" : function(name) {
		var message = "Hello " + name + "!";
		return message;
	},
	"displayMessage" : function(message) {
		alert(message);
	}
};
