package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Prestamo;
import java.util.List;

public interface PrestamoDAO {
    void crear(Prestamo prestamo);
    Prestamo buscar(int id);
    void actualizar(int id, Prestamo prestamo);
    void eliminar(int id);
    List<Prestamo> listar();
}
