/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
/**
 *
 * @author Daniela
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Parámetros de conexión — AJUSTA según tu configuración
    private final String url = "jdbc:mysql://localhost:3306/ECOTIENDA?useSSL=false";
    private final String user = "root";
    private final String password = "admin";

    // Constructor privado (Singleton)
    private DatabaseConnection() {
        connect();
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión exitosa a la base de datos ECOTIENDA.");
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
        if (connection == null || connection.isClosed()) {
            System.out.println("🔄 Reconectando...");
            connect();
        }
    } catch (SQLException e) {
        System.err.println("Error al verificar la conexión: " + e.getMessage());
    }
    return connection;

    }

}

