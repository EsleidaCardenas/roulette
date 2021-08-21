package com.masiv.esleida.cardenas.utils;

public enum StatusRoulette {
	OPEN("ABIERTO"), CLOSED("CERRADO");
	
	private String name;
	
	private StatusRoulette(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
