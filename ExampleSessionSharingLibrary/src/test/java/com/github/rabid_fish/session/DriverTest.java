package com.github.rabid_fish.session;

import org.junit.Test;

public class DriverTest {

	@Test
	public void test() throws ClassNotFoundException {
		Class.forName("org.h2.Driver");
	}
	
}
