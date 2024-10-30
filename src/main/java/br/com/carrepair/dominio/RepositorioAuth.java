package br.com.carrepair.dominio;

public interface RepositorioAuth {

    Cliente autenticar(Login login);
    void fechar();
}
