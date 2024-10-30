package br.com.carrepair.service;

import br.com.carrepair.dominio.Peca;
import br.com.carrepair.dominio.RepositorioPecas;

import java.util.ArrayList;

public class PecaService {
    private RepositorioPecas repositorioPecas;

    public PecaService(RepositorioPecas repositorioPecas) {
        this.repositorioPecas = repositorioPecas;
    }

    public void adicionar(Peca peca) {
        repositorioPecas.adicionar(peca);
        repositorioPecas.fechar();
    }

    public ArrayList<Peca> obterTodas() {
        ArrayList<Peca> pecas = repositorioPecas.obterTodas();
        repositorioPecas.fechar();
        return pecas;
    }

    public void atualizar(Peca pecaAtualizada) {
        repositorioPecas.atualizar(pecaAtualizada);
        repositorioPecas.fechar();
    }

    public void deletar(Long id) {
        repositorioPecas.deletar(id);
        repositorioPecas.fechar();
    }
}
