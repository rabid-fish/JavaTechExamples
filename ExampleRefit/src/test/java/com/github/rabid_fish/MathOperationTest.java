package com.github.rabid_fish;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.rabid_fish.MathOperation;

public class MathOperationTest {
	
	@Test
	public void add() {
		int result = new MathOperation().add(5, 4);
		assertTrue(result == 9);
	}
}
