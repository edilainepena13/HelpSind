package com.ufop.HelpSind.enums;

public enum TypeApportCondominium {

	A("Fixo", "F"),
	X("Variável", "V");

	private final String name;
	private final String sigla;

	public String getName() {
		return name;
	}
		public String getSigla(){
		return sigla;
	}

	private TypeApportCondominium(String name, String sigla) {
		this.name = name;
		this.sigla = sigla;
	}
}
