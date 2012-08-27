package com.github.rabid_fish.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/jpa-context.xml")
@ActiveProfiles("test")
public class GlobalUserSessionRepositoryTest {

	@Autowired
	GlobalUserSessionRepository repository;

	@Test
	public void testSave() {

		String sessionId = "EXAMPLE JSESSIONID VALUE";
		
		List<GlobalUserSession> list = repository.list();
		assertTrue(list.size() == 0);
		
		GlobalUserSession session1 = repository.create(sessionId);
		GlobalUserSession session2 = repository.find(sessionId);
		
		assertEquals(session1.getSerializedValues(), session2.getSerializedValues());
		
		repository.delete(sessionId);
	}

}
