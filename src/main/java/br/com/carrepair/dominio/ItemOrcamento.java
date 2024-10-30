package br.com.carrepair.dominio;

public class ItemOrcamento {
    private Long idPeca;
    private int quantidade;
    private double valorTotal;

    public ItemOrcamento() {};

    public ItemOrcamento(Long idPeca, int quantidade) {
        this();
        this.idPeca = idPeca;
        this.quantidade = quantidade;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Peca peca) {
        this.valorTotal = peca.getValorUnitario() * quantidade;
    }
}
