package examemedico.dao;

import java.sql.*;
import examemedico.connection.ConnectionModule;
import examemedico.model.DetalheExameResultado;
import examemedico.model.SerieExame;
import examemedico.exception.DatabaseException;

public class DetalheExameResultadoSerieExameDAO {

    private static final String INSERT_DETALHE_EXAME_RESULTADO_SERIE_EXAME =
            "INSERT INTO detalhe_exame_resultado_serie_exame (detalhe_exame_resultado_id, serie_exame_id) VALUES (?, ?)";
    private static final String DELETE_DETALHE_EXAME_RESULTADO_SERIE_EXAME =
            "DELETE FROM detalhe_exame_resultado_serie_exame WHERE detalhe_exame_resultado_id = ? AND serie_exame_id = ?";
    private static final String SELECT_SERIE_EXAMES_BY_DETALHE_EXAME_RESULTADO =
            "SELECT s.id, s.descricao FROM serie_exame s INNER JOIN detalhe_exame_resultado_serie_exame derse ON s.id = derse.serie_exame_id WHERE derse.detalhe_exame_resultado_id = ?";

    // Inserir relacionamento na tabela de junção
    public void addSerieExameToDetalheExameResultado(int detalheExameResultadoId, int serieExameId) throws SQLException {
        try (Connection connection = ConnectionModule.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DETALHE_EXAME_RESULTADO_SERIE_EXAME)) {

            preparedStatement.setInt(1, detalheExameResultadoId);
            preparedStatement.setInt(2, serieExameId);
            preparedStatement.executeUpdate();
            System.out.println("Relacionamento inserido com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inserir relacionamento na tabela de junção", e, e.getErrorCode(), e.getSQLState());
        }
    }

    // Remover relacionamento na tabela de junção
    public void removeSerieExameFromDetalheExameResultado(int detalheExameResultadoId, int serieExameId) throws SQLException {
        try (Connection connection = ConnectionModule.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DETALHE_EXAME_RESULTADO_SERIE_EXAME)) {

            preparedStatement.setInt(1, detalheExameResultadoId);
            preparedStatement.setInt(2, serieExameId);
            preparedStatement.executeUpdate();
            System.out.println("Relacionamento deletado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar relacionamento da tabela de junção", e, e.getErrorCode(), e.getSQLState());
        }
    }

    // Obter série de exames associados a um detalhe de exame
    public void getSerieExamesByDetalheExameResultado(int detalheExameResultadoId) throws SQLException {
        try (Connection connection = ConnectionModule.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERIE_EXAMES_BY_DETALHE_EXAME_RESULTADO)) {

            preparedStatement.setInt(1, detalheExameResultadoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int serieExameId = resultSet.getInt("id");
                    String descricao = resultSet.getString("descricao");
                    System.out.println("Série Exame ID: " + serieExameId + " | Descrição: " + descricao);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar séries de exames pelo detalhe de exame", e, e.getErrorCode(), e.getSQLState());
        }
    }
}
