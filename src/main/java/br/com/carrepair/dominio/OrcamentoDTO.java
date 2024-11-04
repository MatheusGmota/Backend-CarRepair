package br.com.carrepair.dominio;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OrcamentoDTO {
    @JsonProperty
    private Veiculos veiculo;

    private String possivelReparo;
    private double valorTotal;

    public OrcamentoDTO(Veiculos veiculo, String possivelReparo,double valorTotal) {
        this.veiculo = veiculo;
        this.possivelReparo = possivelReparo;
        this.valorTotal = valorTotal;
    }

    public Veiculos getVeiculo() {
        return veiculo;
    }

    public String getPossivelReparo() {
        return possivelReparo;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
