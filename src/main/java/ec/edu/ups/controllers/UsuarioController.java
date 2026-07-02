package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.models.Usuario;
import ec.edu.ups.views.ActualizarUsuarioView;
import ec.edu.ups.views.BuscarUsuarioView;
import ec.edu.ups.views.EliminarUsuarioView;
import ec.edu.ups.views.ListarUsuarioView;
import ec.edu.ups.views.RegistrarUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    private RegistrarUsuarioView registrarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private ActualizarUsuarioView actualizarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private ListarUsuarioView listarUsuarioView;

    public UsuarioController(UsuarioDAO usuarioDAO, RegistrarUsuarioView registrarUsuarioView, BuscarUsuarioView buscarUsuarioView, ActualizarUsuarioView actualizarUsuarioView, EliminarUsuarioView eliminarUsuarioView, ListarUsuarioView listarUsuarioView) {
        this.usuarioDAO = usuarioDAO;
        this.registrarUsuarioView = registrarUsuarioView;
        this.buscarUsuarioView = buscarUsuarioView;
        this.actualizarUsuarioView = actualizarUsuarioView;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.listarUsuarioView = listarUsuarioView;
        configurarEventosRegistrarUsuario();
        configurarEventosBuscarUsuario();
        configurarEventosBuscarActualizar();
        configurarEventosActualizarUsuario();
        configurarEventosBuscarEliminar();
        configurarEventosEliminarUsuario();
        configurarEventosListarUsuarios();
    }

    public void registrarUsuario() {
        String nombreCompleto = registrarUsuarioView.getTxtNombre().getText();
        String cedula = registrarUsuarioView.getTxtCedula().getText();
        int edad = Integer.parseInt(registrarUsuarioView.getTxtEdad().getText());
        String correoElectronico = registrarUsuarioView.getTxtCorreo().getText();

        Usuario usuario = new Usuario(nombreCompleto, cedula, edad, correoElectronico);
        usuarioDAO.crear(usuario);
        registrarUsuarioView.mostrarInformacion("Usuario registrado exitosamente");
        registrarUsuarioView.limpiarCampos();
    }

    public void configurarEventosRegistrarUsuario() {
        registrarUsuarioView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    public void buscarUsuario() {
        String cedula = buscarUsuarioView.getTxtCedula().getText();
        Usuario usuario = usuarioDAO.buscar(cedula);

        if (usuario != null) {
            buscarUsuarioView.getTxtNombre().setText(usuario.getNombreCompleto());
            buscarUsuarioView.getTxtEdad().setText(String.valueOf(usuario.getEdad()));
            buscarUsuarioView.getTxtCorreo().setText(usuario.getCorreoElectronico());
        } else {
            buscarUsuarioView.mostrarInformacion("Usuario no encontrado");
        }
    }

    public void configurarEventosBuscarUsuario() {
        buscarUsuarioView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
    }

    public void buscarUsuarioActualizar() {
        String cedula = actualizarUsuarioView.getTxtCedula().getText();
        Usuario usuario = usuarioDAO.buscar(cedula);

        if (usuario != null) {
            actualizarUsuarioView.getTxtNombre().setText(usuario.getNombreCompleto());
            actualizarUsuarioView.getTxtEdad().setText(String.valueOf(usuario.getEdad()));
            actualizarUsuarioView.getTxtCorreo().setText(usuario.getCorreoElectronico());
        } else {
            actualizarUsuarioView.mostrarInformacion("Usuario no encontrado");
        }
    }

    public void configurarEventosBuscarActualizar() {
        actualizarUsuarioView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioActualizar();
            }
        });
    }

    public void actualizarUsuario() {
        String cedula = actualizarUsuarioView.getTxtCedula().getText();
        String nombreCompleto = actualizarUsuarioView.getTxtNombre().getText();
        int edad = Integer.parseInt(actualizarUsuarioView.getTxtEdad().getText());
        String correoElectronico = actualizarUsuarioView.getTxtCorreo().getText();

        Usuario usuario = new Usuario(nombreCompleto, cedula, edad, correoElectronico);
        usuarioDAO.actualizar(cedula, usuario);
        actualizarUsuarioView.mostrarInformacion("Usuario actualizado exitosamente");
    }

    public void configurarEventosActualizarUsuario() {
        actualizarUsuarioView.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
    }

    public void buscarUsuarioEliminar() {
        String cedula = eliminarUsuarioView.getTxtCedulaBuscada().getText();
        Usuario usuario = usuarioDAO.buscar(cedula);

        if (usuario != null) {
            eliminarUsuarioView.getTxtCedula().setText(usuario.getCedula());
            eliminarUsuarioView.getTxtNombre().setText(usuario.getNombreCompleto());
            eliminarUsuarioView.getTxtEdad().setText(String.valueOf(usuario.getEdad()));
            eliminarUsuarioView.getTxtCorreo().setText(usuario.getCorreoElectronico());
        } else {
            eliminarUsuarioView.mostrarInformacion("Usuario no encontrado");
        }
    }

    public void configurarEventosBuscarEliminar() {
        eliminarUsuarioView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioEliminar();
            }
        });
    }

    public void eliminarUsuario() {
        String cedula = eliminarUsuarioView.getTxtCedulaBuscada().getText();
        boolean confirmado = eliminarUsuarioView.confirmarEliminacion("Esta seguro que desea eliminar al usuario?");
        if(confirmado){
            usuarioDAO.eliminar(cedula);
            eliminarUsuarioView.mostrarInformacion("Usuario eliminado exitosamente");
        }
        
    }

    public void configurarEventosEliminarUsuario() {
        eliminarUsuarioView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
    }

    public void listarUsuarios() {
        List<Usuario> lista = usuarioDAO.listar();
        listarUsuarioView.cargarDatos(lista);
    }

    public void configurarEventosListarUsuarios() {
        listarUsuarioView.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                listarUsuarios();
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                listarUsuarios();
            }
        });
    }
}
