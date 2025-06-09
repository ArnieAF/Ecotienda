/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;
/**
 *
 * @author Daniela
 */
import Modelo.Categoria;
import Conexion.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    
    // Método para insertar una nueva categoría
    public boolean insertarCategoria(Categoria categoria) {
        String sql = "INSERT INTO Categoria (nombre) VALUES (?)";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, categoria.getNombre());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar categoría: " + e.getMessage());
            return false;
        }
    }
    
    // Método para obtener todas las categorías
    public List<Categoria> obtenerTodasCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Categoria ORDER BY nombre";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener categorías: " + e.getMessage());
        }
        return categorias;
    }
    
    // Método para obtener una categoría por ID
    public Categoria obtenerCategoriaPorId(int id) {
        String sql = "SELECT id, nombre FROM Categoria WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                return categoria;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener categoría por ID: " + e.getMessage());
        }
        return null;
    }
    
    // Método para actualizar una categoría
    public boolean actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE Categoria SET nombre = ? WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getId());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }
    
    // Método para eliminar una categoría
    public boolean eliminarCategoria(int id) {
        String sql = "DELETE FROM Categoria WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar categoría: " + e.getMessage());
            return false;
        }
    }
    
    // Método para buscar categorías por nombre
    public List<Categoria> buscarCategoriasPorNombre(String nombre) {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Categoria WHERE nombre LIKE ? ORDER BY nombre";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nombre + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar categorías: " + e.getMessage());
        }
        return categorias;
    }
}
