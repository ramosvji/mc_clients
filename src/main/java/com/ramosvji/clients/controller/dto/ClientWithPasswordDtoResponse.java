package com.ramosvji.clients.controller.dto;

public class ClientWithPasswordDtoResponse extends ClientDtoResponse {
	private static final long serialVersionUID = 1L;
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
