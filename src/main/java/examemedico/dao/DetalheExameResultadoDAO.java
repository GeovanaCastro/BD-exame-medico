package examemedico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import examemedico.connection.ConnectionModule;
import static examemedico.connection.ConnectionModule.prepareStatement;
import examemedico.exception.DatabaseException;
import examemedico.model.DetalheExameResultado;

public class DetalheExameResultadoDAO {

    private static final String INSERT = "INSERT INTO detalhe_exame_resultado (valor_amostrado, exame_resultado_id) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM detalhe_exame_resultado WHERE id = ?;";
    private static final String UPDATE = "UPDATE detalhe_exame_resultado SET valor_amostrado = ? WHERE id = ?;";
    private static final String SELECT_BY_ID = "SELECT id, valor_amostrado, exame_resultado_id FROM detalhe_exame_resultado WHERE id = ?;";
    private static final String SELECT = "SELECT * FROM detalhe_exame_resultado;";

    public void createDetalheExameResultado(DetalheExameResultado detalheExameResultado) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setString(1, detalheExameResultado.getValor_amostrado());
            preparedStatement.setInt(2, detalheExameResultado.getExame_resultado_id());
            preparedStatement.executeUpdate();
            System.out.println("Detalhe de resultado de exame criado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao criar detalhe de resultado de exame no banco de dados", e,
                    e.getErrorCode(), e.getSQLState());
        }
    }

    public void updateDetalheExameResultado(DetalheExameResultado detalheExameResultado) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setString(1, detalheExameResultado.getValor_amostrado());
            preparedStatement.setInt(2, detalheExameResultado.getId());
            preparedStatement.executeUpdate();
            System.out.println("Detalhe de resultado de exame atualizado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar detalhe de resultado de exame no banco de dados", e,
                    e.getErrorCode(), e.getSQLState());
        }
    }

    public void deleteDetalheExameResultado(DetalheExameResultado detalheExameResultado) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, detalheExameResultado.getId());
            preparedStatement.executeUpdate();
            System.out.println("Detalhe de resultado de exame deletado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao tentar deletar detalhe de resultado de exame do banco de dados", e,
                    e.getErrorCode(), e.getSQLState());
        }
    }

    public DetalheExameResultado getByIdDetalheExameResultado(int id) throws SQLException {
        try (Connection connection = ConnectionModule.connection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    DetalheExameResultado detalhe = new DetalheExameResultado(
                            resultSet.getInt("id"),
                            resultSet.getString("valor_amostrado"),
                            resultSet.getInt("exame_resultado_id"));

                    System.out.println("Detalhe encontrado, Valor amostrado = " + detalhe.getValor_amostrado());
                    return detalhe;
                } else {
                    System.out.println("Nenhum detalhe encontrado com ID: " + id);
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao procurar detalhe de resultado de exame no banco de dados", e,
                    e.getErrorCode(), e.getSQLState());
        }
    }

    public List<DetalheExameResultado> findAllDetalheExameResultado() throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(SELECT);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Nenhum registro encontrado na tabela detalhe_exame_resultado.");
            } else {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") + " | Valor Amostrado: "
                            + resultSet.getString("valor_amostrado") + " | Exame Resultado ID: "
                            + resultSet.getInt("exame_resultado_id"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todos os detalhes de resultado de exame no banco de dados", e,
                    e.getErrorCode(), e.getSQLState());
        }
        return List.of();
    }
}
