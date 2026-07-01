package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void crear(Usuario usuario);
    Usuario buscar(String cedula);
    void actualizar(String cedula, Usuario usuario);
    void eliminar(String cedula);
    List<Usuario> listar();
}
