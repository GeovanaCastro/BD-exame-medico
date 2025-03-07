package examemedico.model;

public class MaterialColeta {
  private int id;
  private String descricao;
  private String observacao;

  public MaterialColeta() {
  }

  public MaterialColeta(int id, String descricao, String observacao) {
    this.id = id;
    this.descricao = descricao;
    this.observacao = observacao;
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

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  @Override
  public String toString() {
    return "MaterialColeta{" +
        "id=" + id +
        ", descricao='" + descricao + '\'' +
        ", observacao='" + observacao + '\'' +
        '}';
  }
}
