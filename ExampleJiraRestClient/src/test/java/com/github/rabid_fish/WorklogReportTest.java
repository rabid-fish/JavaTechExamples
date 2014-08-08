package com.github.rabid_fish;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.github.rabid_fish.json.jira.timesheet.Worklog;
import com.github.rabid_fish.json.jira.timesheet.WorklogEntry;

public class WorklogReportTest {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
	WorklogReport report = new WorklogReport();
	
	@Test
	public void workEntryToCsvRecord() {
		
		Worklog worklog = new Worklog();
		worklog.setKey("EXAMPLE-1");
		worklog.setSummary("Example numero uno");
		
		WorklogEntry entry = new WorklogEntry();
		entry.setTimeSpent("100000");
		entry.setStartDate("100000");
		
		String csv = report.workEntryToCsvRecord(worklog , entry, sdf);
		
		assertTrue(csv.indexOf(",") > -1);
	}
	
	@Test
	public void workEntryToCsvRecordWithZeroDateAndTime() {
		
		Worklog worklog = new Worklog();
		worklog.setKey("EXAMPLE-1");
		worklog.setSummary("Example numero uno");
		
		WorklogEntry entry = new WorklogEntry();
		entry.setTimeSpent("0");
		entry.setStartDate("0");

		String csv = report.workEntryToCsvRecord(worklog, entry, sdf);
		
		assertTrue(csv.indexOf(",") > -1);
		assertEquals("\"null\",\"EXAMPLE-1\",\"Example numero uno\",\"19690031\",\"0.00\"\n", csv.toString());
	}
	
	@Test
	public void formatLogDate() {
		
		Long result1 = Long.parseLong( report.formatLogDate("1", sdf) );
		Long result2 = Long.parseLong( report.formatLogDate("0", sdf) );
		
		assertEquals(result1, result2);
	}

	@Test
	public void formatLogTime() {
		
		String result = report.formatLogTime("3600");
		assertTrue(new BigDecimal("1").compareTo(new BigDecimal(result)) == 0);
	}

	@Test
	public void formatLogTimeHalfHour() {
		
		String result = report.formatLogTime("1800");
		assertTrue(new BigDecimal("0.5").compareTo(new BigDecimal(result)) == 0);
	}
	
}
