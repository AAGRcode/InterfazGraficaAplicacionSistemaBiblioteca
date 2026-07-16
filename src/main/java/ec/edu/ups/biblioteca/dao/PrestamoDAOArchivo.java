/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Bibliotecario;
import ec.edu.ups.models.EstadoPrestamo;
import ec.edu.ups.models.Libro;
import ec.edu.ups.models.Prestamo;
import ec.edu.ups.models.Usuario;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class PrestamoDAOArchivo implements PrestamoDAO {
    private static final int TAM_CEDULA = 10;
    private static final int TAM_CODIGO = 10;
    private static final int TAM_FECHA = 10;
    private static final int TAM_ESTADO = 20;
    private static final int TAM_REGISTRO = 4 + 4 + (TAM_CEDULA * 2) + (TAM_CODIGO * 2) + (TAM_FECHA * 2) + (TAM_ESTADO * 2) + 1;
    private String ruta;
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;
    private BibliotecarioDAO bibliotecarioDAO;

    public PrestamoDAOArchivo(String ruta, LibroDAO libroDAO, UsuarioDAO usuarioDAO, BibliotecarioDAO bibliotecarioDAO) {
        this.ruta = ruta;
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
        this.bibliotecarioDAO = bibliotecarioDAO;
    }

    private void escribirTexto(RandomAccessFile archivo, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto == null ? "" : texto);
        sb.setLength(longitud);
        archivo.writeChars(sb.toString());
    }

    private String leerTexto(RandomAccessFile archivo, int longitud) throws IOException {
        char[] caracteres = new char[longitud];
        for (int i = 0; i < longitud; i++) caracteres[i] = archivo.readChar();
        return new String(caracteres).trim();
    }

    private int siguienteId() {
        int max = 0;
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                int id = archivo.readInt();
                if (id > max) max = id;
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        return max + 1;
    }

    @Override
    public void crear(Prestamo prestamo) {
        try {
            prestamo.setId(siguienteId());
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            archivo.seek(archivo.length());
            archivo.writeInt(prestamo.getId());
            archivo.writeInt(prestamo.getLibro().getCodigo());
            escribirTexto(archivo, prestamo.getUsuario().getCedula(), TAM_CEDULA);
            escribirTexto(archivo, prestamo.getBibliotecario().getCodigoEmpleado(), TAM_CODIGO);
            escribirTexto(archivo, prestamo.getFechaDevolucion(), TAM_FECHA);
            escribirTexto(archivo, prestamo.getEstado().toString(), TAM_ESTADO);
            archivo.writeBoolean(true);
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de escritura");
        }
    }

    private Prestamo leerPrestamo(RandomAccessFile archivo) throws IOException {
        int id = archivo.readInt();
        int codigoLibro = archivo.readInt();
        String cedulaUsuario = leerTexto(archivo, TAM_CEDULA);
        String codigoBibliotecario = leerTexto(archivo, TAM_CODIGO);
        String fechaDevolucion = leerTexto(archivo, TAM_FECHA);
        String estadoTexto = leerTexto(archivo, TAM_ESTADO);

        Libro libro = libroDAO.buscar(codigoLibro);
        Usuario usuario = usuarioDAO.buscar(cedulaUsuario);
        Bibliotecario bibliotecario = bibliotecarioDAO.buscar(codigoBibliotecario);

        Prestamo prestamo = new Prestamo(id, libro, usuario, bibliotecario, fechaDevolucion);
        prestamo.setId(id);
        prestamo.setEstado(EstadoPrestamo.valueOf(estadoTexto));
        return prestamo;
    }

    @Override
    public Prestamo buscar(int id) {
        Prestamo encontrado = null;
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                Prestamo prestamo = leerPrestamo(archivo);
                boolean activo = archivo.readBoolean();
                if (activo && prestamo.getId() == id) {
                    encontrado = prestamo;
                    break;
                }
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        return encontrado;
    }

    @Override
    public void actualizar(int id, Prestamo prestamo) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                int idLeido = archivo.readInt();
                archivo.skipBytes(4 + (TAM_CEDULA * 2) + (TAM_CODIGO * 2) + (TAM_FECHA * 2) + (TAM_ESTADO * 2));
                boolean activo = archivo.readBoolean();
                if (activo && idLeido == id) {
                    archivo.seek(posicion);
                    archivo.writeInt(prestamo.getId());
                    archivo.writeInt(prestamo.getLibro().getCodigo());
                    escribirTexto(archivo, prestamo.getUsuario().getCedula(), TAM_CEDULA);
                    escribirTexto(archivo, prestamo.getBibliotecario().getCodigoEmpleado(), TAM_CODIGO);
                    escribirTexto(archivo, prestamo.getFechaDevolucion(), TAM_FECHA);
                    escribirTexto(archivo, prestamo.getEstado().toString(), TAM_ESTADO);
                    archivo.writeBoolean(true);
                    break;
                }
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de escritura");
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                int idLeido = archivo.readInt();
                archivo.skipBytes(4 + (TAM_CEDULA * 2) + (TAM_CODIGO * 2) + (TAM_FECHA * 2) + (TAM_ESTADO * 2));
                boolean activo = archivo.readBoolean();
                if (activo && idLeido == id) {
                    archivo.seek(posicion + 4 + 4 + (TAM_CEDULA * 2) + (TAM_CODIGO * 2) + (TAM_FECHA * 2) + (TAM_ESTADO * 2));
                    archivo.writeBoolean(false);
                    break;
                }
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de escritura");
        }
    }

    @Override
    public List<Prestamo> listar() {
        List<Prestamo> lista = new ArrayList<>();
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                Prestamo prestamo = leerPrestamo(archivo);
                boolean activo = archivo.readBoolean();
                if (activo) lista.add(prestamo);
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        return lista;
    }
}
