package examemedico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static examemedico.connection.ConnectionModule.prepareStatement;
import examemedico.exception.DatabaseException;
import examemedico.model.TipoExame;

public class TipoExameDAO {

  private static final String INSERT = "INSERT INTO tipo_exame (descricao, material_coleta_id) VALUES (?, ?)";
  private static final String DELETE = "DELETE FROM tipo_exame WHERE id = ?";
  private static final String UPDATE = "UPDATE tipo_exame SET descricao = ?, material_coleta_id = ? WHERE id = ?";
  private static final String SELECT_BY_ID = "SELECT * FROM tipo_exame WHERE id = ?";
  private static final String SELECT = "SELECT * FROM tipo_exame";

  public void createTipoExame(TipoExame tipoExame) throws SQLException {
    try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
      preparedStatement.setString(1, tipoExame.getDescricao());
      preparedStatement.setInt(2, tipoExame.getMaterial_coleta_id());
      preparedStatement.executeUpdate();
      System.out.println("Tipo de exame cadastrado com sucesso!");
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao cadastrar tipo de exame", e, e.getErrorCode(), e.getSQLState());
    }
  }

  public List<TipoExame> getAllTipoExames() throws SQLException {
    List<TipoExame> tiposExame = new ArrayList<>();

    try (PreparedStatement preparedStatement = prepareStatement(SELECT)) {
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {
        TipoExame tipoExame = new TipoExame();
        tipoExame.setId(result.getInt("id"));
        tipoExame.setDescricao(result.getString("descricao"));
        tipoExame.setMaterial_coleta_id(result.getInt("material_coleta_id"));
        tiposExame.add(tipoExame);
      }

    } catch (SQLException e) {
      throw new DatabaseException("Erro ao buscar tipos de exame", e, e.getErrorCode(), e.getSQLState());
    }
    return tiposExame;
  }

  public TipoExame getTipoExameById(int id) throws SQLException {
    TipoExame tipoExame = null;
    try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_ID)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        tipoExame = new TipoExame();
        tipoExame.setId(resultSet.getInt("id"));
        tipoExame.setDescricao(resultSet.getString("descricao"));
        tipoExame.setMaterial_coleta_id(resultSet.getInt("material_coleta_id"));
      }
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao buscar tipo de exame pelo ID", e, e.getErrorCode(), e.getSQLState());
    }
    return tipoExame;
  }

  public void updateTipoExame(TipoExame tipoExame) throws SQLException {
    try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
      preparedStatement.setString(1, tipoExame.getDescricao());
      preparedStatement.setInt(2, tipoExame.getMaterial_coleta_id());
      preparedStatement.setInt(3, tipoExame.getId());
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        System.out.println("Tipo de exame atualizado com sucesso!");
      } else {
        System.out.println("Nenhum tipo de exame encontrado com o ID fornecido.");
      }
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao atualizar tipo de exame", e, e.getErrorCode(), e.getSQLState());
    }
  }

  public void deleteTipoExame(int id) throws SQLException {
    try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
      preparedStatement.setInt(1, id);
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        System.out.println("Tipo de exame excluído com sucesso!");
      } else {
        System.out.println("Nenhum tipo de exame encontrado com o ID fornecido.");
      }
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao excluir tipo de exame", e, e.getErrorCode(), e.getSQLState());
    }
  }
}
