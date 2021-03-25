package com.github.rabid_fish;

import com.github.rabid_fish.example1.StatelessSimpleExample;
import org.junit.Test;

public class ScratchTest {

	@Test
	public void test() {
		new StatelessSimpleExample().process("/com/github/rabid_fish/Scratch.drl");
	}
}
