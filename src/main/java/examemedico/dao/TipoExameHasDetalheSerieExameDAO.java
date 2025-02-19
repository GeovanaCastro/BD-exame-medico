package examemedico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static examemedico.connection.ConnectionModule.prepareStatement;
import examemedico.exception.DatabaseException;
import examemedico.model.TipoExame;
import examemedico.model.DetalheExameResultado;

public class TipoExameHasDetalheSerieExameDAO {

    private static final String INSERT = "INSERT INTO tipo_exame_has_detalhe_serie_exame (tipo_exame_id, detalhe_exame_resultado_id) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM tipo_exame_has_detalhe_serie_exame WHERE tipo_exame_id = ? AND detalhe_exame_resultado_id = ?";
    private static final String UPDATE = "UPDATE tipo_exame_has_detalhe_serie_exame SET detalhe_exame_resultado_id = ? WHERE tipo_exame_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM tipo_exame_has_detalhe_serie_exame WHERE tipo_exame_id = ? AND detalhe_exame_resultado_id = ?";
    private static final String SELECT = "SELECT * FROM tipo_exame_has_detalhe_serie_exame WHERE tipo_exame_id = ?";

    public void addRelation(TipoExame tipoExame, DetalheExameResultado detalheExameResultado) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setInt(1, tipoExame.getId());
            preparedStatement.setInt(2, detalheExameResultado.getId());
            preparedStatement.executeUpdate();
            System.out.println("Relacionamento cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao cadastrar relacionamento", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public List<DetalheExameResultado> getDetalhesByTipoExame(int tipoExameId) throws SQLException {
        List<DetalheExameResultado> detalhes = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatement(SELECT)) {
            preparedStatement.setInt(1, tipoExameId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                DetalheExameResultado detalhe = new DetalheExameResultado();
                detalhe.setId(result.getInt("detalhe_exame_resultado_id"));
                detalhes.add(detalhe);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar detalhes do exame", e, e.getErrorCode(), e.getSQLState());
        }
        return detalhes;
    }

    public DetalheExameResultado findById(int tipoExameId, int detalheExameResultadoId) throws SQLException {
        DetalheExameResultado detalhe = null;
        try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, tipoExameId);
            preparedStatement.setInt(2, detalheExameResultadoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                detalhe = new DetalheExameResultado();
                detalhe.setId(resultSet.getInt("detalhe_exame_resultado_id"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar relacionamento pelo ID", e, e.getErrorCode(), e.getSQLState());
        }
        return detalhe;
    }

    public void updateRelation(int tipoExameId, int newDetalheExameResultadoId) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, newDetalheExameResultadoId);
            preparedStatement.setInt(2, tipoExameId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relacionamento atualizado com sucesso!");
            } else {
                System.out.println("Nenhum relacionamento encontrado com os IDs fornecidos.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar relacionamento", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void removeRelation(int tipoExameId, int detalheExameResultadoId) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, tipoExameId);
            preparedStatement.setInt(2, detalheExameResultadoId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relacionamento removido com sucesso!");
            } else {
                System.out.println("Nenhum relacionamento encontrado com os IDs fornecidos.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao remover relacionamento", e, e.getErrorCode(), e.getSQLState());
        }
    }
}
