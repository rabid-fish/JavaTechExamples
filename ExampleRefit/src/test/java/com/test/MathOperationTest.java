package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathOperationTest {
	
	@Test
	public void add() {
		int result = new MathOperation().add(5, 4);
		assertTrue(result == 9);
	}
}
