package examemedico.model;

public class SerieExame {

    private int id;
    private String descricao;

    public SerieExame() {}

    public SerieExame(int id) {
        this.id = id;
    }

    public SerieExame(String descricao) {
        this.descricao = descricao;
    }

    public SerieExame(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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
}
