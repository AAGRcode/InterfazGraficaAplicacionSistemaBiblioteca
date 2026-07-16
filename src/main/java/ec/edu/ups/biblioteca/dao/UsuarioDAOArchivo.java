/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.models.Usuario;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class UsuarioDAOArchivo implements UsuarioDAO {
    private static final int TAM_NOMBRE = 60;
    private static final int TAM_CEDULA = 10;
    private static final int TAM_CORREO = 60;
    private static final int TAM_REGISTRO = (TAM_NOMBRE * 2) + (TAM_CEDULA * 2) + 4 + (TAM_CORREO * 2) + 1;
    private String ruta;

    public UsuarioDAOArchivo(String ruta) { this.ruta = ruta; }

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
    public void crear(Usuario usuario) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            archivo.seek(archivo.length());
            escribirTexto(archivo, usuario.getNombreCompleto(), TAM_NOMBRE);
            escribirTexto(archivo, usuario.getCedula(), TAM_CEDULA);
            archivo.writeInt(usuario.getEdad());
            escribirTexto(archivo, usuario.getCorreoElectronico(), TAM_CORREO);
            archivo.writeBoolean(true);
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de escritura");
        }
    }

    @Override
    public Usuario buscar(String cedula) {
        Usuario encontrado = null;
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String cedulaLeida = leerTexto(archivo, TAM_CEDULA);
                int edad = archivo.readInt();
                String correo = leerTexto(archivo, TAM_CORREO);
                boolean activo = archivo.readBoolean();
                if (activo && cedulaLeida.equals(cedula.trim())) {
                    encontrado = new Usuario(nombre, cedulaLeida, edad, correo);
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
    public void actualizar(String cedula, Usuario usuario) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                archivo.skipBytes(TAM_NOMBRE * 2);
                String cedulaLeida = leerTexto(archivo, TAM_CEDULA);
                archivo.skipBytes(4 + (TAM_CORREO * 2));
                boolean activo = archivo.readBoolean();
                if (activo && cedulaLeida.equals(cedula.trim())) {
                    archivo.seek(posicion);
                    escribirTexto(archivo, usuario.getNombreCompleto(), TAM_NOMBRE);
                    escribirTexto(archivo, usuario.getCedula(), TAM_CEDULA);
                    archivo.writeInt(usuario.getEdad());
                    escribirTexto(archivo, usuario.getCorreoElectronico(), TAM_CORREO);
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
    public void eliminar(String cedula) {
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "rw");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                long posicion = i * TAM_REGISTRO;
                archivo.seek(posicion);
                archivo.skipBytes(TAM_NOMBRE * 2);
                String cedulaLeida = leerTexto(archivo, TAM_CEDULA);
                archivo.skipBytes(4 + (TAM_CORREO * 2));
                boolean activo = archivo.readBoolean();
                if (activo && cedulaLeida.equals(cedula.trim())) {
                    archivo.seek(posicion + (TAM_NOMBRE * 2) + (TAM_CEDULA * 2) + 4 + (TAM_CORREO * 2));
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
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            RandomAccessFile archivo = new RandomAccessFile(ruta, "r");
            long cantidad = archivo.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidad; i++) {
                archivo.seek(i * TAM_REGISTRO);
                String nombre = leerTexto(archivo, TAM_NOMBRE);
                String cedula = leerTexto(archivo, TAM_CEDULA);
                int edad = archivo.readInt();
                String correo = leerTexto(archivo, TAM_CORREO);
                boolean activo = archivo.readBoolean();
                if (activo) lista.add(new Usuario(nombre, cedula, edad, correo));
            }
            archivo.close();
        } catch (IOException ex) {
            System.err.println("Error de lectura");
        }
        return lista;
    }
    
}
