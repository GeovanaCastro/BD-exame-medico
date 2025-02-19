package examemedico;

import java.sql.SQLException;

import examemedico.dao.DetalheExameResultadoDAO;
import examemedico.dao.ExameResultadoDAO;
import examemedico.dao.PessoaDAO;

public class Main {

    static PessoaDAO pessoaDAO = new PessoaDAO();
    static ExameResultadoDAO exameResultadoDAO = new ExameResultadoDAO();
    static DetalheExameResultadoDAO detalheResultadoDAO = new DetalheExameResultadoDAO();

    public static void main(String[] args) throws SQLException {

        // Pessoa p1 = new Pessoa("alyne",12);
        //
        //// pessoaDAO.createPessoa(p1);
        //
        // List<Pessoa> pessoas = pessoaDAO.getAllPessoas();
        // pessoas.forEach(System.out::println);

        // List<Pessoa> pessoasComIdade = pessoaDAO.getPessoasByIdade(25);
        // for (Pessoa p : pessoasComIdade) {
        // System.out.println(p);
        // }

        // Pessoa pessoa1 = new Pessoa( "João Silva", 35);
        // Pessoa pessoa2 = new Pessoa( "Maria Oliveira", 28);
        //
        // pessoaDAO.createPessoa(pessoa1);
        // pessoaDAO.createPessoa(pessoa2);
        //
        // Timestamp dataColeta1 = Timestamp.valueOf("2025-02-01 10:30:00");
        // Timestamp dataResultado1 = Timestamp.valueOf("2025-02-05 14:00:00");
        //
        // Timestamp dataColeta2 = Timestamp.valueOf("2025-02-02 11:00:00");
        // Timestamp dataResultado2 = Timestamp.valueOf("2025-02-06 15:30:00");
        //
        // ExameResultado exame1 = new ExameResultado(dataColeta1, dataResultado1, 1,
        // 2);
        // ExameResultado exame2 = new ExameResultado(dataColeta2, dataResultado2, 2,
        // 1);
        //
        // exameResultadoDAO.createExameResultado(exame1);
        // exameResultadoDAO.createExameResultado(exame2);
        //
        // DetalheExameResultado detalhe1 = new DetalheExameResultado(1, "23", 9);
        // DetalheExameResultado detalhe2 = new DetalheExameResultado(2, "45", 9);
        //
        // DetalheExameResultadoDAO detalheResultadoDAO = new
        // DetalheExameResultadoDAO();
        // detalheResultadoDAO.createDetalheExameResultado(detalhe1);
        // detalheResultadoDAO.createDetalheExameResultado(detalhe2);
        //
        // List<DetalheExameResultado> detalhes =
        // detalheResultadoDAO.findAllDetalheExameResultado();
        //
        // for (DetalheExameResultado detalhe : detalhes) {
        // System.out.println(detalhe);
        // }
    }
}