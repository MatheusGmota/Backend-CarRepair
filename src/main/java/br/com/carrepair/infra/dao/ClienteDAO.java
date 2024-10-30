package br.com.carrepair.infra.dao;

import br.com.carrepair.dominio.Cliente;
import br.com.carrepair.dominio.Login;
import br.com.carrepair.dominio.RepositorioClientes;

import java.sql.*;

public class ClienteDAO implements RepositorioClientes {
    private Connection conn;

    public ClienteDAO() {
        this.conn = ConnectionFactory.obterConexao();
    }

    public void adicionar(Cliente cliente) {
        try {
            String sqlInsert = "INSERT INTO tb_cliente(nome_cliente, tipo_documento, numero_documento) VALUES(?,?,?)";
            String sqlInsertCtt = "INSERT INTO tb_contato VALUES(?,?,?)";

            PreparedStatement cmdInsert = conn.prepareStatement(sqlInsert);
            cmdInsert.setString(1, cliente.getNome());
            cmdInsert.setString(2, cliente.getTipoDocumento());
            cmdInsert.setLong(3, cliente.getNumeroDocumento());
            cmdInsert.executeUpdate();
            cmdInsert.close();

            cliente.setId(obterIdCliente());

            PreparedStatement cmdICtt = conn.prepareStatement(sqlInsertCtt);
            cmdICtt.setLong(1, cliente.getId());
            cmdICtt.setString(2, cliente.getEmail());
            cmdICtt.setLong(3, cliente.getTelefone());
            cmdICtt.executeUpdate();
            cmdICtt.close();

            adicionarLogin(cliente);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void adicionarLogin(Cliente cliente) {
        Login login = cliente.getLogin();
        try {
            String sqlInsert = "INSERT INTO tb_login VALUES(?,?,?)";
            PreparedStatement cmdInsercao = conn.prepareStatement(sqlInsert);
            cmdInsercao.setLong(1, cliente.getId());
            cmdInsercao.setString(2, login.getUsuario());
            cmdInsercao.setString(3, login.getSenha());
            cmdInsercao.executeUpdate();
            cmdInsercao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Cliente obterClientePorId(Long id) {
        Cliente cliente = null;
        try {
            String sqlSelect = "SELECT * FROM tb_cliente c INNER JOIN tb_contato ct ON c.id_cliente = ct.id_cliente WHERE c.id_cliente = ?";
            PreparedStatement cmdSelect = conn.prepareStatement(sqlSelect);
            cmdSelect.setLong(1, id);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(id, rs.getString("nome_cliente"),
                        rs.getString("tipo_documento"),
                        rs.getLong("numero_documento"),
                        rs.getString("email"),
                        rs.getLong("telefone"));
            }
            cmdSelect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public Cliente obterClientePorNumeroDocumento(long numeroDocumento) {
        Cliente cliente = null;
        try {
            String sqlSelect = "SELECT * FROM tb_cliente c INNER JOIN tb_contato ct ON c.id_cliente = ct.id_cliente WHERE c.numero_documento = ?";
            PreparedStatement cmdSelect = conn.prepareStatement(sqlSelect);
            cmdSelect.setLong(1, numeroDocumento);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(rs.getLong("id_cliente"), rs.getString("nome_cliente"),
                        rs.getString("tipo_documento"),
                        rs.getLong("numero_documento"),
                        rs.getString("email"),
                        rs.getLong("telefone"));
            }
            cmdSelect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public void atualizar(Cliente cliente) {
        try {
            String sqlDelete = "UPDATE tb_cliente SET nome_cliente = ?, tipo_documento = ?, numero_documento = ? WHERE id_cliente = ?";
            PreparedStatement cmdDelete = conn.prepareStatement(sqlDelete);
            cmdDelete.setString(1, cliente.getNome());
            cmdDelete.setString(2, cliente.getTipoDocumento());
            cmdDelete.setLong(3, cliente.getNumeroDocumento());
            cmdDelete.setLong(4, cliente.getId());
            cmdDelete.execute();
            cmdDelete.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarContato(Cliente cliente) {
        try {
            String sqlDelete = "UPDATE tb_contato SET email = ?, telefone = ? WHERE id_cliente = ?";
            PreparedStatement cmdDelete = conn.prepareStatement(sqlDelete);
            cmdDelete.setString(1, cliente.getEmail());
            cmdDelete.setLong(2, cliente.getTelefone());
            cmdDelete.setLong(3, cliente.getId());
            cmdDelete.execute();
            cmdDelete.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarLogin(Login login, Long id) {
        try {
            String sqlDelete = "UPDATE tb_login SET login = ?, senha = ? WHERE id_cliente = ?";
            PreparedStatement cmdDelete = conn.prepareStatement(sqlDelete);
            cmdDelete.setString(1, login.getUsuario());
            cmdDelete.setString(2, login.getSenha());
            cmdDelete.setLong(3, id);
            cmdDelete.execute();
            cmdDelete.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Long id) {
        try {
            String sqlDelete = "DELETE FROM tb_cliente WHERE id_cliente = ?";
            PreparedStatement cmdDelete = conn.prepareStatement(sqlDelete);
            cmdDelete.setLong(1, id);
            cmdDelete.execute();
            cmdDelete.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long obterIdCliente() {
        long id = 0;
        String sqlSelect = "SELECT tb_cliente_id_cliente_seq.NEXTVAL FROM DUAL";
        try {
            PreparedStatement cmdSelect = conn.prepareStatement(sqlSelect);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id - 1;
    }

    public void fechar() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
