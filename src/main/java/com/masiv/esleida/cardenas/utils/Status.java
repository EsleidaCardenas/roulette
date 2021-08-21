package com.masiv.esleida.cardenas.utils;

public enum Status {
	SUCCESS("EXITOSO"), DENIED("DENEGADO");
	
	private String name;
	
	private Status(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
