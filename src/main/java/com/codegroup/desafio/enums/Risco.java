package com.codegroup.desafio.enums;

public enum Risco {
    ALTO("Alto"),
    MEDIA("Médio"),
    BAIXA("Baixo");

    private String descricao;
    Risco(String descricao){
        this.descricao=descricao;
    }
    public String getDescricao() {
        return descricao;
    }

}
