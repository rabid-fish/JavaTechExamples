package com.github.rabid_fish;

import fit.ColumnFixture;

public class MathOperationColumnFixture extends ColumnFixture {

	MathOperation mathOperation = new MathOperation();
	
	public int arg1;
	public int arg2;
	
	public int result() {
		return mathOperation.add(arg1, arg2);
	}

}
