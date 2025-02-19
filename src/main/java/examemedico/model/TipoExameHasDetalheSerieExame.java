package examemedico.model;

import java.util.Objects;

public class TipoExameHasDetalheSerieExame {

    private TipoExame tipoExame;

    private DetalheExameResultado detalheExameResultado;

    public TipoExameHasDetalheSerieExame(TipoExame tipoExame, DetalheExameResultado detalheExameResultado) {
        this.tipoExame = tipoExame;
        this.detalheExameResultado = detalheExameResultado;
    }

    public TipoExame getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(TipoExame tipoExame) {
        this.tipoExame = tipoExame;
    }

    public DetalheExameResultado getDetalheExameResultado() {
        return detalheExameResultado;
    }

    public void setDetalheExameResultado(DetalheExameResultado detalheExameResultado) {
        this.detalheExameResultado = detalheExameResultado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TipoExameHasDetalheSerieExame that = (TipoExameHasDetalheSerieExame) o;
        return Objects.equals(tipoExame, that.tipoExame) && Objects.equals(detalheExameResultado, that.detalheExameResultado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoExame, detalheExameResultado);
    }

    @Override
    public String toString() {
        return "TipoExameHasDetalheSerieExame{" +
                "tipoExame=" + tipoExame +
                ", detalheExameResultado=" + detalheExameResultado +
                '}';
    }
}
