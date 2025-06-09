/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author USUARIO
 */
public class DetalleVenta {
    
    private int id;
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private float subTotal;

    public DetalleVenta() {}

    public DetalleVenta(int id, int idVenta, int idProducto, int cantidad, float subTotal) {
        this.id = id;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
    
}
