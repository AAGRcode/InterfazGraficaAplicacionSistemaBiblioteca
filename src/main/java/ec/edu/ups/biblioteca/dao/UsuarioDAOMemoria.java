package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {
    private List<Usuario> lista;

    public UsuarioDAOMemoria() {
        lista = new ArrayList<>();
    }

    @Override
    public void crear(Usuario usuario) {
        lista.add(usuario);
    }

    @Override
    public Usuario buscar(String cedula) {
        for (Usuario usuario : lista) {
            if (usuario.getCedula().equals(cedula)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String cedula, Usuario usuario) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCedula().equals(cedula)) {
                lista.set(i, usuario);
                break;
            }
        }
    }

    @Override
    public void eliminar(String cedula) {
        Usuario usuarioEncontrado = buscar(cedula);
        if (usuarioEncontrado != null) {
            lista.remove(usuarioEncontrado);
        }
    }

    @Override
    public List<Usuario> listar() {
        return lista;
    }
}
