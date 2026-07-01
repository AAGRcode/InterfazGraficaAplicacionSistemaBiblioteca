package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Bibliotecario;
import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAOMemoria implements BibliotecarioDAO {
    private List<Bibliotecario> lista;

    public BibliotecarioDAOMemoria() {
        lista = new ArrayList<>();
    }

    @Override
    public void crear(Bibliotecario bibliotecario) {
        lista.add(bibliotecario);
    }

    @Override
    public Bibliotecario buscar(String codigoEmpleado) {
        for (Bibliotecario bibliotecario : lista) {
            if (bibliotecario.getCodigoEmpleado().equals(codigoEmpleado)) {
                return bibliotecario;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigoEmpleado, Bibliotecario bibliotecario) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigoEmpleado().equals(codigoEmpleado)) {
                lista.set(i, bibliotecario);
                break;
            }
        }
    }

    @Override
    public void eliminar(String codigoEmpleado) {
        Bibliotecario bibliotecarioEncontrado = buscar(codigoEmpleado);
        if (bibliotecarioEncontrado != null) {
            lista.remove(bibliotecarioEncontrado);
        }
    }

    @Override
    public List<Bibliotecario> listar() {
        return lista;
    }
}
