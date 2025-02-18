package examemedico;

import examemedico.dao.PessoaDAO;
import examemedico.model.Pessoa;

import java.sql.SQLException;

public class Main {

    static PessoaDAO pessoaDAO = new PessoaDAO();

    public static void main(String[] args) throws SQLException {

        Pessoa p1 = new Pessoa("alyne",12);

        pessoaDAO.createPessoa(p1);
    }
}