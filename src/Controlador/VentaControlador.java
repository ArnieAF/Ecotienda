package Controlador;

import ClasesDAO.VentaDAO;
import Modelo.Venta;

import java.util.List;

public class VentaControlador {

    private VentaDAO ventaDAO;

    public VentaControlador() {
        ventaDAO = new VentaDAO();
    }

    public boolean agregarVenta(Venta venta) {
        return ventaDAO.insertarVenta(venta);
    }

    public List<Venta> obtenerTodasVentas() {
        return ventaDAO.obtenerVentas();
    }
}
