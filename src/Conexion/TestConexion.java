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

public class TestConexion {
    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        Connection conn = db.getConnection();

        if (conn != null) {
            System.out.println("🎉 Conexión verificada correctamente.");
        } else {
            System.out.println("❌ No se pudo establecer la conexión.");
        }

    }
}

