package br.com.carrepair.service;

import br.com.carrepair.dominio.ClienteDTO;
import br.com.carrepair.dominio.Login;
import br.com.carrepair.dominio.RepositorioAuth;

public class AuthService {

    private RepositorioAuth repositorioAuth;

    public AuthService(RepositorioAuth repositorioAuth) {
        this.repositorioAuth = repositorioAuth;
    }

    public ClienteDTO autenticar(Login login) {
        ClienteDTO clienteDTO = repositorioAuth.autenticar(login);
        repositorioAuth.fechar();
        return clienteDTO;
    }
}
