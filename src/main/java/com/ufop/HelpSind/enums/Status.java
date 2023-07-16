package com.ufop.HelpSind.enums;

public enum Status {

	A("Ativo"),
	X("Excluido");

	private final String name;

	public String getName() {
		return name;
	}

	private Status(String name) {
		this.name = name;
	}
}
