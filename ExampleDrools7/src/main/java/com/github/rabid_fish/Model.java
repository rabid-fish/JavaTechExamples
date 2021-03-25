package com.github.rabid_fish;

import java.io.Serializable;

public class Model  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public Model() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
