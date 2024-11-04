package br.com.carrepair.dominio;

public class Login {
    private String usuario;
    private String senha;

    public Login() {}

    public Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
