package br.com.carrepair.infra.dao;

import br.com.carrepair.dominio.Diagnostico;
import br.com.carrepair.dominio.RepositorioDiagnostico;

import java.sql.*;

public class DiagnosticoDAO implements RepositorioDiagnostico {

    private Connection conn;

    public DiagnosticoDAO() {
        this.conn = ConnectionFactory.obterConexao();
    }

    public void adicionar(Diagnostico diagnostico, Long idVeiculo) {
        try {
            String sql = "INSERT INTO tb_diagnostico(id_veiculo, descricao_problema, data_hora_diagnostico) VALUES(?,?,?)";
            PreparedStatement cmdInsert = conn.prepareStatement(sql);
            cmdInsert.setLong(1,  idVeiculo);
            cmdInsert.setString(2, diagnostico.getDescricaoProblema());
            cmdInsert.setTimestamp(3, dataHoraAtual());
            cmdInsert.execute();
            cmdInsert.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Timestamp dataHoraAtual(){
        try {
            String sqlSelect = "SELECT CURRENT_TIMESTAMP FROM dual";
            PreparedStatement cmdSelect = conn.prepareStatement(sqlSelect);
            ResultSet rs = cmdSelect.executeQuery();
            Timestamp dataHora = null;
            while (rs.next()) {
                dataHora = rs.getTimestamp(1);
            }
            rs.close();
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
