package com.ramosvji.clients.advice.dto;

import java.io.Serializable;
import java.util.List;

public class BadRequestResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfErrors;
	private List<MessageDto> messages;
	
	public Integer getNumberOfErrors() {
		return numberOfErrors;
	}
	public void setNumberOfErrors(Integer numberOfErrors) {
		this.numberOfErrors = numberOfErrors;
	}
	public List<MessageDto> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}
}
