
$(document).ready(function() {
	setInterval("updateTime()", 1000);
	updateTime();
});

function updateTime() {
	
	$.getJSON("hello/getTime", function(data) {
		$("#theTime").html(data.theTime);
	});
};
