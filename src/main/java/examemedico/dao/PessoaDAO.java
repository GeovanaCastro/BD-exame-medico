package examemedico.dao;

import examemedico.exception.DatabaseException;
import examemedico.model.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static examemedico.connection.ConnectionModule.prepareStatement;

public class PessoaDAO{

    private static final String INSERT = "INSERT INTO Pessoa (nome, idade) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM Pessoa WHERE id = ?;";
    private static final String UPDATE = "UPDATE Pessoa SET nome = ? WHERE id = ?;";
    private static final String SELECT_BY_ID = "SELECT id, nome FROM Pessoa WHERE id = ?;";
    private static final String SELECT = "SELECT * FROM Pessoa;";
    private static final String SELECT_PERSON_AGE = "SELECT * FROM Pessoa WHERE idade = ?";


    public void createPessoa(Pessoa pessoa) throws SQLException {

        try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setInt(2, pessoa.getIdade());
            preparedStatement.executeUpdate();
            System.out.println(" Pessoa criada com sucesso!");

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao criar  pessoa no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public List<Pessoa> getAllPessoas() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatement(SELECT)) {

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getInt("id"));
                pessoa.setNome(result.getString("nome"));
                pessoa.setIdade(result.getInt("idade"));
                pessoas.add(pessoa);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar pessoas no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
        return pessoas;
    }

    public Pessoa getPessoaById(int id) throws SQLException {
        Pessoa pessoa = null;
        try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pessoa = new Pessoa();
                pessoa.setId(resultSet.getInt("id"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setIdade(resultSet.getInt("idade"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar pessoa pelo ID no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
        return pessoa;
    }

    public void deletePessoa(int id) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pessoa excluída com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao excluir pessoa do banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public void updatePessoa(Pessoa pessoa) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setInt(2, pessoa.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pessoa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar pessoa no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
    }

    public List<Pessoa> getPessoasByIdade(int idade) throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepareStatement(SELECT_PERSON_AGE)) {
            preparedStatement.setInt(1, idade);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(result.getInt("id"));
                pessoa.setNome(result.getString("nome"));
                pessoa.setIdade(result.getInt("idade"));
                pessoas.add(pessoa);
            }

            if (pessoas.isEmpty()) {
                System.out.println("Nenhuma pessoa encontrada com a idade " + idade);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar pessoas pela idade no banco de dados", e, e.getErrorCode(), e.getSQLState());
        }
        return pessoas;
    }

}
