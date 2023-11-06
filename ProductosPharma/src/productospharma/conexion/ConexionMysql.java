package productospharma.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMysql {
    private Connection connection;
    private String usuario = "root"; // Usuario de MySQL
    private String password = ""; // Contraseña de MySQL (deja esto en blanco)
    private String servidor = "localhost"; // Dirección del servidor MySQL
    private String puerto = "3306"; // Puerto de MySQL
    private String nombreBD = "productos"; // Nombre de la base de datos
    
    private String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + nombreBD + "?serverTimezone=UTC";

    private String driver = "com.mysql.cj.jdbc.Driver";
    
    public ConexionMysql() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, password);
            if (connection != null) {
                System.out.println("Conexión realizada correctamente");
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error en la conexión");
            System.err.println("Mensaje del error: " + e.getMessage());
            System.err.println("Detalles del error:");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
