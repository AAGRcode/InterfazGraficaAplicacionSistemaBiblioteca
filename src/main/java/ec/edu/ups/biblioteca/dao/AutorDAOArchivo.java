/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Autor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class AutorDAOArchivo implements AutorDAO{
    private static final int TAM_NOMBRE = 60;
    private static final int TAM_NACIONALIDAD = 20;
    private static final int TAM_REGISTRO = 4 + (TAM_NOMBRE * 2) + (TAM_NACIONALIDAD * 2) + 4 + 1;
    private String ruta;

    public AutorDAOArchivo(String ruta) { 
        this.ruta = ruta; 
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
    public void crear(Autor autor) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            archivo.seek(archivo.length());
            archivo.writeInt(autor.getCodigo());
            escribirTexto(archivo, autor.getNombre(), TAM_NOMBRE);
            escribirTexto(archivo, autor.getNacionalidad(), TAM_NACIONALIDAD);
            archivo.writeInt(autor.getAnioNacimiento());
            archivo.writeBoolean(true);
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de escritura");
        }
    }

    @Override
    public Autor buscar(int codigo) {
        Autor encontrado = null;
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                int codigoLeido = archivo.readInt();
                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String nacionalidad = leerTexto(archivo, TAM_NACIONALIDAD);
                int anio = archivo.readInt();
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido == codigo) {
                    encontrado = new Autor(codigoLeido, nombre, nacionalidad, anio);
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
    public void actualizar(int codigo, Autor autor) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                int codigoLeido = archivo.readInt();
                archivo.skipBytes((TAM_NOMBRE * 2) + (TAM_NACIONALIDAD * 2) + 4);
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido == codigo) {
                    archivo.seek(posicion);
                    archivo.writeInt(autor.getCodigo());
                    escribirTexto(archivo, autor.getNombre(), TAM_NOMBRE);
                    escribirTexto(archivo, autor.getNacionalidad(), TAM_NACIONALIDAD);
                    archivo.writeInt(autor.getAnioNacimiento());
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
                archivo.skipBytes((TAM_NOMBRE * 2) + (TAM_NACIONALIDAD * 2) + 4);
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido == codigo) {
                    archivo.seek(posicion + 4 + (TAM_NOMBRE * 2) + (TAM_NACIONALIDAD * 2) + 4);
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
    public List<Autor> listar() {
        List<Autor> lista = new ArrayList<>();
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                int codigo = archivo.readInt();
                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String nacionalidad = leerTexto(archivo, TAM_NACIONALIDAD);
                int anio = archivo.readInt();
                boolean activo = archivo.readBoolean();
                if (activo) lista.add(new Autor(codigo, nombre, nacionalidad, anio));
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        return lista;
    }
}
