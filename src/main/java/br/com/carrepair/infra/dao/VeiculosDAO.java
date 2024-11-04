package br.com.carrepair.infra.dao;

import br.com.carrepair.dominio.Orcamento;
import br.com.carrepair.dominio.RepositorioVeiculos;
import br.com.carrepair.dominio.Veiculos;

import java.sql.*;

public class VeiculosDAO implements RepositorioVeiculos {

    private Connection conn;

    public VeiculosDAO() {
        this.conn = ConnectionFactory.obterConexao();
    }

    public void adicionar(Veiculos veiculos, Long idCliente) {
        try {
            String sql = "INSERT INTO tb_veiculos(id_cliente, marca_veiculo, modelo_veiculo, ano_veiculo, quilometragem_veiculo) VALUES(?,?,?,?,?)";
            PreparedStatement cmdInsert = conn.prepareStatement(sql);
            cmdInsert.setLong(1, idCliente);
            cmdInsert.setString(2, veiculos.getMarca());
            cmdInsert.setString(3, veiculos.getModelo());
            cmdInsert.setInt(4, veiculos.getAno());
            cmdInsert.setInt(5, veiculos.getQuilometragem());
            cmdInsert.execute();
            cmdInsert.close();
            veiculos.setIdVeiculo(obterIdVeiculo(idCliente));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Veiculos veiculos) {
        try {
            String sqlUpdate = "UPDATE tb_veiculos SET marca_veiculo = ?, modelo_veiculo = ?, ano_veiculo = ?, quilometragem_veiculo = ? WHERE id_veiculo = ?";
            PreparedStatement cmdUpdate = conn.prepareStatement(sqlUpdate);
            cmdUpdate.setString(1, veiculos.getMarca());
            cmdUpdate.setString(2, veiculos.getModelo());
            cmdUpdate.setInt(3, veiculos.getAno());
            cmdUpdate.setLong(4, veiculos.getQuilometragem());
            cmdUpdate.setLong(5, veiculos.getIdVeiculo());
            cmdUpdate.execute();
            cmdUpdate.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long obterIdVeiculo(Long idCliente) {
        Long id = null;
        try {
            String sql = "SELECT id_veiculo FROM tb_veiculos WHERE id_cliente = ?";
            PreparedStatement cmdInsert = conn.prepareStatement(sql);
            cmdInsert.setLong(1, idCliente);
            ResultSet rs = cmdInsert.executeQuery();
            while (rs.next()) {
                id = rs.getLong("id_veiculo");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public Veiculos obterVeiculo(Long idCliente) {
        Veiculos veiculo = null;
        String sql = "SELECT * FROM tb_veiculos WHERE id_cliente = ?";
        try {
            PreparedStatement cmdSelect = conn.prepareStatement(sql);
            cmdSelect.setLong(1, idCliente);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                veiculo = new Veiculos(rs.getLong(1),
                        rs.getString("marca_veiculo"),
                        rs.getString("modelo_veiculo"),
                        rs.getInt("ano_veiculo"),
                        rs.getInt("quilometragem_veiculo"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veiculo;
    }

    public void fechar() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
