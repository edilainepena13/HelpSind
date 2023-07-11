package com.ufop.HelpSind.enums;

public enum TypeApport {

	A("Igualitário", "I"),
	X("Proporcional", "P");

	private final String name;
	private final String sigla;

	public String getName() {
		return name;
	}
		public String getSigla(){
		return sigla;
	}

	private TypeApport(String name, String sigla) {
		this.name = name;
		this.sigla = sigla;
	}
}
