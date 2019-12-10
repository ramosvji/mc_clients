package com.ramosvji.clients.entity;

import java.io.Serializable;

public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Rol [name=" + name + ", description=" + description + "]";
	}
}
