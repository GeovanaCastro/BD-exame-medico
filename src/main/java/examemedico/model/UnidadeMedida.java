package examemedico.model;

public class UnidadeMedida {

    private int id;
    private String descricao;
    private String sigla;

    public UnidadeMedida() {}

    public UnidadeMedida(int id) {
        this.id = id;
    }

    public UnidadeMedida(String descricao, String sigla, int tipo_exame_id, int serie_exame_id) {
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public UnidadeMedida(int id, String descricao, String sigla, int tipo_exame_id, int serie_exame_id) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "UnidadeMedida{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
