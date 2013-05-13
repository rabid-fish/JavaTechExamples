This project shows how to use Jetty to stand up single-servlet servers
and use them as proxies to other services.  The benefit is that we can
easily intercept the incoming request and return a mocked response.

Below are some command line 'profiles' you can run with.

===
Form Servlet
===

To start up an html 'form' servlet:
	mvn exec:java -Dexec.mainClass=com.github.rabid_fish.proxy.server.JettyServer -Dexec.args="form 6001"
Open a browser to:
	http://localhost:6001/?one=yes&two=no
You should see the additional 'GET' parameters print out in the browser.

===
Html Proxy
===

To start up a proxy to the html 'form' servlet:
	mvn exec:java -Dexec.mainClass=com.github.rabid_fish.proxy.server.JettyServer -Dexec.args="proxy_html 6002 /html/example_url.json /form localhost 6001"
You MUST have the 'form' servlet already running for this example to 
work or else the proxy will be unable to connect to it. Open a browser 
to:
	http://localhost:6002/form/?one=no&two=yes
You should see the additiona 'GET' parameters print out in the browser.
Now open a browser to:
	http://localhost:6002/form/example1.html
You should see a mocked response returned.

===
Soap Proxy
===

To start up a proxy to a locally running Tomcat with a deployed Soap service:
	mvn exec:java -Dexec.mainClass=com.github.rabid_fish.proxy.server.JettyServer -Dexec.args="proxy_soap 6003 /soap/example_regex.json /soap localhost 8080"
Can't really try it out with a browser, I recommend grabbing SoapUI 
and giving it a whirl that way.

===
Useful links to read
===

"I want to send an XML file as a request to a SOAP server"...
http://stackoverflow.com/questions/12827900/why-is-this-simple-soap-client-not-working-org-apache-http
