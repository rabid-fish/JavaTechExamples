package com.github.rabid_fish;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class ExamDao {

	public List<String> listCategories() {

		String resourcePath = "/json/index.json";
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		if (stream == null) {
			throw new RuntimeException("Configuration file not found: " + resourcePath);
		}
		
		String[] files = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			files = mapper.readValue(stream, String[].class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		List<String> list = new ArrayList<String>();
		try {
			for (String file : files) {
				appendPathAnchor(list, file);
			}

		} catch (UnsupportedEncodingException uee) {
			throw new RuntimeException("For some reason UTF-8 isn't supported, wtf?");
		}

		return list;
	}
	
	void appendPathAnchor(List<String> list, String fileName) throws UnsupportedEncodingException {
		
		String fileNameEncoded = URLEncoder.encode(fileName, "UTF-8");
		list.add("<a href=\"servlet?action=" + ExamAction.BEGIN_EXAM + "&amp;file=" + fileNameEncoded + "\">" + fileName + "</a>");
	}

	public Problem[] listProblems(String resourcePath) {

		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		
		if (stream == null) {
			throw new RuntimeException("Configuration file not found: " + resourcePath);
		}
		
		Problem[] problems = null;
		try {
			problems = mapper.readValue(stream, Problem[].class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return problems;
	}

}
