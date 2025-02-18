package examemedico.model;

import examemedico.dao.DetalheExameResultadoDAO;

public class DetalheExameResultado {

    private int id;
    private String valor_amostrado;
    private int exame_resultado_id;

    public DetalheExameResultado() {}

    public DetalheExameResultado(int id) {
        this.id = id;
    }

    public DetalheExameResultado(String valor_amostrado, int exame_resultado_id) {
        this.valor_amostrado = valor_amostrado;
        this.exame_resultado_id = exame_resultado_id;
    }

    public DetalheExameResultado(int id, String valor_amostrado, int exame_resultado_id) {
        this.id = id;
        this.valor_amostrado = valor_amostrado;
        this.exame_resultado_id = exame_resultado_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor_amostrado() {
        return valor_amostrado;
    }

    public void setValor_amostrado(String valor_amostrado) {
        this.valor_amostrado = valor_amostrado;
    }

    public int getExame_resultado_id() {
        return exame_resultado_id;
    }

    public void setExame_resultado_id(int exame_resultado_id) {
        this.exame_resultado_id = exame_resultado_id;
    }
}
