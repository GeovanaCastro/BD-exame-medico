package examemedico.dao;

import examemedico.connection.ConnectionModule;
import examemedico.exception.DatabaseException;
import examemedico.model.ExameResultado;
import examemedico.model.UnidadeMedida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static examemedico.connection.ConnectionModule.prepareStatement;

public class UnidadeMedidaDAO {

    private static final String INSERT = "INSERT INTO unidade_medida (descricao, sigla, tipo_exame_id, serie_exame_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM unidade_medida WHERE id = ?;";
    private static final String UPDATE = "UPDATE unidade_medida SET descricao = ?, sigla = ?, tipo_exame_id = ?, serie_exame_id = ? WHERE id = ?;";
    private static final String SELECT_BY_ID = "SELECT id, descricao, sigla, tipo_exame_id, serie_exame_id FROM unidade_medida WHERE id = ?;";
    private static final String SELECT = "SELECT * FROM unidade_medida;";

    public void createUnidadeMedida(UnidadeMedida unidadeMedida) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setString(1, unidadeMedida.getDescricao());
            preparedStatement.setString(2, unidadeMedida.getSigla());
            preparedStatement.executeUpdate();
            System.out.println("Unidade de Medida criada com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao criar unidade de medida no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void updateUnidadeMedida(UnidadeMedida unidadeMedida) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setString(2, unidadeMedida.getDescricao());
            preparedStatement.setString(3, unidadeMedida.getSigla());
            preparedStatement.setInt(1, unidadeMedida.getId());
            preparedStatement.executeUpdate();
            System.out.println("Unidade de Medida atualizada com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar unidade de medida no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void deleteUnidadeMedida(int id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Unidade de Medida deletada com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar unidade de medida do banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public UnidadeMedida getByIdUnidadeMedida(int id) throws SQLException {
        try (Connection connection = ConnectionModule.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new UnidadeMedida(
                            resultSet.getInt("id"),
                            resultSet.getString("descricao"),
                            resultSet.getString("sigla"),
                            resultSet.getInt("tipo_exame_id"),
                            resultSet.getInt("serie_exame_id")
                    );
                } else {
                    System.out.println("Nenhuma unidade de medida encontrada com ID: " + id);
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar unidade de medida no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public List<UnidadeMedida> getAllUnidadesMedida() throws SQLException {
        List<UnidadeMedida> unidadesMedida = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatement(SELECT)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UnidadeMedida unidadeMedida = new UnidadeMedida();
                unidadeMedida.setId(resultSet.getInt("id"));
                unidadeMedida.setDescricao(resultSet.getString("descricao"));
                unidadeMedida.setSigla(resultSet.getString("sigla"));

                unidadesMedida.add(unidadeMedida);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar unidades de medida no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }

        return unidadesMedida;
    }

}
