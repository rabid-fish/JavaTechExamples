This project shows how to use Jetty to stand up single-servlet servers and use them 
as proxies to other services.  The benefit is that we can easily intercept the 
incoming request and return a mocked response.

Below are some command line 'profiles' you can run with.

===
Html Servlet
===

To start up an html servlet:
	mvn exec:java -Dexec.mainClass=com.github.rabid_fish.proxy.server.JettyServer -Dexec.args="html 6001"
Open a browser to:
	http://localhost:6001/?one=yes&two=no
You should see the additional 'GET' parameters print out in the browser.

===
Html Proxy
===

To start up a proxy to the html servlet:
	mvn exec:java -Dexec.mainClass=com.github.rabid_fish.proxy.server.JettyServer -Dexec.args="proxy_html 6002 /html/example_url.json /html localhost 6001"
You MUST have the html servlet already running for this example to work or else the
proxy will be unable to connect to it.

Open a browser to:
	http://localhost:6002/html/?one=no&two=yes
You should see the additiona 'GET' parameters print out in the browser.
Now open a browser to:
	http://localhost:6002/html/example1.html
You should see a mocked response returned.

===
Soap Servlet
===

To start up a soap servlet:
	mvn exec:java -Dexec.mainClass=com.github.rabid_fish.proxy.server.JettyServer -Dexec.args="soap 6003"
Open a browser to:
	http://localhost:6003/?wsdl
You should see the wsdl print out to the browser.

Open SoapUI and import the SoapUI 4.5 project file found in src/main/resources.  Open
MathRequest and MathRequest Invalid (found inside Math Service > MathBinding Test
Suite > MathRequest TestCase > Test Steps) and run the requests against the endpoint 
at:
	http://localhost:6003/math/
For MathRequest, you should see a valid MathResponse element with a value of '3',
which reflects the two operands of '1' and '2' being added together.  For MathRequest
Invalid you should see a SOAP fault.

===
Soap Proxy
===

To start up a proxy to the soap servlet:
	mvn exec:java -Dexec.mainClass=com.github.rabid_fish.proxy.server.JettyServer -Dexec.args="proxy_soap 6004 /soap/example_regex.json /soap localhost 6003"
You MUST have the soap servlet already running for this example to work or else the
proxy will be unable to connect to it.

Ensure SoapUI is open per the directions above, and change the endpoint to point to:
	http://localhost:6004/math/
Now run the MathRequest and you should once again see a valid response with a value
of '3'.  If you modify operand1 to have a value of '99999' and rerun the request,
you should get a response with value of '20' and a comment in the response which
reads '!--This is a mocked response-->'.

===
Useful links to read
===

"I want to send an XML file as a request to a SOAP server"...
http://stackoverflow.com/questions/12827900/why-is-this-simple-soap-client-not-working-org-apache-http
