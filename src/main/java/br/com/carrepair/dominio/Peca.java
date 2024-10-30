package br.com.carrepair.dominio;

public class Peca {

    private Long id;
    private String nome;
    private String codigo;
    private double valorUnitario;

    public Peca() {}

    public Peca(Long id, String nome, String codigo, double valorUnitario) {
        this();
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.valorUnitario = valorUnitario;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

}
