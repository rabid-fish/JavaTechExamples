package com.github.rabidfish.webflow.ex2;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PhoneComplex implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 100)
	private String areaCode;
	@NotNull
	@Size(min = 1, max = 100)
	private String number;
	
	public PhoneComplex() {
		super();
	}

	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
