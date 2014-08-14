package com.github.rabid_fish;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.MaxCountRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.junit.Test;

public class RepoReaderTest {

	private RepoReader repoReader = new RepoReader();
	private List<RevFilter> list = new ArrayList<RevFilter>();

	@Test
	public void testValidateFilters() {
		repoReader.setMaxHits(5);
		repoReader.setAuthor("test");
		repoReader.validateFilters();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateFiltersCheckForMissingMinimumFilters() {
		repoReader.setAuthor("test");
		repoReader.validateFilters();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateFiltersCheckForDatesInSequentialOrder() {
		repoReader.setAuthor("test");
		repoReader.setSince(new Date());
		repoReader.setUntil(new Date());
		repoReader.validateFilters();
	}
	
	@Test
	public void testAddFilterForDates() {
		repoReader.setSince(new Date());
		repoReader.setUntil(new Date());
		repoReader.addFilterForDates(list);
		assertTrue(list.get(0) instanceof CommitTimeRevFilter);
	}
	
	@Test
	public void testAddFilterForDatesSetSinceDateOnly() {
		repoReader.setSince(new Date());
		repoReader.addFilterForDates(list);
		assertTrue(list.get(0) instanceof CommitTimeRevFilter);
	}
	
	@Test
	public void testAddFilterForDatesSetUntilDateOnly() {
		repoReader.setUntil(new Date());
		repoReader.addFilterForDates(list);
		assertTrue(list.get(0) instanceof CommitTimeRevFilter);
	}
	
	@Test
	public void testAddFilterForMaxHits() {
		repoReader.setMaxHits(5);
		repoReader.addFilterForMaxHits(list);
		assertTrue(list.get(0) instanceof MaxCountRevFilter);
	}
	
	@Test
	public void testAddFilterForAuthor() {
		repoReader.setAuthor("test");
		repoReader.addFilterForAuthor(list);
		assertTrue(list.get(0) instanceof RevFilter);
	}
}
