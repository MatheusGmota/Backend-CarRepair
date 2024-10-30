package br.com.carrepair.dominio;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String tipoDocumento;
    private long numeroDocumento;
    private String email;
    private long telefone;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.tipoDocumento = cliente.getTipoDocumento();
        this.numeroDocumento = cliente.getNumeroDocumento();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }

    public Long getId() {
        return id;
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
}
