package com.github.rabid_fish.session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/servlet-context.xml")
@ActiveProfiles("test")
public class GlobalUserSessionSmokeTest {

	@Autowired
	GlobalUserSessionController2 controller;

	@Test
	public void testSave() {
		System.out.println("Success!");
	}

}
