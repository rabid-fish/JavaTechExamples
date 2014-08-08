package com.github.rabid_fish;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.github.rabid_fish.json.jira.timesheet.WorkDigest;

/*
 * Example coded with much help from the following sources:
 * http://stackoverflow.com/questions/14458450/what-to-use-instead-of-org-jboss-resteasy-client-clientrequest
 * https://jersey.java.net/nonav/documentation/latest/user-guide.html#d0e4909
 * https://answers.atlassian.com/questions/30525/how-do-i-log-in-jira-using-rest-api
 * 
 * In order to get this to run properly, use the following vm parameter
 * 		-Djsse.enableSNIExtension=false
 */
public class JiraRestClient {
	
	private static final String APPLICATION_JSON = "application/json";
	private Client client = null;
	
	/*
	 * Example call to main:
	 *   java com.
	 *   "http://<your_server>/rest/auth/latest/session"
	 *   "http://<your_server>/rest/timesheet-gadget/1.0/raw-timesheet.json?startDate=1%2FMay%2F14&endDate=31%2FMay%2F14&targetUser=<target_user>&targetGroup=&excludeTargetGroup=&priority=&projectid=&filterid=&projectRoleId="
	 */
	public static void main(String[] args) throws IOException, ParseException {
		
		if (args.length != 2) {
			throw new IllegalArgumentException("Missing username and password");
		}
		
		JiraRestClient jiraRestClient = new JiraRestClient();
		jiraRestClient.login(args[0], args[1], args[2]);
		jiraRestClient.createWorklogReport(args[3]);
	}

	void login(String username, String password, String urlLogin) {
		
		client = ClientBuilder.newClient();
		client.register(JacksonFeature.class);
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
		client.register(feature);
		
		Invocation invocation = client.target(urlLogin).request(APPLICATION_JSON).buildGet();
		String response = invocation.invoke(String.class);
		
		//System.out.println(response);
		assert(response != null);
	}
	
	void createWorklogReport(String uri) throws IOException, ParseException {
		
		String uri1 = uri;
		Invocation invocation = client.target(uri1).request(APPLICATION_JSON).buildGet();
		WorkDigest digest = invocation.invoke(WorkDigest.class);
		
		WorklogReport report = new WorklogReport();
		report.transformDigestToCsv(digest, "./example.csv");
		
//		System.out.println(response);
	}

}
