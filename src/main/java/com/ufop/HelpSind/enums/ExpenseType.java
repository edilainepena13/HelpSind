package com.ufop.HelpSind.enums;

public enum ExpenseType {

    F("Fixa", "F"),
    V("Variável", "V");

    private final String name;
    private final String sigla;

    private ExpenseType(String name, String sigla) {
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
