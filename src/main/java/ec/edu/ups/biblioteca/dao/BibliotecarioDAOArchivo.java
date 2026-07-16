/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Bibliotecario;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class BibliotecarioDAOArchivo implements BibliotecarioDAO {
    private static final int TAM_NOMBRE = 60;
    private static final int TAM_CEDULA = 10;
    private static final int TAM_CODIGO = 10;
    private static final int TAM_REGISTRO = (TAM_NOMBRE * 2) + (TAM_CEDULA * 2) + 4 + (TAM_CODIGO * 2) + 1;
    private String ruta;

    public BibliotecarioDAOArchivo(String ruta) { 
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
    public void crear(Bibliotecario bibliotecario) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            archivo.seek(archivo.length());
            escribirTexto(archivo, bibliotecario.getNombre(), TAM_NOMBRE);
            escribirTexto(archivo, bibliotecario.getCedula(), TAM_CEDULA);
            archivo.writeInt(bibliotecario.getEdad());
            escribirTexto(archivo, bibliotecario.getCodigoEmpleado(), TAM_CODIGO);
            archivo.writeBoolean(true);
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de escritura");
        }
    }

    @Override
    public Bibliotecario buscar(String codigoEmpleado) {
        Bibliotecario encontrado = null;
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String cedula = leerTexto(archivo, TAM_CEDULA);
                int edad = archivo.readInt();
                String codigoLeido = leerTexto(archivo, TAM_CODIGO);
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido.equals(codigoEmpleado.trim())) {
                    encontrado = new Bibliotecario(nombre, cedula, edad, codigoLeido);
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
    public void actualizar(String codigoEmpleado, Bibliotecario bibliotecario) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                archivo.skipBytes((TAM_NOMBRE * 2) + (TAM_CEDULA * 2) + 4);
                String codigoLeido = leerTexto(archivo, TAM_CODIGO);
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido.equals(codigoEmpleado.trim())) {
                    archivo.seek(posicion);
                    escribirTexto(archivo, bibliotecario.getNombre(), TAM_NOMBRE);
                    escribirTexto(archivo, bibliotecario.getCedula(), TAM_CEDULA);
                    archivo.writeInt(bibliotecario.getEdad());
                    escribirTexto(archivo, bibliotecario.getCodigoEmpleado(), TAM_CODIGO);
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
    public void eliminar(String codigoEmpleado) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                archivo.skipBytes((TAM_NOMBRE * 2) + (TAM_CEDULA * 2) + 4);
                String codigoLeido = leerTexto(archivo, TAM_CODIGO);
                boolean activo = archivo.readBoolean();
                if (activo && codigoLeido.equals(codigoEmpleado.trim())) {
                    archivo.seek(posicion + (TAM_NOMBRE * 2) + (TAM_CEDULA * 2) + 4 + (TAM_CODIGO * 2));
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
    public List<Bibliotecario> listar() {
        List<Bibliotecario> lista = new ArrayList<>();
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String cedula = leerTexto(archivo, TAM_CEDULA);
                int edad = archivo.readInt();
                String codigo = leerTexto(archivo, TAM_CODIGO);
                boolean activo = archivo.readBoolean();
                if (activo) lista.add(new Bibliotecario(nombre, cedula, edad, codigo));
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        return lista;
    }
}
