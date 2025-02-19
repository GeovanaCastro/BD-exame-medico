package examemedico;

import java.sql.SQLException;
import java.util.List;

import examemedico.dao.*;
import examemedico.model.*;

public class Main {

    static PessoaDAO pessoaDAO = new PessoaDAO();
    static ExameResultadoDAO exameResultadoDAO = new ExameResultadoDAO();
    static DetalheExameResultadoDAO detalheResultadoDAO = new DetalheExameResultadoDAO();
    static MaterialColetaDAO materialColetaDAO = new MaterialColetaDAO();
    static SerieExameDAO serieExameDAO = new SerieExameDAO();
    static TipoExameDAO tipoExameDAO = new TipoExameDAO();
    static UnidadeMedidaDAO unidadeMedidaDAO = new UnidadeMedidaDAO();
    static ValorReferenciaDAO valorReferenciaDAO = new ValorReferenciaDAO();

    public static void main(String[] args) throws SQLException {

        System.out.println("----- Listando todas as pessoas -----");
        List<Pessoa> pessoas = pessoaDAO.getAllPessoas();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }

        System.out.println("\n----- Listando todos os resultados de exames -----");
        List<ExameResultado> exames = exameResultadoDAO.getAllExameResultados();
        for (ExameResultado exame : exames) {
            System.out.println(exame);
        }

        System.out.println("\n----- Listando todos os detalhes de exames -----");
        List<DetalheExameResultado> detalhes = detalheResultadoDAO.findAllDetalheExameResultado();
        for (DetalheExameResultado detalhe : detalhes) {
            System.out.println(detalhe);
        }

        System.out.println("\n----- Listando todos os materiais de coleta -----");
        List<MaterialColeta> materiais = materialColetaDAO.getAllMaterialColeta();
        for (MaterialColeta material : materiais) {
            System.out.println(material);
        }

        System.out.println("\n----- Listando todas as séries de exames -----");
        List<SerieExame> series = serieExameDAO.findAllSerieExame();
        for (SerieExame serie : series) {
            System.out.println(serie);
        }

        System.out.println("\n----- Listando todos os tipos de exames -----");
        List<TipoExame> tipos = tipoExameDAO.getAllTipoExames();
        for (TipoExame tipo : tipos) {
            System.out.println(tipo);
        }

        System.out.println("\n----- Listando todas as unidades de medida -----");
        List<UnidadeMedida> unidades = unidadeMedidaDAO.getAllUnidadesMedida();
        for (UnidadeMedida unidade : unidades) {
            System.out.println(unidade);
        }

        System.out.println("\n----- Listando todos os valores de referência -----");
        List<ValorReferencia> valoresReferencia = valorReferenciaDAO.findAllValorReferencia();
        for (ValorReferencia valor : valoresReferencia) {
            System.out.println(valor);
        }
    }
}
