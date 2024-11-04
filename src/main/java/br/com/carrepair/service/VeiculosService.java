package br.com.carrepair.service;

import br.com.carrepair.dominio.*;

public class VeiculosService {
    private GeraOrcamento geraOrcamento;
    private RepositorioVeiculos repositorioVeiculos;
    private RepositorioDiagnostico repositorioDiagnostico;

    public VeiculosService(GeraOrcamento geraOrcamento,RepositorioVeiculos repositorioVeiculos, RepositorioDiagnostico repositorioDiagnostico) {
        this.geraOrcamento = geraOrcamento;
        this.repositorioVeiculos = repositorioVeiculos;
        this.repositorioDiagnostico = repositorioDiagnostico;
    }

    public Veiculos obter(Long idCliente) {
        Veiculos veiculo = repositorioVeiculos.obterVeiculo(idCliente);
        repositorioVeiculos.fechar();
        return veiculo;
    }

    public OrcamentoDTO adicionar(Veiculos veiculos, Long idCliente) {
        OrcamentoDTO orcamentoDTO = geraOrcamento.obterOrcamento(veiculos);
        repositorioVeiculos.adicionar(veiculos, idCliente);
        repositorioDiagnostico.adicionar(veiculos.getDiagnostico(), veiculos.getIdVeiculo());
        repositorioVeiculos.fechar();
        repositorioDiagnostico.fechar();
        return orcamentoDTO;
    }

    public void atualizar(Veiculos veiculos) {
        repositorioVeiculos.atualizar(veiculos);
        repositorioVeiculos.fechar();
    }
}
