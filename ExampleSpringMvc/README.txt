This is an extremely barebones SpringMvc app that illustrates use of the
Spring architecture to get an ajax request/response.

Files of note:
- web.xml          >> maps Spring's DispatcherServlet to a url
- HelloController  >> maps url's under 'hello' to views like 'hello.jsp'; also provides the ajax response body
- hello.jsp        >> simple view jspthat calls HelloController.getTime() to update the time via ajax
