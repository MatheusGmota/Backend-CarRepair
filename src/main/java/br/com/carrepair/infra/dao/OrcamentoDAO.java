package br.com.carrepair.infra.dao;

import br.com.carrepair.dominio.ItemOrcamento;
import br.com.carrepair.dominio.Orcamento;
import br.com.carrepair.dominio.RepositorioOrcamentos;

import java.sql.*;

public class OrcamentoDAO implements RepositorioOrcamentos {
    private Connection conn;

    public OrcamentoDAO() {
        conn = ConnectionFactory.obterConexao();
    }

    public void adicionar(Orcamento orcamento) {
        String sql = "INSERT INTO tb_orcamento(id_cliente, id_veiculo, valor_total_orcamento, data_hora_orcamento, prc_hora_mecanico) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement cmdInsert = conn.prepareStatement(sql);
            cmdInsert.setLong(1, orcamento.getIdCliente());
            cmdInsert.setLong(2, orcamento.getIdVeiculo());
            cmdInsert.setDouble(3, orcamento.getValorTotal());
            cmdInsert.setTimestamp(4, dataHoraAtual());
            cmdInsert.setDouble(5, orcamento.getPrecoHoraMecanico());
            cmdInsert.execute();
            cmdInsert.close();
            orcamento.setIdOrcamento(obterIdOrcamento());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarItem(ItemOrcamento itemOrcamento, Long idOrcamento) {
        String sql = "INSERT INTO tb_item_orcamento(id_orcamento, id_peca, quantidade, valor_total) VALUES(?,?,?,?)";
        try {
            PreparedStatement cmdInsert = conn.prepareStatement(sql);
            cmdInsert.setLong(1, idOrcamento);
            cmdInsert.setLong(2, itemOrcamento.getIdPeca());
            cmdInsert.setInt(3, itemOrcamento.getQuantidade());
            cmdInsert.setDouble(4, itemOrcamento.getValorTotal());
            cmdInsert.execute();
            cmdInsert.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarItem(ItemOrcamento itemOrcamento, Long idOrcamento) {
        String sql = "UPDATE tb_item_orcamento SET quantidade = ?, valor_total = ? WHERE id_item_orcamento = ?";
        try {
            PreparedStatement cmdInsert = conn.prepareStatement(sql);
            cmdInsert.setInt(1, itemOrcamento.getQuantidade());
            cmdInsert.setDouble(2, itemOrcamento.getValorTotal());
            cmdInsert.setLong(3, getIdItemOrcamento(idOrcamento, itemOrcamento.getIdPeca()));
            cmdInsert.execute();
            cmdInsert.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getIdItemOrcamento(Long idOrcamento, Long idPeca) {
        long id = 0;
        String sql = "SELECT id_item_orcamento FROM tb_item_orcamento WHERE id_orcamento = ? AND id_peca = ?";
        try {
            PreparedStatement cmdSelect = conn.prepareStatement(sql);
            cmdSelect.setLong(1, idOrcamento);
            cmdSelect.setLong(2, idPeca);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id-1;
    }

    public void atualizar(Orcamento orcamento) {
        String sql = "UPDATE tb_orcamento SET valor_total_orcamento = ?, data_hora_orcamento = ?, prc_hora_mecanico = ? WHERE id_orcamento = ?";
        try {
            PreparedStatement cmdUpdate = conn.prepareStatement(sql);
            cmdUpdate.setDouble(1, orcamento.getValorTotal());
            cmdUpdate.setTimestamp(2, orcamento.getDataHora());
            cmdUpdate.setDouble(3, orcamento.getPrecoHoraMecanico());
            cmdUpdate.setLong(4, orcamento.getIdOrcamento());
            cmdUpdate.execute();
            cmdUpdate.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Orcamento obterPorId(Long idOrcamento) {
        Orcamento orcamento = null;
        String sqlSelect = "SELECT * FROM tb_orcamento WHERE id_orcamento = ?";
        try {
            PreparedStatement cmdSelect = conn.prepareStatement(sqlSelect);
            cmdSelect.setLong(1, idOrcamento);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                orcamento = new Orcamento(
                        rs.getLong("id_orcamento"),
                        rs.getLong("id_cliente"),
                        rs.getLong("id_veiculo"),
                        rs.getDouble("valor_total_orcamento"),
                        rs.getDouble("prc_hora_mecanico"),
                        rs.getTimestamp("data_hora_orcamento"), null);
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return orcamento;
    }

    public void deletar(Long idOrcamento) {
        String sqlDelete = "DELETE FROM tb_orcamento WHERE id_orcamento = ?";
        try {
            PreparedStatement cmdDelete = conn.prepareStatement(sqlDelete);
            cmdDelete.setLong(1, idOrcamento);
            cmdDelete.execute();
            cmdDelete.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Long obterIdOrcamento() {
        long id = 0;
        String sql = "SELECT tb_orcamento_id_orcamento_seq.NEXTVAL FROM DUAL";
        try {
            PreparedStatement cmdSelect = conn.prepareStatement(sql);
            ResultSet rs = cmdSelect.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id-1;
    }

    private Timestamp dataHoraAtual(){
        try {
            String sql = "SELECT CURRENT_TIMESTAMP FROM dual";
            PreparedStatement cmdSelect = conn.prepareStatement(sql);
            ResultSet rs = cmdSelect.executeQuery();
            Timestamp dataHora = null;
            while (rs.next()) {
                dataHora = rs.getTimestamp(1);
            }
            cmdSelect.close();
            return dataHora;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fechar() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
