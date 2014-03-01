package com.github.rabid_fish;

import fit.Fixture;

public class MathOperationActionFixture extends Fixture {

	MathOperation mathOperation = new MathOperation();
	
	private int arg1;
	private int arg2;
	
	public void arg1(int arg1) {
		this.arg1 = arg1;
	}
	
	public void arg2(int arg2) {
		this.arg2 = arg2;
	}
	
	public int result() {
		return mathOperation.add(arg1, arg2);
	}
}
