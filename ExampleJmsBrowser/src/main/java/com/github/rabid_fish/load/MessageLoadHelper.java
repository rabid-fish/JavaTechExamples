package com.github.rabid_fish.load;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class MessageLoadHelper {

	private MessageLoad[] messageLoadArray = null;
	
	public MessageLoadHelper(String resourcePath) {
		setMessageLoadArray(resourcePath);
	}
	
	private void setMessageLoadArray(String resourcePath) {
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream stream = getClass().getResourceAsStream(resourcePath);
		try {
			setMessageLoadArray(mapper.readValue(stream, MessageLoad[].class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public MessageLoad[] getMessageLoadArray() {
		return messageLoadArray;
	}

	public void setMessageLoadArray(MessageLoad[] messageLoadArray) {
		this.messageLoadArray = messageLoadArray;
	}

}
