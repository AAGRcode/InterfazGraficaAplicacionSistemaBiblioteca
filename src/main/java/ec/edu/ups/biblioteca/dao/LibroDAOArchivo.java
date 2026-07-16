/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Autor;
import ec.edu.ups.models.Categoria;
import ec.edu.ups.models.Libro;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class LibroDAOArchivo implements LibroDAO {
    private static final int TAM_TITULO = 60;
    private static final int TAM_CATEGORIA = 20;
    private static final int TAM_REGISTRO = 4 + (TAM_TITULO * 2) + (TAM_CATEGORIA * 2) + 4 + 4 + 1 + 1;
    private String ruta;
    private AutorDAO autorDAO;

    public LibroDAOArchivo(String ruta, AutorDAO autorDAO) {
        this.ruta = ruta;
        this.autorDAO = autorDAO;
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

    @Override
    public void crear(Libro libro) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            archivo.seek(archivo.length());
            archivo.writeInt(libro.getCodigo());
            escribirTexto(archivo, libro.getTitulo(), TAM_TITULO);
            escribirTexto(archivo, libro.getCategoria().toString(), TAM_CATEGORIA);
            archivo.writeInt(libro.getAnioPublicacion());
            archivo.writeInt(libro.getAutor().getCodigo());
            archivo.writeBoolean(libro.isEstaDisponible());
            archivo.writeBoolean(true);
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de escritura");
        }
    }

    private Libro leerLibro(RandomAccessFile archivo) throws IOException {
        int codigo = archivo.readInt();
        String titulo = leerTexto(archivo, TAM_TITULO);
        String categoriaTexto = leerTexto(archivo, TAM_CATEGORIA);
        int anio = archivo.readInt();
        int codigoAutor = archivo.readInt();
        boolean disponible = archivo.readBoolean();
        Autor autor = autorDAO.buscar(codigoAutor);
        Libro libro = new Libro(codigo, titulo, Categoria.valueOf(categoriaTexto), anio, autor);
        libro.setEstaDisponible(disponible);
        return libro;
    }

    @Override
    public Libro buscar(int codigo) {
        Libro encontrado = null;
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                Libro libro = leerLibro(archivo);
                boolean activo = archivo.readBoolean();
                if (activo && libro.getCodigo() == codigo) {
                    encontrado = libro;
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
    public void actualizar(int codigo, Libro libro) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                int codigoLeido = archivo.readInt();
                archivo.skipBytes((TAM_TITULO * 2) + (TAM_CATEGORIA * 2) + 4 + 4 + 1);
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido == codigo) {
                    archivo.seek(posicion);
                    archivo.writeInt(libro.getCodigo());
                    escribirTexto(archivo, libro.getTitulo(), TAM_TITULO);
                    escribirTexto(archivo, libro.getCategoria().toString(), TAM_CATEGORIA);
                    archivo.writeInt(libro.getAnioPublicacion());
                    archivo.writeInt(libro.getAutor().getCodigo());
                    archivo.writeBoolean(libro.isEstaDisponible());
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
    public void eliminar(int codigo) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                int codigoLeido = archivo.readInt();
                archivo.skipBytes((TAM_TITULO * 2) + (TAM_CATEGORIA * 2) + 4 + 4 + 1);
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido == codigo) {
                    archivo.seek(posicion + 4 + (TAM_TITULO * 2) + (TAM_CATEGORIA * 2) + 4 + 4 + 1);
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
    public List<Libro> listar() {
        List<Libro> lista = new ArrayList<>();
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                Libro libro = leerLibro(archivo);
                boolean activo = archivo.readBoolean();
                if (activo) lista.add(libro);
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        return lista;
    }
    
}
