package examemedico.model;

public class TipoExame {
  private int id;
  private String descricao;
  private int material_coleta_id;

  public TipoExame() {
  }

  public TipoExame(int id, String descricao, int material_coleta_id) {
    this.id = id;
    this.descricao = descricao;
    this.material_coleta_id = material_coleta_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public int getMaterial_coleta_id() {
    return material_coleta_id;
  }

  public void setMaterial_coleta_id(int material_coleta_id) {
    this.material_coleta_id = material_coleta_id;
  }

  @Override
  public String toString() {
    return "TipoExame{" +
        "id=" + id +
        ", descricao=" + descricao +
        ", material_coleta_id=" + material_coleta_id + "}";
  }
}
