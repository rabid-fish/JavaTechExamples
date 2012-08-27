package com.github.rabid_fish;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
 * Courtesy of
 * http://www.fwd.at/tomcat/sharing-session-data-howto.html
 * 
 * Keep in mind this approach will NOT work in a clustered environment
 * 
 * What we need to have happen:
 * 1) put a timestamp field on GUS and update it every time GUS is checked
 * 2) check the timestamp on every access to determine if it exceeds the timeout period, 
 *    and if so either redirect or create a fresh session
 * 3) have a listener or background process clean up sessions once a day or some such
 */
public class SessionSharingListener implements HttpSessionListener {

	  public void sessionCreated (HttpSessionEvent e) {

	  }

	  public void sessionDestroyed (HttpSessionEvent e) {
		  
//	      HttpSession session = e.getSession();
//	      String sessionid = session.getId();
//	      System.out.println("Looking if session " + sessionid + " is enabled for SSO");
//
//	      ServletContext context = session.getServletContext();
//	      // look if the session being destroyed is stored in the ServletContext
//	      Hashtable shareddata = (Hashtable)context.getAttribute("shared_userroles");
//
//	      if (shareddata.containsKey(sessionid)) {
//	          System.out.println("Removing SSO data for session " + sessionid);
//	          shareddata.remove(sessionid);
//	          context.setAttribute("shared_userroles", shareddata);
//	      }
	  }

	}