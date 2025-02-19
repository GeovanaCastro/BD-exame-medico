package examemedico.model;

public class DetalheSerieExame {

    private int id;
    private String descricao;
    private SerieExame serieExame;
    private ValorReferencia valorReferencia;
    private UnidadeMedida unidadeMedida;
    private DetalheExameResultado detalheExameResultado;

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

    public SerieExame getSerieExame() {
        return serieExame;
    }

    public void setSerieExame(SerieExame serieExame) {
        this.serieExame = serieExame;
    }

    public ValorReferencia getValorReferencia() {
        return valorReferencia;
    }

    public void setValorReferencia(ValorReferencia valorReferencia) {
        this.valorReferencia = valorReferencia;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public DetalheExameResultado getDetalheExameResultado() {
        return detalheExameResultado;
    }

    public void setDetalheExameResultado(DetalheExameResultado detalheExameResultado) {
        this.detalheExameResultado = detalheExameResultado;
    }

    public DetalheSerieExame(int id, String descricao, SerieExame serieExame, ValorReferencia valorReferencia, UnidadeMedida unidadeMedida, DetalheExameResultado detalheExameResultado) {
        this.id = id;
        this.descricao = descricao;
        this.serieExame = serieExame;
        this.valorReferencia = valorReferencia;
        this.unidadeMedida = unidadeMedida;
        this.detalheExameResultado = detalheExameResultado;
    }

    public DetalheSerieExame () {

    }

    @Override
    public String toString() {
        return "DetalheSerieExameDAO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", serieExame=" + serieExame +
                ", valorReferencia=" + valorReferencia +
                ", unidadeMedida=" + unidadeMedida +
                ", detalheExameResultado=" + detalheExameResultado +
                '}';
    }
}
