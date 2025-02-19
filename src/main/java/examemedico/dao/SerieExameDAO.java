package examemedico.dao;

import examemedico.connection.ConnectionModule;
import examemedico.exception.DatabaseException;
import examemedico.model.SerieExame;

import java.sql.*;

import static examemedico.connection.ConnectionModule.prepareStatement;

public class SerieExameDAO {

    private static final String INSERT = "INSERT INTO serie_exame (descricao) VALUES (?)";
    private static final String DELETE = "DELETE FROM serie_exame WHERE id = ?;";
    private static final String UPDATE = "UPDATE serie_exame SET descricao = ? WHERE id = ?;";
    private static final String SELECT_BY_ID = "SELECT id, descricao FROM serie_exame WHERE id = ?;";
    private static final String SELECT = "SELECT * FROM serie_exame;";

    public void createSerieExame(SerieExame serieExame) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setString(1, serieExame.getDescricao());
            preparedStatement.executeUpdate();
            System.out.println("Série de Exame criada com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao criar série de exame no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void updateSerieExame(SerieExame serieExame) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setString(1, serieExame.getDescricao());
            preparedStatement.setInt(2, serieExame.getId());
            preparedStatement.executeUpdate();
            System.out.println("Série de Exame atualizada com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar série de exame no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void deleteSerieExame(int id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Série de Exame deletada com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar série de exame do banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public SerieExame getByIdSerieExame(int id) throws SQLException {
        try (Connection connection = ConnectionModule.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new SerieExame(
                            resultSet.getInt("id"),
                            resultSet.getString("descricao")
                    );
                } else {
                    System.out.println("Nenhuma série de exame encontrada com ID: " + id);
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar série de exame no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void findAllSerieExame() throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(SELECT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Nenhuma série de exame encontrada.");
            } else {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") +
                            " | Descrição: " + resultSet.getString("descricao"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todas as séries de exame no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }
}
