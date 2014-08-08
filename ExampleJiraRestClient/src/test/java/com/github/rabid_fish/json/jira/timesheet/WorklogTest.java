package com.github.rabid_fish.json.jira.timesheet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WorklogTest {

	@Test
	// http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	public void testDeserialize() throws IOException {
		byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/example.json"));
		ObjectMapper objectMapper = new ObjectMapper();
		WorkDigest digest = objectMapper.readValue(jsonData, WorkDigest.class);
		System.out.println("Worklog Object\n" + digest);
	}

}
