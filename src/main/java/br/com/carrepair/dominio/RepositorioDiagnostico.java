package br.com.carrepair.dominio;

public interface RepositorioDiagnostico {
    void adicionar(Diagnostico diagnostico, Long idVeiculo);
    void fechar();
}
