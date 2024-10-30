package br.com.carrepair.service;

import br.com.carrepair.dominio.Cliente;
import br.com.carrepair.dominio.RepositorioClientes;

public class ClienteService {

    private RepositorioClientes repositorioClientes;

    public ClienteService(RepositorioClientes repositorioClientes) {
        this.repositorioClientes = repositorioClientes;
    }

    public Cliente obterCliente(Long id) {
        Cliente cliente = repositorioClientes.obterClientePorId(id);
        repositorioClientes.fechar();
        return cliente;
    }

    public Cliente obterCliente(long numeroDocumento) {
        Cliente cliente = repositorioClientes.obterClientePorNumeroDocumento(numeroDocumento);
        repositorioClientes.fechar();
        return cliente;
    }

    public void adicionar(Cliente cliente) {
        repositorioClientes.adicionar(cliente);
        repositorioClientes.fechar();
    }


    public void atualizar(Cliente cliente) {
        repositorioClientes.atualizar(cliente);
        repositorioClientes.atualizarContato(cliente);
        repositorioClientes.atualizarLogin(cliente.getLogin(), cliente.getId());
        repositorioClientes.fechar();
    }

    public void deletar(Long id) {
        repositorioClientes.deletar(id);
        repositorioClientes.fechar();
    }

}
