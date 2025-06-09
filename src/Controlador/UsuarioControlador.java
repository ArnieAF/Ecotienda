/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
/**
 *
 * @author Daniela
 */
import ClasesDAO.UsuarioDAO;
import Modelo.Usuario;

import java.util.List;

public class UsuarioControlador {

    private UsuarioDAO usuarioDAO;

    public UsuarioControlador() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Autenticación de usuario
    public Usuario autenticarUsuario(String nombreUsuario, String contrasenia) {
        return usuarioDAO.autenticar(nombreUsuario, contrasenia);
    }

    // Insertar nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        return usuarioDAO.insertarUsuario(usuario);
    }

    // Listar todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    // Actualizar un usuario
    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizarUsuario(usuario);
    }

    // Eliminar un usuario por ID
    public boolean eliminarUsuario(int idUsuario) {
        return usuarioDAO.eliminarUsuario(idUsuario);
    }
}

