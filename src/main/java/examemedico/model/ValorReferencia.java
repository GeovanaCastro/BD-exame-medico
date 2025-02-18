package examemedico.model;

public class ValorReferencia {

    int id;
    String valor_referencia_por;
    String valor_referencia_abs;

    public ValorReferencia() {}

    public ValorReferencia(int id) {
        this.id = id;
    }

    public ValorReferencia(String valor_referencia_por, String valor_referencia_abs) {
        this.valor_referencia_por = valor_referencia_por;
        this.valor_referencia_abs = valor_referencia_abs;
    }

    public ValorReferencia(int id, String valor_referencia_por, String valor_referencia_abs) {
        this.id = id;
        this.valor_referencia_por = valor_referencia_por;
        this.valor_referencia_abs = valor_referencia_abs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor_referencia_por() {
        return valor_referencia_por;
    }

    public void setValor_referencia_por(String valor_referencia_por) {
        this.valor_referencia_por = valor_referencia_por;
    }

    public String getValor_referencia_abs() {
        return valor_referencia_abs;
    }

    public void setValor_referencia_abs(String valor_referencia_abs) {
        this.valor_referencia_abs = valor_referencia_abs;
    }
}
