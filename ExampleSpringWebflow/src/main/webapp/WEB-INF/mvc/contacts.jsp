<!DOCTYPE html>
<html>
<head>
	<title>Example Spring Webflow</title>
	<link rel="stylesheet" type="text/css" href="/ExampleSpringWebflow/static/common.css" />
</head>
<body>

	<h3>Contacts</h3>
	<div id="contacts"></div>
	<a href="./">Home</a>

<script type="text/javascript" src="/ExampleSpringWebflow/static/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

	// based on code from:
	// http://stackoverflow.com/questions/5334380/replacing-text-inside-of-curley-braces-javascript
	var template = function(string, data) {
		for (var element in data) {
			string = string.replace(new RegExp('{' + element + '}','g'), data[element]);
		}
		
		return string;
	}

	function insertTestData() {
		$.ajax({
			dataType: 'json',
			type: 'GET',
			url: 'contact/initializeTestData',
			data: '',
			success: function(data) {
				refreshList();
			}
		});
	}
	
	function refreshList() {
		$.ajax({
			dataType: 'json',
			type: 'GET',
			url: 'contact/list',
			data: '',
			success: function(data) {
				drawContactsTable(data);
			}
		});
	}
	
	function drawContactsTable(contacts) {
		
		var rowTemplate = 
			'<tr>' + 
			'  <td>{firstName}</td>' +
			'  <td>{lastName}</td>' +
			'  <td>{phone}</td>' +
			'</tr>\n'
			;
		
		var rowsHtml = '';
		for (var i = 0; i < contacts.length; i++) {
			var contact = contacts[i];
			rowsHtml += template(rowTemplate, contact);
		}
			
		var html = 
			'<table>\n' +
			'<tr>' + 
			'  <th>first</th>' +
			'  <th>last</th>' +
			'  <th>phone</th>' +
			'</tr>\n' +
			rowsHtml +
			'</table>\n'
			;
			
		html = template(html, contact);
		$('#contacts').html(html);
	}
	
	$( document ).ready(function() {
		insertTestData();
	});
	
</script>

</body>
</html>
