package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.AutorDAO;
import ec.edu.ups.models.Autor;
import ec.edu.ups.views.ActualizarAutorView;
import ec.edu.ups.views.BuscarAutorView;
import ec.edu.ups.views.EliminarAutorView;
import ec.edu.ups.views.ListarAutorView;
import ec.edu.ups.views.RegistrarAutorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class AutorController {
    private AutorDAO autorDAO;
    private RegistrarAutorView registrarAutorView;
    private BuscarAutorView buscarAutorView;
    private ActualizarAutorView actualizarAutorView;
    private EliminarAutorView eliminarAutorView;
    private ListarAutorView listarAutorView;

    public AutorController(AutorDAO autorDAO, RegistrarAutorView registrarAutorView, BuscarAutorView buscarAutorView, ActualizarAutorView actualizarAutorView, EliminarAutorView eliminarAutorView, ListarAutorView listarAutorView) {
        this.autorDAO = autorDAO;
        this.registrarAutorView = registrarAutorView;
        this.buscarAutorView = buscarAutorView;
        this.actualizarAutorView = actualizarAutorView;
        this.eliminarAutorView = eliminarAutorView;
        this.listarAutorView = listarAutorView;
        configurarEventosRegistrarAutor();
        configurarEventosBuscarAutor();
        configurarEventosBuscarActualizar();
        configurarEventosActualizarAutor();
        configurarEventosBuscarEliminar();
        configurarEventosEliminarAutor();
        configurarEventosListarAutores();
    }

    public void registrarAutor() {
        int codigo = Integer.parseInt(registrarAutorView.getTxtCodigo().getText());
        String nombreCompleto = registrarAutorView.getTxtNombre().getText();
        String nacionalidad = (String) registrarAutorView.getCmbNacionalidad().getSelectedItem();
        int anioNacimiento = Integer.parseInt(registrarAutorView.getTxtAnioNacimiento().getText());

        Autor autor = new Autor(codigo, nombreCompleto, nacionalidad, anioNacimiento);
        autorDAO.crear(autor);
        registrarAutorView.mostrarInformacion("msgAutorRegistrado");
        registrarAutorView.limpiarCampos();
    }

    public void configurarEventosRegistrarAutor() {
        registrarAutorView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarAutor();
            }
        });
    }

    public void buscarAutor() {
        int codigo = Integer.parseInt(buscarAutorView.getTxtCodigo().getText());
        Autor autor = autorDAO.buscar(codigo);

        if (autor != null) {
            buscarAutorView.getTxtNombre().setText(autor.getNombre());
            buscarAutorView.getTxtNacionalidad().setText(autor.getNacionalidad());
            buscarAutorView.getTxtAnioNacimiento().setText(String.valueOf(autor.getAnioNacimiento()));
        } else {
            buscarAutorView.mostrarInformacion("msgAutorNoEncontrado");
        }
    }

    public void configurarEventosBuscarAutor() {
        buscarAutorView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAutor();
            }
        });
    }

    public void buscarAutorActualizar() {
        int codigo = Integer.parseInt(actualizarAutorView.getTxtCodigo().getText());
        Autor autor = autorDAO.buscar(codigo);

        if (autor != null) {
            actualizarAutorView.getTxtNombre().setText(autor.getNombre());
            actualizarAutorView.getCmbNacionalidad().setSelectedItem(autor.getNacionalidad());
            actualizarAutorView.getTxtAnioNacimiento().setText(String.valueOf(autor.getAnioNacimiento()));
        } else {
            actualizarAutorView.mostrarInformacion("msgAutorNoEncontrado");
        }
    }

    public void configurarEventosBuscarActualizar() {
        actualizarAutorView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAutorActualizar();
            }
        });
    }

    public void actualizarAutor() {
        int codigo = Integer.parseInt(actualizarAutorView.getTxtCodigo().getText());
        String nombreCompleto = actualizarAutorView.getTxtNombre().getText();
        String nacionalidad = (String) actualizarAutorView.getCmbNacionalidad().getSelectedItem();
        int anioNacimiento = Integer.parseInt(actualizarAutorView.getTxtAnioNacimiento().getText());

        Autor autor = new Autor(codigo, nombreCompleto, nacionalidad, anioNacimiento);
        autorDAO.actualizar(codigo, autor);
        actualizarAutorView.mostrarInformacion("msgAutorActualizado");
    }

    public void configurarEventosActualizarAutor() {
        actualizarAutorView.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarAutor();
            }
        });
    }

    public void buscarAutorEliminar() {
        int codigo = Integer.parseInt(eliminarAutorView.getTxtCodigo().getText());
        Autor autor = autorDAO.buscar(codigo);

        if (autor != null) {
            eliminarAutorView.getTxtNombre().setText(autor.getNombre());
            eliminarAutorView.getTxtNacionalidad().setText(autor.getNacionalidad());
            eliminarAutorView.getTxtAnioNacimiento().setText(String.valueOf(autor.getAnioNacimiento()));
        } else {
            eliminarAutorView.mostrarInformacion("msgAutorNoEncontrado");
        }
    }

    public void configurarEventosBuscarEliminar() {
        eliminarAutorView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAutorEliminar();
            }
        });
    }

    public void eliminarAutor() {
        int codigo = Integer.parseInt(eliminarAutorView.getTxtCodigo().getText());
        boolean confirmado = eliminarAutorView.confirmarEliminacion("msgAutorConfirmarEliminar");
        if(confirmado){
            autorDAO.eliminar(codigo);
            eliminarAutorView.mostrarInformacion("msgAutorEliminado");
        }
    }

    public void configurarEventosEliminarAutor() {
        eliminarAutorView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAutor();
            }
        });
    }

    public void listarAutores() {
        List<Autor> lista = autorDAO.listar();
        listarAutorView.cargarDatos(lista);
    }

    public void configurarEventosListarAutores() {
        listarAutorView.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                listarAutores();
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                listarAutores();
            }
        });
    }
}
