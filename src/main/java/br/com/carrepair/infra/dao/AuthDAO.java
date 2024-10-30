package br.com.carrepair.infra.dao;

import br.com.carrepair.dominio.Cliente;
import br.com.carrepair.dominio.Login;
import br.com.carrepair.dominio.RepositorioAuth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO implements RepositorioAuth {

    private Connection conn;

    public AuthDAO() {
        conn = ConnectionFactory.obterConexao();
    }

    public Cliente autenticar(Login login) {
        Cliente cliente = null;
        try {
            String sqlSelect = "SELECT id_cliente FROM tb_login WHERE login = ? AND senha = ?";
            PreparedStatement cmdSelect = conn.prepareStatement(sqlSelect);
            cmdSelect.setString(1, login.getUsuario());
            cmdSelect.setString(2, login.getSenha());
            ResultSet rs = cmdSelect.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            cmdSelect.close();
            cliente = obterClientePorId(id);
            if (cliente != null) cliente.setLogin(login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public Cliente obterClientePorId(long id) {
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

    public void fechar() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
