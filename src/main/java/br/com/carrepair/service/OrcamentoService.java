package br.com.carrepair.service;

import br.com.carrepair.dominio.*;

public class OrcamentoService {
    private RepositorioOrcamentos repositorioOrcamentos;
    private RepositorioPecas repositorioPecas;

    public OrcamentoService(RepositorioOrcamentos repositorioOrcamentos, RepositorioPecas repositorioPecas) {
        this.repositorioOrcamentos = repositorioOrcamentos;
        this.repositorioPecas = repositorioPecas;
    }

    public void adicionar(Orcamento orcamento) {
        repositorioOrcamentos.adicionar(orcamento);
        for (ItemOrcamento itemOrcamento : orcamento.getItensOrcamento()) {

            Peca peca = repositorioPecas.obterPorId(itemOrcamento.getIdPeca());
            itemOrcamento.setValorTotal(peca);

            repositorioOrcamentos.adicionarItem(itemOrcamento, orcamento.getIdOrcamento());
        }
        orcamento.calculoValorTotal(2);
        repositorioOrcamentos.atualizar(orcamento);
        repositorioPecas.fechar();
        repositorioOrcamentos.fechar();
    }

    public void atualizar(Orcamento orcamento) {
        repositorioOrcamentos.atualizar(orcamento);
        for (ItemOrcamento itemOrcamento : orcamento.getItensOrcamento()) {

            Peca peca = repositorioPecas.obterPorId(itemOrcamento.getIdPeca());
            itemOrcamento.setValorTotal(peca);

            repositorioOrcamentos.atualizarItem(itemOrcamento, orcamento.getIdOrcamento());
        }
        repositorioOrcamentos.fechar();
    }

    public void deletar(Long idOrcamento) {
        repositorioOrcamentos.deletar(idOrcamento);
        repositorioOrcamentos.fechar();
    }

    public Orcamento obterPorId(Long idOrcamento) {
        Orcamento orcamento = repositorioOrcamentos.obterPorId(idOrcamento);
        repositorioOrcamentos.fechar();
        return orcamento;
    }
}
