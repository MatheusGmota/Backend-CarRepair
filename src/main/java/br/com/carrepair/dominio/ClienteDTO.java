package br.com.carrepair.dominio;

public class ClienteDTO {
    private Long id;

    public ClienteDTO() {}

    public ClienteDTO(Cliente cliente) {
        this();
        this.id = cliente.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
