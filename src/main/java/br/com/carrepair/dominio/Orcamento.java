package br.com.carrepair.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Orcamento {
    private Long idOrcamento;
    private Long idCliente;
    private Long idVeiculo;
    private double valorTotal;
    private double precoHoraMecanico;
    private Timestamp dataHora;

    @JsonProperty
    private ArrayList<ItemOrcamento> itensOrcamento;

    public Orcamento() {};

    public Orcamento(Long idOrcamento, Long idCliente, Long idVeiculo, double valorTotal, double precoHoraMecanico, Timestamp dataHora, ArrayList<ItemOrcamento> itensOrcamento) {
        this.idOrcamento = idOrcamento;
        this.idCliente = idCliente;
        this.idVeiculo = idVeiculo;
        this.valorTotal = valorTotal;
        this.precoHoraMecanico = precoHoraMecanico;
        this.dataHora = dataHora;
        this.itensOrcamento = itensOrcamento;
    }

    public void calculoValorTotal(double quantidadeHoras) {
        double somaItem = 0;
        for (ItemOrcamento item : itensOrcamento) {
            somaItem += item.getValorTotal();
        }
        this.valorTotal = somaItem + (precoHoraMecanico * quantidadeHoras);
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getPrecoHoraMecanico() {
        return precoHoraMecanico;
    }

    public Long getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public ArrayList<ItemOrcamento> getItensOrcamento() {
        return itensOrcamento;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setItensOrcamento(ArrayList<ItemOrcamento> itensOrcamento) {
        this.itensOrcamento = itensOrcamento;
    }
}
