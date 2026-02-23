package cl.speedfast.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/speedfast?useSSl=false&useUnicode=true&serverTimezone=UTC";
    private static final String USUARIO = "speedfast";
    private static final String PASSWORD = "secret";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
