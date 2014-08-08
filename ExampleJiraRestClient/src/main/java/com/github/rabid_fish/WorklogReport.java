package com.github.rabid_fish;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.rabid_fish.json.jira.timesheet.WorkDigest;
import com.github.rabid_fish.json.jira.timesheet.Worklog;
import com.github.rabid_fish.json.jira.timesheet.WorklogEntry;

public class WorklogReport {

	private static final BigDecimal TIME_IN_HOURS_DIVISOR = new BigDecimal("3600");
	private static final String COMMA = ",";
	private static final String QUOTE = "\"";

	public void transformDigestToCsv(WorkDigest digest, String path) throws IOException, ParseException {
		
		FileWriter writer = new FileWriter(path);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		
		for (Worklog worklog : digest.getWorklog()) {
			for (WorklogEntry entry : worklog.getEntries()) {
				String csvRow = workEntryToCsvRecord(worklog, entry, sdf);
				writer.write(csvRow);
			}
		}
		
		writer.flush();
		writer.close();
	}

	String workEntryToCsvRecord(Worklog worklog, WorklogEntry entry, SimpleDateFormat sdf) {
		
		StringBuffer buffer = new StringBuffer("");
		
		buffer.append(QUOTE + entry.getAuthor() + QUOTE + COMMA);
		buffer.append(QUOTE + worklog.getKey() + QUOTE + COMMA);
		buffer.append(QUOTE + worklog.getSummary() + QUOTE + COMMA);
		buffer.append(QUOTE + formatLogDate(entry.getStartDate(), sdf) + QUOTE + COMMA);
		buffer.append(QUOTE + formatLogTime(entry.getTimeSpent()) + QUOTE);
		buffer.append("\n");
		
		return buffer.toString();
	}

	String formatLogDate(String dateString, SimpleDateFormat sdf) {
		long dateInMillis = Long.parseLong(dateString);
		Date date = new Date(dateInMillis);
		return sdf.format(date);
	}
	
	String formatLogTime(String timeString) {
		BigDecimal timeInSeconds = new BigDecimal(timeString);
		BigDecimal timeInHours = timeInSeconds.divide(TIME_IN_HOURS_DIVISOR, 2, BigDecimal.ROUND_HALF_UP);
		return timeInHours.toString();
	}
	
}
