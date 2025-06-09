package Controlador;

import ClasesDAO.ProductoDAO;
import Modelo.Producto;

import java.util.List;

public class ProductoControlador {
    private ProductoDAO productoDAO;

    public ProductoControlador() {
        productoDAO = new ProductoDAO();
    }

    public boolean agregarProducto(Producto producto) {
        return productoDAO.insertarProducto(producto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.listarProductos();
    }

    public Producto obtenerProductoPorId(int id) {
        return productoDAO.buscarProductoPorId(id);
    }

    public boolean actualizarProducto(Producto producto) {
        return productoDAO.actualizarProducto(producto);
    }

    public boolean eliminarProducto(int idProducto) {
    return productoDAO.eliminarProducto(idProducto);
}

}
