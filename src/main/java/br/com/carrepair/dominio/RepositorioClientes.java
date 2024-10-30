package br.com.carrepair.dominio;

public interface RepositorioClientes {

    void adicionar(Cliente cliente);
    Cliente obterClientePorId(Long id);
    Cliente obterClientePorNumeroDocumento(long numeroDocumento);
    void atualizar(Cliente cliente);
    void atualizarContato(Cliente cliente);
    void atualizarLogin(Login login, Long id);
    void deletar(Long id);
    void fechar();
}
