package br.com.carrepair.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Veiculos {
    private Long idVeiculo;
    private String marca;
    private String modelo;
    private int ano;
    private int quilometragem;

    @JsonProperty
    private Diagnostico diagnostico;

    public Veiculos(){};

    public Veiculos(Long idVeiculo, String marca, String modelo, int ano, int quilometragem) {
        this();
        this.idVeiculo = idVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = quilometragem;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }
}
