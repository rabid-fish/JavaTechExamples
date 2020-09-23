package com.github.rabid_fish;

import java.math.BigDecimal;

public class Number {

	private BigDecimal value;
	private boolean even;
	private boolean odd;
	private String message;

	public Number(String stringValue) {
		setValue(new BigDecimal(stringValue));
		setEven(getValue().remainder(new BigDecimal("2")).equals(BigDecimal.ZERO));
		setOdd(getValue().remainder(new BigDecimal("2")).equals(BigDecimal.ONE));
	}
	
	public void addOneToValue() {
		setValue( getValue().add( new BigDecimal("1") ) );
	}

	public String valueAsString() {
		return value.toString();
	}
	
	public String getDataType() {
		
		if (value.equals(new BigDecimal(value.longValue()))) {
			return "whole";
		}
		
		return "decimal";
	}
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEven() {
		return even;
	}

	public void setEven(boolean even) {
		this.even = even;
	}

	public boolean isOdd() {
		return odd;
	}

	public void setOdd(boolean odd) {
		this.odd = odd;
	}

}
