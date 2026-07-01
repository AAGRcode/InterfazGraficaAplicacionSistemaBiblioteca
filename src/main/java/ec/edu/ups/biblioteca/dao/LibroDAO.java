package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Libro;
import java.util.List;

public interface LibroDAO {
    void crear(Libro libro);
    Libro buscar(int codigo);
    void actualizar(int codigo, Libro libro);
    void eliminar(int codigo);
    List<Libro> listar();
}
