package com.ufop.HelpSind.enums;

public enum Type {

	A("Despesa", "D"),
	X("Receita", "R");

	private final String name;
	private final String sigla;

	public String getName() {
		return name;
	}
	public String getSigla(){
		return sigla;
	}
	
	private Type(String name, String sigla) {
		this.name = name;
		this.sigla = sigla;
	}
}
