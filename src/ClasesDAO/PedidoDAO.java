/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;
/**
 *
 * @author Daniela
 */
import Modelo.Pedido;
import Conexion.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    
    // Método para insertar un nuevo pedido
    public int insertarPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedido (fecha, total) VALUES (?, ?)";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setDate(1, new java.sql.Date(pedido.getFecha().getTime()));
            pstmt.setFloat(2, pedido.getTotal());
            
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Retorna el ID generado
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al insertar pedido: " + e.getMessage());
        }
        return -1; // Error
    }
    
    // Método para obtener todos los pedidos
    public List<Pedido> obtenerTodosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id, fecha, total FROM Pedido ORDER BY fecha DESC";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setTotal(rs.getFloat("total"));
                pedidos.add(pedido);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
        }
        return pedidos;
    }
    
    // Método para obtener un pedido por ID
    public Pedido obtenerPedidoPorId(int id) {
        String sql = "SELECT id, fecha, total FROM Pedido WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setTotal(rs.getFloat("total"));
                return pedido;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener pedido por ID: " + e.getMessage());
        }
        return null;
    }
    
    // Método para actualizar un pedido
    public boolean actualizarPedido(Pedido pedido) {
        String sql = "UPDATE Pedido SET fecha = ?, total = ? WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, new java.sql.Date(pedido.getFecha().getTime()));
            pstmt.setFloat(2, pedido.getTotal());
            pstmt.setInt(3, pedido.getId());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }
    
    // Método para eliminar un pedido
    public boolean eliminarPedido(int id) {
        String sql = "DELETE FROM Pedido WHERE id = ?";
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
    
    // Método para obtener pedidos por rango de fechas
    public List<Pedido> obtenerPedidosPorFecha(Date fechaInicio, Date fechaFin) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id, fecha, total FROM Pedido WHERE fecha BETWEEN ? AND ? ORDER BY fecha DESC";
        
        try (Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            pstmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setTotal(rs.getFloat("total"));
                pedidos.add(pedido);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos por fecha: " + e.getMessage());
        }
        return pedidos;
    }
}
