package com.ufop.HelpSind.enums;

public enum TypePerson {

    M("Morador", "M"),
    P("Proprietário", "P"),
    A("Ambos", "A");

    private final String name;
    private final String sigla;

    private TypePerson(String name, String sigla) {
        this.name = name;
        this.sigla = sigla;
    }

    public String getName() {
        return name;
    }

    public String getSigla() {
        return sigla;
    }
}
