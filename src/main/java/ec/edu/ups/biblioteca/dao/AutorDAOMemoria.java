package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Autor;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOMemoria implements AutorDAO {
    private List<Autor> lista;

    public AutorDAOMemoria() {
        lista = new ArrayList<>();
    }

    @Override
    public void crear(Autor autor) {
        lista.add(autor);
    }

    @Override
    public Autor buscar(int codigo) {
        for (Autor autor : lista) {
            if (autor.getCodigo() == codigo) {
                return autor;
            }
        }
        return null;
    }

    @Override
    public void actualizar(int codigo, Autor autor) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == codigo) {
                lista.set(i, autor);
                break;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Autor autorEncontrado = buscar(codigo);
        if (autorEncontrado != null) {
            lista.remove(autorEncontrado);
        }
    }

    @Override
    public List<Autor> listar() {
        return lista;
    }
}
