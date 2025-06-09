package ClasesDAO;

import Modelo.DetallePedido;
import Conexion.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {

    // Insertar un detalle de pedido
    public boolean insertarDetallePedido(DetallePedido detalle) {
        String sql = "INSERT INTO detallepedido (cantidad, subTotal, idProducto, idPedido) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, detalle.getCantidad());
            stmt.setFloat(2, detalle.getSubTotal());
            stmt.setInt(3, detalle.getIdProducto());
            stmt.setInt(4, detalle.getIdPedido());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        detalle.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar detalle pedido: " + e.getMessage());
            return false;
        }
    }

    // Listar detalles por idPedido
    public List<DetallePedido> obtenerDetallesPorPedido(int idPedido) {
        List<DetallePedido> lista = new ArrayList<>();
        String sql = "SELECT id, cantidad, subTotal, idProducto, idPedido FROM detallepedido WHERE idPedido = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetallePedido dp = new DetallePedido();
                    dp.setId(rs.getInt("id"));
                    dp.setCantidad(rs.getInt("cantidad"));
                    dp.setSubTotal(rs.getFloat("subTotal"));
                    dp.setIdProducto(rs.getInt("idProducto"));
                    dp.setIdPedido(rs.getInt("idPedido"));

                    lista.add(dp);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener detalles por pedido: " + e.getMessage());
        }

        return lista;
    }
}
