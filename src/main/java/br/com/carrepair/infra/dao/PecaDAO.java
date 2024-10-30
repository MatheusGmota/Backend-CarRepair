package br.com.carrepair.infra.dao;

import br.com.carrepair.dominio.Peca;
import br.com.carrepair.dominio.RepositorioPecas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PecaDAO implements RepositorioPecas {

    private Connection conn;

    public PecaDAO() {
        this.conn = ConnectionFactory.obterConexao();
    }

    public ArrayList<Peca> obterTodas() {
        ArrayList<Peca> pecas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_pecas";
            PreparedStatement cmdSelect = conn.prepareStatement(sql);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                Peca peca = new Peca(rs.getLong("id_peca"),
                                rs.getString("nome_peca"),
                                rs.getString("codigo_peca"),
                                rs.getDouble("valor_peca"));
                pecas.add(peca);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pecas;
    }


    public void adicionar(Peca peca) {
        try {
            String sql = "INSERT INTO TB_PECAS(nome_peca, codigo_peca, valor_peca) VALUES(?, ?, ?)";
            PreparedStatement cmdInsert = conn.prepareStatement(sql);
            cmdInsert.setString(1, peca.getNome());
            cmdInsert.setString(2, peca.getCodigo());
            cmdInsert.setDouble(3, peca.getValorUnitario());
            cmdInsert.execute();
            cmdInsert.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Peca obterPorId(Long id) {
        Peca peca = null;
        try {
            String sql = "SELECT * FROM tb_pecas WHERE id_peca = ?";
            PreparedStatement cmdSelect = conn.prepareStatement(sql);
            cmdSelect.setLong(1,id);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                 peca = new Peca(rs.getLong("id_peca"),
                        rs.getString("nome_peca"),
                        rs.getString("codigo_peca"),
                        rs.getDouble("valor_peca"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (peca == null) throw new RuntimeException("Peça não existe no banco de dados");
        else return peca;
    }

    public void atualizar(Peca peca) {
        try {
            String sqlDelete = "UPDATE tb_pecas SET nome_peca = ?, codigo_peca = ?, valor_peca = ? WHERE id_peca = ?";
            PreparedStatement cmdDelete = conn.prepareStatement(sqlDelete);
            cmdDelete.setString(1, peca.getNome());
            cmdDelete.setString(2, peca.getCodigo());
            cmdDelete.setDouble(3, peca.getValorUnitario());
            cmdDelete.setLong(4, peca.getId());
            cmdDelete.execute();
            cmdDelete.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Long id) {
        try {
            String sqlDelete = "DELETE FROM tb_pecas WHERE id_peca = ?";
            PreparedStatement cmdDelete = conn.prepareStatement(sqlDelete);
            cmdDelete.setLong(1, id);
            cmdDelete.execute();
            cmdDelete.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fechar() {
        try {
            conn.close();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

}
