package br.com.carrepair.dominio;

public interface RepositorioVeiculos {
    Long obterIdVeiculo(Long idCliente);
    Veiculos obterVeiculo(Long idVeiculo);
    void adicionar(Veiculos veiculos, Long id);
    void atualizar(Veiculos veiculos);
    void fechar();
}
