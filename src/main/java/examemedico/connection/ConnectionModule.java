package examemedico.connection;

import examemedico.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionModule {
    private static final String URL = "jdbc:mysql://localhost:3306/exame_medico";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Admin@123";

    public static Connection connection() throws SQLException {

        System.out.println("Conectando Banco de Dados...");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        if (connection!= null) {
            System.out.println("Conexão com o banco de daos sucecida!");
            return connection;
        } else {
            throw new RuntimeException("Erro ao conectar com o banco de dados");
        }
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection().prepareStatement(sql);
    }
}
