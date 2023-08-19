package com.ufop.HelpSind.enums;

public enum TypeDR {

	D("Despesa", "D"),
	R("Receita", "R");

	private final String name;
	private final String sigla;

	public String getName() {
		return name;
	}
	public String getSigla(){
		return sigla;
	}
	
	private TypeDR(String name, String sigla) {
		this.name = name;
		this.sigla = sigla;
	}
}
