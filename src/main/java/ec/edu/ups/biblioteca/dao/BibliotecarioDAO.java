package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Bibliotecario;
import java.util.List;

public interface BibliotecarioDAO {
    void crear(Bibliotecario bibliotecario);
    Bibliotecario buscar(String codigoEmpleado);
    void actualizar(String codigoEmpleado, Bibliotecario bibliotecario);
    void eliminar(String codigoEmpleado);
    List<Bibliotecario> listar();
}
