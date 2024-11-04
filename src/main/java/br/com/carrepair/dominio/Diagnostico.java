package br.com.carrepair.dominio;

import java.sql.Timestamp;

public class Diagnostico {

    private String descricaoProblema;

    public Diagnostico(){}

    public Diagnostico(String descricaoProblema) {
        this();
        this.descricaoProblema = descricaoProblema;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }
}
