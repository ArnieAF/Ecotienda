/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;
/**
 *
 * @author Daniela
 */
import Modelo.Inventario;
import Conexion.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    
    // Método para insertar un nuevo inventario
    public boolean insertarInventario(Inventario inventario) {
        String sql = "INSERT INTO Inventario (idProducto, cantidadEnStock, stockMinimo) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, inventario.getIdProducto());
            pstmt.setInt(2, inventario.getCantidadEnStock());
            pstmt.setInt(3, inventario.getStockMinimo());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar inventario: " + e.getMessage());
            return false;
        }
    }
    
    // Método para obtener todo el inventario
    public List<Inventario> obtenerTodoInventario() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT idProducto, cantidadEnStock, stockMinimo FROM Inventario ORDER BY idProducto";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setIdProducto(rs.getInt("idProducto"));
                inventario.setCantidadEnStock(rs.getInt("cantidadEnStock"));
                inventario.setStockMinimo(rs.getInt("stockMinimo"));
                inventarios.add(inventario);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener inventario: " + e.getMessage());
        }
        return inventarios;
    }
    
    // Método para obtener inventario por ID de producto
    public Inventario obtenerInventarioPorProducto(int idProducto) {
        String sql = "SELECT idProducto, cantidadEnStock, stockMinimo FROM Inventario WHERE idProducto = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setIdProducto(rs.getInt("idProducto"));
                inventario.setCantidadEnStock(rs.getInt("cantidadEnStock"));
                inventario.setStockMinimo(rs.getInt("stockMinimo"));
                return inventario;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener inventario por producto: " + e.getMessage());
        }
        return null;
    }
    
    // Método para actualizar inventario
    public boolean actualizarInventario(Inventario inventario) {
        String sql = "UPDATE Inventario SET cantidadEnStock = ?, stockMinimo = ? WHERE idProducto = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, inventario.getCantidadEnStock());
            pstmt.setInt(2, inventario.getStockMinimo());
            pstmt.setInt(3, inventario.getIdProducto());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar inventario: " + e.getMessage());
            return false;
        }
    }
    
    // Método para eliminar inventario
    public boolean eliminarInventario(int idProducto) {
        String sql = "DELETE FROM Inventario WHERE idProducto = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProducto);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar inventario: " + e.getMessage());
            return false;
        }
    }
    
    // Método para obtener productos con stock bajo
    public List<Inventario> obtenerProductosStockBajo() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT idProducto, cantidadEnStock, stockMinimo FROM Inventario WHERE cantidadEnStock <= stockMinimo ORDER BY cantidadEnStock";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setIdProducto(rs.getInt("idProducto"));
                inventario.setCantidadEnStock(rs.getInt("cantidadEnStock"));
                inventario.setStockMinimo(rs.getInt("stockMinimo"));
                inventarios.add(inventario);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener productos con stock bajo: " + e.getMessage());
        }
        return inventarios;
    }
    
    // Método para actualizar solo la cantidad en stock
    public boolean actualizarCantidadStock(int idProducto, int nuevaCantidad) {
        String sql = "UPDATE Inventario SET cantidadEnStock = ? WHERE idProducto = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, nuevaCantidad);
            pstmt.setInt(2, idProducto);
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar cantidad en stock: " + e.getMessage());
            return false;
        }
    }
}
