package examemedico.dao;

import examemedico.connection.ConnectionModule;
import examemedico.exception.DatabaseException;
import examemedico.model.ValorReferencia;

import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static examemedico.connection.ConnectionModule.prepareStatement;

public class ValorReferenciaDAO {

    private static final String INSERT = "INSERT INTO valor_referencia (valor_referencia_por, valor_referencia_abs) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM valor_referencia WHERE id = ?;";
    private static final String UPDATE = "UPDATE valor_referencia SET valor_referencia_por = ?, valor_referencia_abs = ? WHERE id = ?;";
    private static final String SELECT_BY_ID = "SELECT id, valor_referencia_por, valor_referencia_abs FROM valor_referencia WHERE id = ?;";
    private static final String SELECT = "SELECT * FROM valor_referencia;";

    public void createValorReferencia(ValorReferencia valorReferencia) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setString(1, valorReferencia.getValor_referencia_por());
            preparedStatement.setString(2, valorReferencia.getValor_referencia_abs());
            preparedStatement.executeUpdate();
            System.out.println("Valor Referencia criado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao criar valor referencia no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void updateValorReferencia(ValorReferencia valorReferencia) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setString(1, valorReferencia.getValor_referencia_por());
            preparedStatement.setString(2, valorReferencia.getValor_referencia_abs());
            preparedStatement.setInt(3, valorReferencia.getId());
            preparedStatement.executeUpdate();
            System.out.println("Valor Referencia atualizado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar valor referencia no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void deleteValorReferencia(ValorReferencia valorReferencia) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, valorReferencia.getId());
            preparedStatement.executeUpdate();
            System.out.println("Valor Referencia deletado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao tentar deletar valor referencia do banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public ValorReferencia getByIdValorReferencia(int id) throws SQLException {
        try (Connection connection = ConnectionModule.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                   ValorReferencia valor_referencia = new ValorReferencia(
                            resultSet.getInt("id"),
                            resultSet.getString("valor_referencia_por"),
                            resultSet.getString("valor_referencia_abs")
                    );
                    System.out.println("Valores encontrados: \nValor Referencia por = " + valor_referencia.getValor_referencia_por()+",\nValor Referencia abs = " + valor_referencia.getValor_referencia_abs());
                    return valor_referencia;
                } else {
                    System.out.println("Nenhum valor referencia encontrado com ID: " + id);
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao procurar valor referencia no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void findAllValorReferencia() throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(SELECT);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Nenhum registro encontrado na tabela valor_referencia");
            } else {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id")+" | Valor Referencia por: "+ resultSet.getString("valor_referencia_por")+" | Valor Referencia abs: "+ resultSet.getString("valor_referencia_abs"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar todos os valores referencia no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }
}
