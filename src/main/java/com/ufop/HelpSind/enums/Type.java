package com.ufop.HelpSind.enums;

public enum Type {

	A("Despesa"),
	X("Receita");

	private final String name;

	public String getName() {
		return name;
	}

	private Type(String name) {
		this.name = name;
	}
}
