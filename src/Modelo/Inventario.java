/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author USUARIO
 */
public class Inventario {
    
    private int idProducto;
    private int cantidadEnStock;
    private int stockMinimo;

    public Inventario() {}

    public Inventario(int idProducto, int cantidadEnStock, int stockMinimo) {
        this.idProducto = idProducto;
        this.cantidadEnStock = cantidadEnStock;
        this.stockMinimo = stockMinimo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public boolean verificarStockMinimo() {
        return cantidadEnStock < stockMinimo;
    }
    
}
