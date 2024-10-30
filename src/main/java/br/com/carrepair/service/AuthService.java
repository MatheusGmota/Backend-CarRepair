package br.com.carrepair.service;

import br.com.carrepair.dominio.Cliente;
import br.com.carrepair.dominio.Login;
import br.com.carrepair.dominio.RepositorioAuth;

public class AuthService {

    private RepositorioAuth repositorioAuth;

    public AuthService(RepositorioAuth repositorioAuth) {
        this.repositorioAuth = repositorioAuth;
    }

    public Cliente autenticar(Login login) {
        Cliente cliente = repositorioAuth.autenticar(login);
        repositorioAuth.fechar();
        return cliente;
    }
}
