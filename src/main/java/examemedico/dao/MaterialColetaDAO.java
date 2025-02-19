package examemedico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static examemedico.connection.ConnectionModule.prepareStatement;
import examemedico.exception.DatabaseException;
import examemedico.model.MaterialColeta;

public class MaterialColetaDAO {

  private static final String INSERT = "INSERT INTO material_coleta (descricao, observacao) VALUES (?, ?)";
  private static final String DELETE = "DELETE FROM material_coleta WHERE id = ?";
  private static final String UPDATE = "UPDATE material_coleta SET descricao = ?, observacao = ? WHERE id = ?";
  private static final String SELECT_BY_ID = "SELECT * FROM material_coleta WHERE id = ?";
  private static final String SELECT = "SELECT * FROM material_coleta";

  public void createMaterialColeta(MaterialColeta materialColeta) throws SQLException {
    try (PreparedStatement preparedStatement = prepareStatement(INSERT)) {
      preparedStatement.setString(1, materialColeta.getDescricao());
      preparedStatement.setString(2, materialColeta.getObservacao());
      preparedStatement.executeUpdate();
      System.out.println("Material de coleta cadastrado com sucesso!");
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao cadastrar material de coleta", e, e.getErrorCode(), e.getSQLState());
    }
  }

  public List<MaterialColeta> getAllMaterialColeta() throws SQLException {
    List<MaterialColeta> materiais = new ArrayList<>();

    try (PreparedStatement preparedStatement = prepareStatement(SELECT)) {
      ResultSet result = preparedStatement.executeQuery();

      while (result.next()) {
        MaterialColeta material = new MaterialColeta();
        material.setId(result.getInt("id"));
        material.setDescricao(result.getString("descricao"));
        material.setObservacao(result.getString("observacao"));
        materiais.add(material);
      }

    } catch (SQLException e) {
      throw new DatabaseException("Erro ao buscar materiais de coleta", e, e.getErrorCode(), e.getSQLState());
    }
    return materiais;
  }

  public MaterialColeta getMaterialColetaById(int id) throws SQLException {
    MaterialColeta material = null;
    try (PreparedStatement preparedStatement = prepareStatement(SELECT_BY_ID)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        material = new MaterialColeta();
        material.setId(resultSet.getInt("id"));
        material.setDescricao(resultSet.getString("descricao"));
        material.setObservacao(resultSet.getString("observacao"));
      }
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao buscar material de coleta pelo ID", e, e.getErrorCode(), e.getSQLState());
    }
    return material;
  }

  public void updateMaterialColeta(MaterialColeta materialColeta) throws SQLException {
    try (PreparedStatement preparedStatement = prepareStatement(UPDATE)) {
      preparedStatement.setString(1, materialColeta.getDescricao());
      preparedStatement.setString(2, materialColeta.getObservacao());
      preparedStatement.setInt(3, materialColeta.getId());
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        System.out.println("Material de coleta atualizado com sucesso!");
      } else {
        System.out.println("Nenhum material de coleta encontrado com o ID fornecido.");
      }
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao atualizar material de coleta", e, e.getErrorCode(), e.getSQLState());
    }
  }

  public void deleteMaterialColeta(int id) throws SQLException {
    try (PreparedStatement preparedStatement = prepareStatement(DELETE)) {
      preparedStatement.setInt(1, id);
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected > 0) {
        System.out.println("Material de coleta excluído com sucesso!");
      } else {
        System.out.println("Nenhum material de coleta encontrado com o ID fornecido.");
      }
    } catch (SQLException e) {
      throw new DatabaseException("Erro ao excluir material de coleta", e, e.getErrorCode(), e.getSQLState());
    }
  }
}
