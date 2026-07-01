package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Prestamo;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOMemoria implements PrestamoDAO {
    private List<Prestamo> lista;
    private int contador;

    public PrestamoDAOMemoria() {
        lista = new ArrayList<>();
        contador = 1;
    }

    public int siguienteId() {
        return contador;
    }

    @Override
    public void crear(Prestamo prestamo) {
        prestamo.setId(contador);
        lista.add(prestamo);
        contador++;
    }

    @Override
    public Prestamo buscar(int id) {
        for (Prestamo prestamo : lista) {
            if (prestamo.getId() == id) {
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public void actualizar(int id, Prestamo prestamo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                lista.set(i, prestamo);
                break;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        Prestamo prestamoEncontrado = buscar(id);
        if (prestamoEncontrado != null) {
            lista.remove(prestamoEncontrado);
        }
    }

    @Override
    public List<Prestamo> listar() {
        return lista;
    }
}
