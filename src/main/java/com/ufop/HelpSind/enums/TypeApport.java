package com.ufop.HelpSind.enums;

public enum TypeApport {

	A("Igualitário"),
	X("Proporcional");

	private final String name;

	public String getName() {
		return name;
	}

	private TypeApport(String name) {
		this.name = name;
	}
}
