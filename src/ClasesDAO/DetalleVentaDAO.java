package ClasesDAO;

import Modelo.DetalleVenta;
import Conexion.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    // Insertar detalle venta
    public boolean insertarDetalleVenta(DetalleVenta detalle) {
        String sql = "INSERT INTO detalleventa (idVenta, idProducto, cantidad, subTotal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, detalle.getIdVenta());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setFloat(4, detalle.getSubTotal());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        detalle.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar detalle venta: " + e.getMessage());
            return false;
        }
    }

    // Listar detalles por idVenta
    public List<DetalleVenta> obtenerDetallesPorVenta(int idVenta) {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT id, idVenta, idProducto, cantidad, subTotal FROM detalleventa WHERE idVenta = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetalleVenta dv = new DetalleVenta();
                    dv.setId(rs.getInt("id"));
                    dv.setIdVenta(rs.getInt("idVenta"));
                    dv.setIdProducto(rs.getInt("idProducto"));
                    dv.setCantidad(rs.getInt("cantidad"));
                    dv.setSubTotal(rs.getFloat("subTotal"));

                    lista.add(dv);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener detalles por venta: " + e.getMessage());
        }

        return lista;
    }
}
