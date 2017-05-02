package com.github.rabidfish.webflow.ex4;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.rabidfish.HasId;

public class Car implements Serializable, HasId {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String make;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String model;

	public Car() {
		super();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
