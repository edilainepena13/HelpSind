package com.ufop.HelpSind.enums;

public enum ApportionmentType {

    P("Proporcional", "P"),
    I("Igualitario", "I");

    private final String name;
    private final String sigla;

    private ApportionmentType(String name, String sigla) {
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
