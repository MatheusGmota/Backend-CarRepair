package br.com.carrepair.dominio;

import java.util.ArrayList;

public interface RepositorioPecas {
    void adicionar(Peca peca);
    ArrayList<Peca> obterTodas();
    Peca obterPorId(Long id);
    void atualizar(Peca peca);
    void deletar(Long id);
    void fechar();
}
