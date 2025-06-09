// Archivo: ClasesDAO/ProductoDAO.java
package ClasesDAO;

import Modelo.Producto;
import java.util.List;

public interface ProductoDAO {
    void insertar(Producto producto);
    void actualizar(Producto producto);
    void eliminar(int id);
    Producto obtenerPorId(int id);
    List<Producto> obtenerTodos();
}
