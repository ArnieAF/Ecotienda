/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author USUARIO
 */
public class Usuario {
    
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;
    private boolean activo;

    public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String rol, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean verificarCredenciales(String usuario, String contrasenia) {
        return this.nombreUsuario.equals(usuario) && this.contrasenia.equals(contrasenia);
    }

    public boolean tienePermiso(String permiso) {
        return rol != null && rol.contains(permiso);
    }

    public void cambiarContrasenia(String nuevaContrasenia) {
        this.contrasenia = nuevaContrasenia;
    }
    
    
}
