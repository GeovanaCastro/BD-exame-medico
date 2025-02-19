package examemedico.model;

public class UnidadeMedida {

    private int id;
    private String descricao;
    private String sigla;
    private int tipo_exame_id; // Atributo de chave estrangeira
    private int serie_exame_id; // Atributo de chave estrangeira

    public UnidadeMedida() {}

    public UnidadeMedida(int id) {
        this.id = id;
    }

    public UnidadeMedida(String descricao, String sigla, int tipo_exame_id, int serie_exame_id) {
        this.descricao = descricao;
        this.sigla = sigla;
        this.tipo_exame_id = tipo_exame_id;
        this.serie_exame_id = serie_exame_id;
    }

    public UnidadeMedida(int id, String descricao, String sigla, int tipo_exame_id, int serie_exame_id) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
        this.tipo_exame_id = tipo_exame_id;
        this.serie_exame_id = serie_exame_id;
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

    public int getTipo_exame_id() {
        return tipo_exame_id;
    }

    public void setTipo_exame_id(int tipo_exame_id) {
        this.tipo_exame_id = tipo_exame_id;
    }

    public int getSerie_exame_id() {
        return serie_exame_id;
    }

    public void setSerie_exame_id(int serie_exame_id) {
        this.serie_exame_id = serie_exame_id;
    }
}
