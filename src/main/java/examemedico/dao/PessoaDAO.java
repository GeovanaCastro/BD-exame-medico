package examemedico.dao;

import examemedico.connection.ConnectionModule;
import examemedico.exception.DatabaseException;
import examemedico.model.Pessoa;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static examemedico.connection.ConnectionModule.prepareStatement;

public class PessoaDAO{

    private static final String INSERT = "INSERT INTO Pessoa (nome, idade) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM Pessoa WHERE id = ?;";
    private static final String UPDATE = "UPDATE Pessoa SET nome = ? WHERE id = ?;";
    private static final String SELECT_BY_ID = "SELECT id, nome FROM Pessoa WHERE id = ?;";
    private static final String SELECT = "SELECT * FROM Pessoa;";

    public void createPessoa(Pessoa pessoa) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setInt(2, pessoa.getIdade());
            preparedStatement.executeUpdate();
            System.out.println("Pessoa criada com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao criar pessoa no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }
}
