package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.BibliotecarioDAO;
import ec.edu.ups.models.Bibliotecario;
import ec.edu.ups.views.ActualizarBibliotecarioView;
import ec.edu.ups.views.BuscarBibliotecarioView;
import ec.edu.ups.views.EliminarBibliotecarioView;
import ec.edu.ups.views.ListarBibliotecarioView;
import ec.edu.ups.views.RegistrarBibliotecarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class BibliotecarioController {
    private BibliotecarioDAO bibliotecarioDAO;
    private RegistrarBibliotecarioView registrarBibliotecarioView;
    private BuscarBibliotecarioView buscarBibliotecarioView;
    private ActualizarBibliotecarioView actualizarBibliotecarioView;
    private EliminarBibliotecarioView eliminarBibliotecarioView;
    private ListarBibliotecarioView listarBibliotecarioView;

    public BibliotecarioController(BibliotecarioDAO bibliotecarioDAO, RegistrarBibliotecarioView registrarBibliotecarioView, BuscarBibliotecarioView buscarBibliotecarioView, ActualizarBibliotecarioView actualizarBibliotecarioView, EliminarBibliotecarioView eliminarBibliotecarioView, ListarBibliotecarioView listarBibliotecarioView) {
        this.bibliotecarioDAO = bibliotecarioDAO;
        this.registrarBibliotecarioView = registrarBibliotecarioView;
        this.buscarBibliotecarioView = buscarBibliotecarioView;
        this.actualizarBibliotecarioView = actualizarBibliotecarioView;
        this.eliminarBibliotecarioView = eliminarBibliotecarioView;
        this.listarBibliotecarioView = listarBibliotecarioView;
        configurarEventosRegistrarBibliotecario();
        configurarEventosBuscarBibliotecario();
        configurarEventosBuscarActualizar();
        configurarEventosActualizarBibliotecario();
        configurarEventosBuscarEliminar();
        configurarEventosEliminarBibliotecario();
        configurarEventosListarBibliotecarios();
    }

    public void registrarBibliotecario() {
        String nombreCompleto = registrarBibliotecarioView.getTxtNombre().getText();
        String cedula = registrarBibliotecarioView.getTxtCedula().getText();
        int edad = Integer.parseInt(registrarBibliotecarioView.getTxtEdad().getText());
        String codigoEmpleado = registrarBibliotecarioView.getTxtCodigoEmpleado().getText();

        Bibliotecario bibliotecario = new Bibliotecario(nombreCompleto, cedula, edad, codigoEmpleado);
        bibliotecarioDAO.crear(bibliotecario);
        registrarBibliotecarioView.mostrarInformacion("Bibliotecario registrado exitosamente");
        registrarBibliotecarioView.limpiarCampos();
    }

    public void configurarEventosRegistrarBibliotecario() {
        registrarBibliotecarioView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarBibliotecario();
            }
        });
    }

    public void buscarBibliotecario() {
        String codigoEmpleado = buscarBibliotecarioView.getTxtCodigo().getText();
        Bibliotecario bibliotecario = bibliotecarioDAO.buscar(codigoEmpleado);

        if (bibliotecario != null) {
            buscarBibliotecarioView.getTxtNombre().setText(bibliotecario.getNombre());
            buscarBibliotecarioView.getTxtCedula().setText(bibliotecario.getCedula());
            buscarBibliotecarioView.getTxtEdad().setText(String.valueOf(bibliotecario.getEdad()));
        } else {
            buscarBibliotecarioView.mostrarInformacion("Bibliotecario no encontrado");
        }
    }

    public void configurarEventosBuscarBibliotecario() {
        buscarBibliotecarioView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarBibliotecario();
            }
        });
    }

    public void buscarBibliotecarioActualizar() {
        String codigoEmpleado = actualizarBibliotecarioView.getTxtCodigo().getText();
        Bibliotecario bibliotecario = bibliotecarioDAO.buscar(codigoEmpleado);

        if (bibliotecario != null) {
            actualizarBibliotecarioView.getTxtNombre().setText(bibliotecario.getNombre());
            actualizarBibliotecarioView.getTxtCedula().setText(bibliotecario.getCedula());
            actualizarBibliotecarioView.getTxtEdad().setText(String.valueOf(bibliotecario.getEdad()));
        } else {
            actualizarBibliotecarioView.mostrarInformacion("Bibliotecario no encontrado");
        }
    }

    public void configurarEventosBuscarActualizar() {
        actualizarBibliotecarioView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarBibliotecarioActualizar();
            }
        });
    }

    public void actualizarBibliotecario() {
        String codigoEmpleado = actualizarBibliotecarioView.getTxtCodigo().getText();
        String nombreCompleto = actualizarBibliotecarioView.getTxtNombre().getText();
        String cedula = actualizarBibliotecarioView.getTxtCedula().getText();
        int edad = Integer.parseInt(actualizarBibliotecarioView.getTxtEdad().getText());

        Bibliotecario bibliotecario = new Bibliotecario(nombreCompleto, cedula, edad, codigoEmpleado);
        bibliotecarioDAO.actualizar(codigoEmpleado, bibliotecario);
        actualizarBibliotecarioView.mostrarInformacion("Bibliotecario actualizado exitosamente");
    }

    public void configurarEventosActualizarBibliotecario() {
        actualizarBibliotecarioView.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarBibliotecario();
            }
        });
    }

    public void buscarBibliotecarioEliminar() {
        String codigoEmpleado = eliminarBibliotecarioView.getTxtCodigo().getText();
        Bibliotecario bibliotecario = bibliotecarioDAO.buscar(codigoEmpleado);

        if (bibliotecario != null) {
            eliminarBibliotecarioView.getTxtNombre().setText(bibliotecario.getNombre());
            eliminarBibliotecarioView.getTxtCedula().setText(bibliotecario.getCedula());
            eliminarBibliotecarioView.getTxtEdad().setText(String.valueOf(bibliotecario.getEdad()));
        } else {
            eliminarBibliotecarioView.mostrarInformacion("Bibliotecario no encontrado");
        }
    }

    public void configurarEventosBuscarEliminar() {
        eliminarBibliotecarioView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarBibliotecarioEliminar();
            }
        });
    }

    public void eliminarBibliotecario() {
        String codigoEmpleado = eliminarBibliotecarioView.getTxtCodigo().getText();
        boolean confirmado = eliminarBibliotecarioView.confirmarEliminacion("Esta seguro que desea eliminar al bibliotecario?");
        if(confirmado){
            bibliotecarioDAO.eliminar(codigoEmpleado);
            eliminarBibliotecarioView.mostrarInformacion("Bibliotecario eliminado exitosamente");
        }
    }

    public void configurarEventosEliminarBibliotecario() {
        eliminarBibliotecarioView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarBibliotecario();
            }
        });
    }

    public void listarBibliotecarios() {
        List<Bibliotecario> lista = bibliotecarioDAO.listar();
        listarBibliotecarioView.cargarDatos(lista);
    }

    public void configurarEventosListarBibliotecarios() {
        listarBibliotecarioView.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                listarBibliotecarios();
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                listarBibliotecarios();
            }
        });
    }
}
