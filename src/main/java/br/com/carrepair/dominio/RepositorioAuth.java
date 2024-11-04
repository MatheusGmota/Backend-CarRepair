package br.com.carrepair.dominio;

public interface RepositorioAuth {

    ClienteDTO autenticar(Login login);
    void fechar();
}
