package br.com.carrepair.infra.dao;

import br.com.carrepair.dominio.Cliente;
import br.com.carrepair.dominio.ClienteDTO;
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

    public ClienteDTO autenticar(Login login) {
        ClienteDTO clienteDTO = new ClienteDTO();
        try {
            String sqlSelect = "SELECT id_cliente FROM tb_login WHERE login = ? AND senha = ?";
            PreparedStatement cmdSelect = conn.prepareStatement(sqlSelect);
            cmdSelect.setString(1, login.getUsuario());
            cmdSelect.setString(2, login.getSenha());
            ResultSet rs = cmdSelect.executeQuery();
            Long id = null;
            while (rs.next()) {
                id = rs.getLong(1);
            }
            cmdSelect.close();
            if (id == null) clienteDTO = null;
            else clienteDTO.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clienteDTO;
    }

    public void fechar() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
