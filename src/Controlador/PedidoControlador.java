package Controlador;

import ClasesDAO.PedidoDAO;
import Modelo.Pedido;

import java.util.Date;
import java.util.List;

public class PedidoControlador {

    private PedidoDAO pedidoDAO;

    public PedidoControlador() {
        pedidoDAO = new PedidoDAO();
    }

    public int crearPedido(Pedido pedido) {
        return pedidoDAO.insertarPedido(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.obtenerTodosPedidos();
    }

    public Pedido obtenerPedidoPorId(int id) {
        return pedidoDAO.obtenerPedidoPorId(id);
    }

    public boolean actualizarPedido(Pedido pedido) {
        return pedidoDAO.actualizarPedido(pedido);
    }

    public boolean eliminarPedido(int id) {
        return pedidoDAO.eliminarPedido(id);
    }
    

    public List<Pedido> buscarPedidosPorFecha(Date fechaInicio, Date fechaFin) {
        return pedidoDAO.obtenerPedidosPorFecha(fechaInicio, fechaFin);
    }
}
