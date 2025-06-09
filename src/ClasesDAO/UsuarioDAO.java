package ClasesDAO;

import Modelo.Usuario;
import Conexion.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Insertar un nuevo usuario
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombreUsuario, contrasena, rol, activo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContrasenia());
            stmt.setString(3, usuario.getRol());
            stmt.setString(4, usuario.isActivo() ? "Sí" : "No");

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // Autenticar usuario por nombre y contraseña
    public Usuario autenticar(String nombreUsuario, String contrasenia) {
    String sql = "SELECT * FROM Usuario WHERE nombreUsuario = ? AND contrasena = ? AND activo = 'Sí'";
    
    try {
        Connection conn = Conexion.DatabaseConnection.getInstance().getConnection();
        if (conn == null || conn.isClosed()) {
            System.err.println("❌ Conexión no disponible.");
            return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("activo").equalsIgnoreCase("Sí")
                    );
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("❌ Error al autenticar usuario: " + e.getMessage());
    }

    return null;
}


    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        rs.getString("activo").equalsIgnoreCase("Sí")
                );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    // Actualizar usuario
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nombreUsuario = ?, contrasena = ?, rol = ?, activo = ? WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContrasenia());
            stmt.setString(3, usuario.getRol());
            stmt.setString(4, usuario.isActivo() ? "Sí" : "No");
            stmt.setInt(5, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // Eliminar usuario por ID
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
