package com.github.rabid_fish.example1;

import org.junit.Test;

import com.github.rabid_fish.example1.StatelessSimpleExample;

public class StatelessSimpleExampleTest {

	@Test
	public void test() {
		new StatelessSimpleExample().process("StatelessSimpleExample.drl");
	}
}
