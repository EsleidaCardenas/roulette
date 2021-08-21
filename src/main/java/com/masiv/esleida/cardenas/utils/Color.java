package com.masiv.esleida.cardenas.utils;

public enum Color {
	RED("ROJO"), BLACK("NEGRO");
	
	private String name;
	
	private Color(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
