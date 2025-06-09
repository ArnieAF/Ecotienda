/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;
/**
 *
 * @author Daniela
 */
import Modelo.Proveedor;
import Conexion.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    
    // Método para insertar un nuevo proveedor
    public boolean insertarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedor (nombre, contacto) VALUES (?, ?)";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    // Método para obtener todos los proveedores
    public List<Proveedor> obtenerTodosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT id, nombre, contacto FROM Proveedor ORDER BY nombre";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedores.add(proveedor);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores: " + e.getMessage());
        }
        return proveedores;
    }
    
    // Método para obtener un proveedor por ID
    public Proveedor obtenerProveedorPorId(int id) {
        String sql = "SELECT id, nombre, contacto FROM Proveedor WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
                return proveedor;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedor por ID: " + e.getMessage());
        }
        return null;
    }
    
    // Método para actualizar un proveedor
    public boolean actualizarProveedor(Proveedor proveedor) {
        String sql = "UPDATE Proveedor SET nombre = ?, contacto = ? WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setInt(3, proveedor.getId());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    // Método para eliminar un proveedor
    public boolean eliminarProveedor(int id) {
        String sql = "DELETE FROM Proveedor WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    // Método para buscar proveedores por nombre
    public List<Proveedor> buscarProveedoresPorNombre(String nombre) {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT id, nombre, contacto FROM Proveedor WHERE nombre LIKE ? ORDER BY nombre";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nombre + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
                proveedores.add(proveedor);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar proveedores: " + e.getMessage());
        }
        return proveedores;
    }
}
