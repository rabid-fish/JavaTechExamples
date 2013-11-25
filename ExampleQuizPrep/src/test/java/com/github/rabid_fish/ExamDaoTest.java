package com.github.rabid_fish;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.rabid_fish.ExamDao;
import com.github.rabid_fish.Problem;

public class ExamDaoTest {

	ExamDao examDao;
	
	@Before
	public void setUp() {
		examDao = new ExamDao();
	}
	
	@Test
	public void testListCategories() {
		List<String> listCategories = examDao.listCategories();
		assertTrue(listCategories.size() > 0);
	}
	
	@Test
	public void testListProblems() {
		Problem[] problems = examDao.listProblems("/json/problem-test.json");
		assertTrue(problems.length > 0);
	}
	
	@Test(expected = RuntimeException.class)
	public void testListProblemsThrowsExceptionWhenBadResourcePath() {
		examDao.listProblems("junk");
	}
}
