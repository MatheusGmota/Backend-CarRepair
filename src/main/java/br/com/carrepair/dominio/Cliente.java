package br.com.carrepair.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente {
    private Long id;
    private String nome;
    private String tipoDocumento;
    private long numeroDocumento;
    private String email;
    private long telefone;

    @JsonProperty
    private Login login;

//    @JsonPropert
//    private Veiculos veiculos;

    public Cliente() {}

    public Cliente(Long id, String nome, String tipoDocumento, long numeroDocumento, String email, long telefone) {
        this();
        this.id = id;
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public long getTelefone() {
        return telefone;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) { this.login = login; }

}
