package examemedico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static examemedico.connection.ConnectionModule.prepareStatement;
import examemedico.exception.DatabaseException;
import examemedico.model.*;

public class DetalheSerieExameDAO {

    private static final String INSERT = "INSERT INTO detalhe_serie_exame (descricao, serie_exame_id, valor_referencia_id, unidade_medida_id, detalhe_exame_resultado_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM detalhe_serie_exame WHERE id = ?";
    private static final String UPDATE = "UPDATE detalhe_serie_exame SET descricao = ?, serie_exame_id = ?, valor_referencia_id = ?, unidade_medida_id = ?, detalhe_exame_resultado_id = ? WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM detalhe_serie_exame WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM detalhe_serie_exame";

    public void create(DetalheSerieExame detalhe) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setString(1, detalhe.getDescricao());
            preparedStatement.setInt(2, detalhe.getSerieExame().getId());
            preparedStatement.setInt(3, detalhe.getValorReferencia().getId());
            preparedStatement.setInt(4, detalhe.getUnidadeMedida().getId());
            preparedStatement.setInt(5, detalhe.getDetalheExameResultado().getId());
            preparedStatement.executeUpdate();
            System.out.println("Detalhe de série de exame cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao cadastrar detalhe de série de exame", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public List<DetalheSerieExame> getAll() throws SQLException {
        List<DetalheSerieExame> detalhes = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DetalheSerieExame detalhe = mapResultSetToDetalhe(resultSet);
                detalhes.add(detalhe);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar detalhes de série de exame", e, e.getErrorCode(), e.getSQLState());
        }
        return detalhes;
    }

    public DetalheSerieExame findById(int id) throws SQLException {
        DetalheSerieExame detalhe = null;
        try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                detalhe = mapResultSetToDetalhe(resultSet);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar detalhe de série de exame pelo ID", e, e.getErrorCode(), e.getSQLState());
        }
        return detalhe;
    }

    public void update(DetalheSerieExame detalhe) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setString(1, detalhe.getDescricao());
            preparedStatement.setInt(2, detalhe.getSerieExame().getId());
            preparedStatement.setInt(3, detalhe.getValorReferencia().getId());
            preparedStatement.setInt(4, detalhe.getUnidadeMedida().getId());
            preparedStatement.setInt(5, detalhe.getDetalheExameResultado().getId());
            preparedStatement.setInt(6, detalhe.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Detalhe de série de exame atualizado com sucesso!");
            } else {
                System.out.println("Nenhum detalhe encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar detalhe de série de exame", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void delete(int id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Detalhe de série de exame excluído com sucesso!");
            } else {
                System.out.println("Nenhum detalhe encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao excluir detalhe de série de exame", e, e.getErrorCode(), e.getSQLState());
        }
    }

    private DetalheSerieExame mapResultSetToDetalhe(ResultSet resultSet) throws SQLException {
        DetalheSerieExame detalhe = new DetalheSerieExame();
        detalhe.setId(resultSet.getInt("id"));
        detalhe.setDescricao(resultSet.getString("descricao"));

        SerieExame serieExame = new SerieExame();
        serieExame.setId(resultSet.getInt("serie_exame_id"));
        detalhe.setSerieExame(serieExame);

        ValorReferencia valorReferencia = new ValorReferencia();
        valorReferencia.setId(resultSet.getInt("valor_referencia_id"));
        detalhe.setValorReferencia(valorReferencia);

        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(resultSet.getInt("unidade_medida_id"));
        detalhe.setUnidadeMedida(unidadeMedida);

        DetalheExameResultado detalheExameResultado = new DetalheExameResultado();
        detalheExameResultado.setId(resultSet.getInt("detalhe_exame_resultado_id"));
        detalhe.setDetalheExameResultado(detalheExameResultado);

        return detalhe;
    }
}