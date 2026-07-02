package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.models.Libro;
import ec.edu.ups.views.ActualizarLibroView;
import ec.edu.ups.views.BuscarLibroView;
import ec.edu.ups.views.EliminarLibroView;
import ec.edu.ups.views.ListarLibroView;
import ec.edu.ups.views.RegistrarLibroView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class LibroController {
    private LibroDAO libroDAO;
    private RegistrarLibroView registrarLibroView;
    private BuscarLibroView buscarLibroView;
    private ActualizarLibroView actualizarLibroView;
    private EliminarLibroView eliminarLibroView;
    private ListarLibroView listarLibroView;

    public LibroController(LibroDAO libroDAO, RegistrarLibroView registrarLibroView, BuscarLibroView buscarLibroView, ActualizarLibroView actualizarLibroView, EliminarLibroView eliminarLibroView, ListarLibroView listarLibroView) {
        this.libroDAO = libroDAO;
        this.registrarLibroView = registrarLibroView;
        this.buscarLibroView = buscarLibroView;
        this.actualizarLibroView = actualizarLibroView;
        this.eliminarLibroView = eliminarLibroView;
        this.listarLibroView = listarLibroView;
        configurarEventosRegistrarLibro();
        configurarEventosBuscarLibro();
        configurarEventosBuscarActualizar();
        configurarEventosActualizarLibro();
        configurarEventosBuscarEliminar();
        configurarEventosEliminarLibro();
        configurarEventosListarLibros();
    }

    public void registrarLibro() {
        int codigo = Integer.parseInt(registrarLibroView.getTxtCodigo().getText());
        String nombre = registrarLibroView.getTxtTitulo().getText();
        String categoria = registrarLibroView.getTxtCategoria().getText();
        int anioPublicacion = Integer.parseInt(registrarLibroView.getTxtAnioPublicacion().getText());

        Libro libro = new Libro(codigo, nombre, categoria, anioPublicacion);
        libroDAO.crear(libro);
        registrarLibroView.mostrarInformacion("msgLibroRegistrado");
        registrarLibroView.limpiarCampos();
    }

    public void configurarEventosRegistrarLibro() {
        registrarLibroView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarLibro();
            }
        });
    }
    
    public void buscarLibro() {
        int codigo = Integer.parseInt(buscarLibroView.getTxtCodigo().getText());
        Libro libro = libroDAO.buscar(codigo);

        if (libro != null) {
            buscarLibroView.getTxtTitulo().setText(libro.getTitulo());
            buscarLibroView.getTxtCategoria().setText(libro.getCategoria());
            buscarLibroView.getTxtAnioPublicacion().setText(String.valueOf(libro.getAnioPublicacion()));
        } else {
            buscarLibroView.mostrarInformacion("msgLibroNoEncontrado");
        }
    }

    public void configurarEventosBuscarLibro() {
        buscarLibroView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibro();
            }
        });
    }

    public void buscarLibroActualizar() {
        int codigo = Integer.parseInt(actualizarLibroView.getTxtCodigo().getText());
        Libro libro = libroDAO.buscar(codigo);

        if (libro != null) {
            actualizarLibroView.getTxtTitulo().setText(libro.getTitulo());
            actualizarLibroView.getTxtCategoria().setText(libro.getCategoria());
            actualizarLibroView.getTxtAnioPublicacion().setText(String.valueOf(libro.getAnioPublicacion()));
        } else {
            actualizarLibroView.mostrarInformacion("msgLibroNoEncontrado");
        }
    }

    public void configurarEventosBuscarActualizar() {
        actualizarLibroView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibroActualizar();
            }
        });
    }

    public void actualizarLibro() {
        int codigo = Integer.parseInt(actualizarLibroView.getTxtCodigo().getText());
        String titulo = actualizarLibroView.getTxtTitulo().getText();
        String categoria = actualizarLibroView.getTxtCategoria().getText();
        int anioPublicacion = Integer.parseInt(actualizarLibroView.getTxtAnioPublicacion().getText());

        Libro libro = new Libro(codigo, titulo, categoria, anioPublicacion);
        libroDAO.actualizar(codigo, libro);
        actualizarLibroView.mostrarInformacion("msgLibroActualizado");
    }

    public void configurarEventosActualizarLibro() {
        actualizarLibroView.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarLibro();
            }
        });
    }

    public void buscarLibroEliminar() {
        int codigo = Integer.parseInt(eliminarLibroView.getTxtCodigo().getText());
        Libro libro = libroDAO.buscar(codigo);

        if (libro != null) {
            eliminarLibroView.getTxtTitulo().setText(libro.getTitulo());
            eliminarLibroView.getTxtCategoria().setText(libro.getCategoria());
            eliminarLibroView.getTxtAnioPublicacion().setText(String.valueOf(libro.getAnioPublicacion()));
        } else {
            eliminarLibroView.mostrarInformacion("msgLibroNoEncontrado");
        }
    }

    public void configurarEventosBuscarEliminar() {
        eliminarLibroView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibroEliminar();
            }
        });
    }

    public void eliminarLibro() {
        int codigo = Integer.parseInt(eliminarLibroView.getTxtCodigo().getText());
        boolean confirmado = eliminarLibroView.confirmarEliminacion("msgLibroConfirmarEliminar");
        if(confirmado){
            libroDAO.eliminar(codigo);
            eliminarLibroView.mostrarInformacion("msgLibroEliminado");
        }
    }

    public void configurarEventosEliminarLibro() {
        eliminarLibroView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });
    }

    public void listarLibros() {
        List<Libro> lista = libroDAO.listar();
        listarLibroView.cargarDatos(lista);
    }

    public void configurarEventosListarLibros() {
        listarLibroView.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                listarLibros();
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                listarLibros();
            }
        });
    }
}
