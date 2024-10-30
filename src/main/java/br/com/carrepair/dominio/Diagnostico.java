package br.com.carrepair.dominio;

import java.sql.Timestamp;

public class Diagnostico {
    private String descricaoProblema;
    private Timestamp dataHoraAtual;

    public Diagnostico(){}

    public Diagnostico(String descricaoProblema, Timestamp dataHoraAtual) {
        this();
        this.descricaoProblema = descricaoProblema;
        this.dataHoraAtual = dataHoraAtual;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public Timestamp getDataHoraAtual() { return dataHoraAtual; }
}
