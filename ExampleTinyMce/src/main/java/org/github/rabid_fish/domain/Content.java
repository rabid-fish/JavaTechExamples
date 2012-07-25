package org.github.rabid_fish.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.github.rabid_fish.service.util.ServiceEntity;

public class Content implements Serializable, ServiceEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Date createdDate;
	
	@NotNull
	private String text;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
