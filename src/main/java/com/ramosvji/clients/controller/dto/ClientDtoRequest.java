package com.ramosvji.clients.controller.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ClientDtoRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Name must not be null or empty")
	private String name;
	@NotEmpty(message="Lastname must not be null or empty")
	private String lastname;
	private Boolean enable;
	@NotBlank(message="Usernmae must not be null or empty")
	private String username;
	@NotBlank(message="Password must not be null or empty")
	private String password;
	
	private List<RoleDto> roles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
}
