package com.github.rabid_fish.session;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "global_user_session")
public class GlobalUserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 60)
	private String sessionId;

	@Column(length = 1000)
	private byte[] serializedValues;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public byte[] getSerializedValues() {
		return serializedValues;
	}

	public void setSerializedValues(byte[] serializedValues) {
		this.serializedValues = serializedValues;
	}

}
