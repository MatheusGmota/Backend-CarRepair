package br.com.carrepair.service;

import br.com.carrepair.dominio.*;

public class VeiculosService {
    private RepositorioVeiculos repositorioVeiculos;
    private RepositorioDiagnostico repositorioDiagnostico;

    public VeiculosService(RepositorioVeiculos repositorioVeiculos, RepositorioDiagnostico repositorioDiagnostico) {
        this.repositorioVeiculos = repositorioVeiculos;
        this.repositorioDiagnostico = repositorioDiagnostico;
    }

    public Veiculos obter(Long idCliente) {
        Veiculos veiculo = repositorioVeiculos.obterVeiculo(idCliente);
        repositorioVeiculos.fechar();
        return veiculo;
    }
    public void adicionar(Veiculos veiculos, Long idCliente) {
        repositorioVeiculos.adicionar(veiculos, idCliente);
        Long idVeiculo = repositorioVeiculos.obterIdVeiculo(idCliente);
        repositorioDiagnostico.adicionar(veiculos.getDiagnostico(), idVeiculo);
        repositorioDiagnostico.fechar();
        repositorioVeiculos.fechar();
    }

    public void atualizar(Veiculos veiculos) {
        repositorioVeiculos.atualizar(veiculos);
        repositorioVeiculos.fechar();
    }
}
