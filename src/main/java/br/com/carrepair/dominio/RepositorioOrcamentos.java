package br.com.carrepair.dominio;

public interface RepositorioOrcamentos {
    Orcamento obter(Long idCliente,Long idVeiculo);
    void adicionar(Orcamento orcamento);
    void atualizar(Orcamento orcamento);
    void deletar(Long idOrcamento);
    void adicionarItem(ItemOrcamento itemOrcamento, Long idOrcamento);
    void atualizarItem(ItemOrcamento itemOrcamento, Long idOrcamento);
    void fechar();
}
