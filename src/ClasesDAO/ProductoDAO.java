package ClasesDAO;

import Modelo.Producto;
import Conexion.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Insertar un nuevo producto
    public boolean insertarProducto(Producto producto) {
        String sql = "INSERT INTO Producto (nombre, descripcion, precio, cantidad, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setFloat(3, producto.getPrecio());
            stmt.setInt(4, producto.getCantidad());
            stmt.setInt(5, producto.getCategoria_id());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos los productos
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio"),
                        rs.getInt("cantidad"),
                        rs.getInt("categoria_id")
                );
                productos.add(producto);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar productos: " + e.getMessage());
        }
        return productos;
    }

    // Buscar producto por ID
    public Producto buscarProductoPorId(int id) {
        String sql = "SELECT * FROM Producto WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getFloat("precio"),
                            rs.getInt("cantidad"),
                            rs.getInt("categoria_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar producto: " + e.getMessage());
        }
        return null;
    }

    // Actualizar producto
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE Producto SET nombre = ?, descripcion = ?, precio = ?, cantidad = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setFloat(3, producto.getPrecio());
            stmt.setInt(4, producto.getCantidad());
            stmt.setInt(5, producto.getCategoria_id());
            stmt.setInt(6, producto.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // Eliminar producto por ID
   public boolean eliminarProducto(int idProducto) {
    String sql = "DELETE FROM Producto WHERE id = ?";
    try (Connection conn = DatabaseConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idProducto);
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("❌ Error al eliminar producto: " + e.getMessage());
        return false;
    }
}

}
