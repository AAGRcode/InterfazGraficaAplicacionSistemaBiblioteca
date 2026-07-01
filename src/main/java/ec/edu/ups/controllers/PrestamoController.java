package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.models.Bibliotecario;
import ec.edu.ups.models.Libro;
import ec.edu.ups.models.Prestamo;
import ec.edu.ups.models.Usuario;
import ec.edu.ups.views.ListarPrestamoView;
import ec.edu.ups.views.RegistrarDevolucionView;
import ec.edu.ups.views.RegistrarPrestamoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrestamoController {
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private RegistrarPrestamoView registrarPrestamoView;
    private RegistrarDevolucionView registrarDevolucionView;
    private ListarPrestamoView listarPrestamoView;

    public PrestamoController(PrestamoDAO prestamoDAO, UsuarioDAO usuarioDAO,
            RegistrarPrestamoView registrarPrestamoView, RegistrarDevolucionView registrarDevolucionView,
            ListarPrestamoView listarPrestamoView) {
        this.prestamoDAO = prestamoDAO;
        this.usuarioDAO = usuarioDAO;
        this.registrarPrestamoView = registrarPrestamoView;
        this.registrarDevolucionView = registrarDevolucionView;
        this.listarPrestamoView = listarPrestamoView;
        configurarEventosRegistrarPrestamo();
        configurarEventosBuscarDevolucion();
        configurarEventosRegistrarDevolucion();
        configurarEventosListarPrestamos();
    }

    public void registrarPrestamo() {
        String cedula = registrarPrestamoView.getTxtCedula().getText();
        Usuario usuario = usuarioDAO.buscar(cedula);
        Libro libro = (Libro) registrarPrestamoView.getCmbLibro().getSelectedItem();
        Bibliotecario bibliotecario = (Bibliotecario) registrarPrestamoView.getCmbBibliotecario().getSelectedItem();
        String fechaDevolucion = registrarPrestamoView.getTxtDevolucion().getText();

        if (usuario != null) {
            Prestamo prestamo = new Prestamo(0, libro, usuario, bibliotecario, fechaDevolucion);
            prestamoDAO.crear(prestamo);
            registrarPrestamoView.mostrarInformacion("Prestamo registrado exitosamente");
            listarPrestamos();
        } else {
            registrarPrestamoView.mostrarInformacion("Usuario no encontrado");
        }
    }

    public void configurarEventosRegistrarPrestamo() {
        registrarPrestamoView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPrestamo();
            }
        });
    }

    public void buscarPrestamoDevolucion() {
        int id = Integer.parseInt(registrarDevolucionView.getTxtCodigo().getText());
        Prestamo prestamo = prestamoDAO.buscar(id);

        if (prestamo != null) {
            if (prestamo.isDevuelto()) {
                registrarDevolucionView.mostrarInformacion("Este prestamo ya fue devuelto");
            } else {
                registrarDevolucionView.getTxtLibro().setText(prestamo.getLibro().getTitulo());
                registrarDevolucionView.getTxtUsuario().setText(prestamo.getUsuario().toString());
                registrarDevolucionView.getTxtDevolucion().setText(prestamo.getFechaDevolucion());
            }
        } else {
            registrarDevolucionView.mostrarInformacion("Prestamo no encontrado");
        }
    }

    public void configurarEventosBuscarDevolucion() {
        registrarDevolucionView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPrestamoDevolucion();
            }
        });
    }

    public void registrarDevolucion() {
        int id = Integer.parseInt(registrarDevolucionView.getTxtCodigo().getText());
        Prestamo prestamo = prestamoDAO.buscar(id);

        if (prestamo != null) {
            prestamo.setDevuelto(true);
            prestamoDAO.actualizar(id, prestamo);
            registrarDevolucionView.mostrarInformacion("Devolucion registrada exitosamente");
            registrarDevolucionView.limpiarCampos();
            listarPrestamos();
        } else {
            registrarDevolucionView.mostrarInformacion("Prestamo no encontrado");
        }
    }

    public void configurarEventosRegistrarDevolucion() {
        registrarDevolucionView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDevolucion();
            }
        });
    }

    public void listarPrestamos() {
        List<Prestamo> lista = prestamoDAO.listar();
        listarPrestamoView.cargarDatos(lista);
    }

    public void configurarEventosListarPrestamos() {
        listarPrestamoView.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                listarPrestamos();
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                listarPrestamos();
            }
        });
    }
}
