package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Autor;
import java.util.List;

public interface AutorDAO {
    void crear(Autor autor);
    Autor buscar(int codigo);
    void actualizar(int codigo, Autor autor);
    void eliminar(int codigo);
    List<Autor> listar();
}
