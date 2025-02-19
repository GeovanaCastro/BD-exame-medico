package examemedico.dao;

import examemedico.exception.DatabaseException;
import examemedico.model.ExameResultado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static examemedico.connection.ConnectionModule.prepareStatement;

public class ExameResultadoDAO {

    private static final String INSERT = "INSERT INTO exame_resultado (data_coleta, data_resultado, Pessoa_id, tipo_exame_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM exame_resultado WHERE id = ?;";
    private static final String UPDATE = "UPDATE exame_resultado SET data_coleta = ?, data_resultado = ?, Pessoa_id = ?, tipo_exame_id = ? WHERE id = ?;";
    private static final String SELECT_BY_ID = "SELECT * FROM exame_resultado WHERE id = ?;";
    private static final String SELECT = "SELECT * FROM exame_resultado;";
    private static final String SELECT_BY_PESSOA_ID = "SELECT * FROM exame_resultado WHERE Pessoa_id = ?;";
    private static final String SELECT_BY_TIPO_EXAME_ID = "SELECT * FROM exame_resultado WHERE tipo_exame_id = ?;";

    public void createExameResultado(ExameResultado exameResultado) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setTimestamp(1, exameResultado.getDataColeta());
            preparedStatement.setTimestamp(2, exameResultado.getDataResultado());
            preparedStatement.setInt(3, exameResultado.getPessoaId());
            preparedStatement.setInt(4, exameResultado.getTipoExameId());
            preparedStatement.executeUpdate();
            System.out.println("Resultado de exame criado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao criar resultado de exame no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public List<ExameResultado> getAllExameResultados() throws SQLException {
        List<ExameResultado> exameResultados = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatement(SELECT)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                ExameResultado exameResultado = new ExameResultado();
                exameResultado.setId(result.getInt("id"));
                exameResultado.setDataColeta(result.getTimestamp("data_coleta"));
                exameResultado.setDataResultado(result.getTimestamp("data_resultado"));
                exameResultado.setPessoaId(result.getInt("Pessoa_id"));
                exameResultado.setTipoExameId(result.getInt("tipo_exame_id"));
                exameResultados.add(exameResultado);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar resultados de exames no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
        return exameResultados;
    }

    public ExameResultado getExameResultadoById(int id) throws SQLException {
        ExameResultado exameResultado = null;
        try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                exameResultado = new ExameResultado();
                exameResultado.setId(resultSet.getInt("id"));
                exameResultado.setDataColeta(resultSet.getTimestamp("data_coleta"));
                exameResultado.setDataResultado(resultSet.getTimestamp("data_resultado"));
                exameResultado.setPessoaId(resultSet.getInt("Pessoa_id"));
                exameResultado.setTipoExameId(resultSet.getInt("tipo_exame_id"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar resultado de exame pelo ID no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
        return exameResultado;
    }

    public List<ExameResultado> getExameResultadosByPessoaId(int pessoaId) throws SQLException {
        List<ExameResultado> exameResultados = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_PESSOA_ID)) {
            preparedStatement.setInt(1, pessoaId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                ExameResultado exameResultado = new ExameResultado();
                exameResultado.setId(result.getInt("id"));
                exameResultado.setDataColeta(result.getTimestamp("data_coleta"));
                exameResultado.setDataResultado(result.getTimestamp("data_resultado"));
                exameResultado.setPessoaId(result.getInt("Pessoa_id"));
                exameResultado.setTipoExameId(result.getInt("tipo_exame_id"));
                exameResultados.add(exameResultado);
            }

            if (exameResultados.isEmpty()) {
                System.out.println("Nenhum resultado de exame encontrado para a pessoa com ID " + pessoaId);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar resultados de exames pela pessoa no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
        return exameResultados;
    }

    public List<ExameResultado> getExameResultadosByTipoExameId(int tipoExameId) throws SQLException {
        List<ExameResultado> exameResultados = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_TIPO_EXAME_ID)) {
            preparedStatement.setInt(1, tipoExameId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                ExameResultado exameResultado = new ExameResultado();
                exameResultado.setId(result.getInt("id"));
                exameResultado.setDataColeta(result.getTimestamp("data_coleta"));
                exameResultado.setDataResultado(result.getTimestamp("data_resultado"));
                exameResultado.setPessoaId(result.getInt("Pessoa_id"));
                exameResultado.setTipoExameId(result.getInt("tipo_exame_id"));
                exameResultados.add(exameResultado);
            }

            if (exameResultados.isEmpty()) {
                System.out.println("Nenhum resultado de exame encontrado para o tipo de exame com ID " + tipoExameId);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar resultados de exames pelo tipo de exame no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
        return exameResultados;
    }

    public void deleteExameResultado(int id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Resultado de exame excluído com sucesso!");
            } else {
                System.out.println("Nenhum resultado de exame encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao excluir resultado de exame do banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void updateExameResultado(ExameResultado exameResultado) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setTimestamp(1, exameResultado.getDataColeta());
            preparedStatement.setTimestamp(2, exameResultado.getDataResultado());
            preparedStatement.setInt(3, exameResultado.getPessoaId());
            preparedStatement.setInt(4, exameResultado.getTipoExameId());
            preparedStatement.setInt(5, exameResultado.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Resultado de exame atualizado com sucesso!");
            } else {
                System.out.println("Nenhum resultado de exame encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar resultado de exame no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }
}
