package examemedico.model;

import java.sql.Timestamp;

public class ExameResultado {

    private int id;
    private Timestamp dataColeta;
    private Timestamp dataResultado;
    private int pessoaId;
    private int tipoExameId;

    public ExameResultado(Timestamp dataColeta, Timestamp dataResultado, int pessoaId, int tipoExameId) {
        this.dataColeta = dataColeta;
        this.dataResultado = dataResultado;
        this.pessoaId = pessoaId;
        this.tipoExameId = tipoExameId;
    }

    public ExameResultado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(Timestamp dataColeta) {
        this.dataColeta = dataColeta;
    }

    public Timestamp getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(Timestamp dataResultado) {
        this.dataResultado = dataResultado;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public int getTipoExameId() {
        return tipoExameId;
    }

    public void setTipoExameId(int tipoExameId) {
        this.tipoExameId = tipoExameId;
    }

    @Override
    public String toString() {
        return "ExameResultado{" +
                "id=" + id +
                ", dataColeta=" + dataColeta +
                ", dataResultado=" + dataResultado +
                ", pessoaId=" + pessoaId +
                ", tipoExameId=" + tipoExameId +
                '}';
    }
}
