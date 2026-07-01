package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Libro;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOMemoria implements LibroDAO {
    private List<Libro> lista;

    public LibroDAOMemoria() {
        lista = new ArrayList<>();
    }

    @Override
    public void crear(Libro libro) {
        lista.add(libro);
    }

    @Override
    public Libro buscar(int codigo) {
        for (Libro libro : lista) {
            if (libro.getCodigo() == codigo) {
                return libro;
            }
        }
        return null;
    }


    @Override
    public void actualizar(int codigo, Libro libro) {
        for (int i = 0; i < lista.size(); i++) {
            Libro libroEncontrado = lista.get(i);
            if (libroEncontrado.getCodigo() == codigo) {
                lista.set(i, libro);
                break;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Libro libroEncontrado = buscar(codigo);
        if (libroEncontrado != null) {
            lista.remove(libroEncontrado);
        }
    }

    @Override
    public List<Libro> listar() {
        return lista;
    }
}
