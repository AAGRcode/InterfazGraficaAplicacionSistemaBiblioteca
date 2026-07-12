package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.exceptions.CampoVacioException;
import ec.edu.ups.biblioteca.exceptions.DatoInvalidoException;
import ec.edu.ups.biblioteca.exceptions.TextoInvalidoException;
import ec.edu.ups.models.Autor;
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
        try {
            String codigoTexto = registrarLibroView.getTxtCodigo().getText();
            String nombre = registrarLibroView.getTxtTitulo().getText();
            String anioTexto = registrarLibroView.getTxtAnioPublicacion().getText();
            Autor autor = (Autor) registrarLibroView.getCmbAutor().getSelectedItem();
            String categoria = (String) registrarLibroView.getCmbCategoria().getSelectedItem();

            validarCamposLibro(codigoTexto, nombre, anioTexto);

            int codigo = Integer.parseInt(codigoTexto.trim());
            int anioPublicacion = Integer.parseInt(anioTexto.trim());
            Libro libro = new Libro(codigo, nombre, categoria, anioPublicacion, autor);
            libroDAO.crear(libro);
            registrarLibroView.mostrarInformacion("msgLibroRegistrado");
            registrarLibroView.limpiarCampos();
        } catch (CampoVacioException ex1) {
            registrarLibroView.mostrarInformacion(ex1.getMessage());
        } catch (TextoInvalidoException ex2) {
            registrarLibroView.mostrarInformacion(ex2.getMessage());
        } catch (DatoInvalidoException ex3) {
            registrarLibroView.mostrarInformacion(ex3.getMessage());
        }

    }

    public void validarCamposLibro(String codigoTexto, String nombre, String anioTexto)
            throws CampoVacioException, TextoInvalidoException, DatoInvalidoException {
        if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
            throw new CampoVacioException("msgLibroCodigoVacio");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new CampoVacioException("msgLibroTituloVacio");
        }
        if (anioTexto == null || anioTexto.trim().isEmpty()) {
            throw new CampoVacioException("msgLibroAnioVacio");
        }
        if (contieneNumeros(nombre)) {
            throw new TextoInvalidoException("msgLibroTituloNumeros");
        }
        if (!esNumeroEntero(codigoTexto.trim())) {
            throw new DatoInvalidoException("msgLibroCodigoInvalido");
        }
        if (!esNumeroEntero(anioTexto.trim())) {
            throw new DatoInvalidoException("msgLibroAnioInvalido");
        }
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
        try {
            String codigoTexto = buscarLibroView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgLibroCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgLibroCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            Libro libro = libroDAO.buscar(codigo);
            if (libro != null) {
                buscarLibroView.getTxtTitulo().setText(libro.getTitulo());
                buscarLibroView.getTxtAutor().setText(libro.getAutor().getNombre());
                buscarLibroView.getTxtCategoria().setText(libro.getCategoria());
                buscarLibroView.getTxtAnioPublicacion().setText(String.valueOf(libro.getAnioPublicacion()));

            } else {
                buscarLibroView.mostrarInformacion("msgLibroNoEncontrado");
            }
        } catch (CampoVacioException ex1) {
            buscarLibroView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            buscarLibroView.mostrarInformacion(ex2.getMessage());
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
        try {
            String codigoTexto = actualizarLibroView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgLibroCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgLibroCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            Libro libro = libroDAO.buscar(codigo);

            if (libro != null) {
                actualizarLibroView.getTxtTitulo().setText(libro.getTitulo());
                actualizarLibroView.getCmbCategoria().setSelectedItem(libro.getCategoria());
                actualizarLibroView.getTxtAnioPublicacion().setText(String.valueOf(libro.getAnioPublicacion()));
                actualizarLibroView.getCmbAutor().setSelectedItem(libro.getAutor());
            } else {
                actualizarLibroView.mostrarInformacion("msgLibroNoEncontrado");
            }
        } catch (CampoVacioException ex1) {
            actualizarLibroView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            actualizarLibroView.mostrarInformacion(ex2.getMessage());
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
        try {
            String codigoTexto = actualizarLibroView.getTxtCodigo().getText();
            String nombre = actualizarLibroView.getTxtTitulo().getText();
            String anioTexto = actualizarLibroView.getTxtAnioPublicacion().getText();
            Autor autor = (Autor) registrarLibroView.getCmbAutor().getSelectedItem();
            String categoria = (String) actualizarLibroView.getCmbCategoria().getSelectedItem();

            validarCamposLibro(codigoTexto, nombre, anioTexto);
            int codigo = Integer.parseInt(codigoTexto.trim());
            int anioPublicacion = Integer.parseInt(anioTexto.trim());
            Libro libro = new Libro(codigo, nombre, categoria, anioPublicacion, autor);
            libroDAO.actualizar(codigo, libro);
            actualizarLibroView.mostrarInformacion("msgLibroActualizado");
        } catch (CampoVacioException ex1) {
            actualizarLibroView.mostrarInformacion(ex1.getMessage());
        } catch (TextoInvalidoException ex2) {
            actualizarLibroView.mostrarInformacion(ex2.getMessage());
        } catch (DatoInvalidoException ex3) {
            actualizarLibroView.mostrarInformacion(ex3.getMessage());
        }

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
        try {
            String codigoTexto = eliminarLibroView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgLibroCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgLibroCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            Libro libro = libroDAO.buscar(codigo);
            if (libro != null) {
                eliminarLibroView.getTxtTitulo().setText(libro.getTitulo());
                eliminarLibroView.getTxtAutor().setText(libro.getAutor().getNombre());
                eliminarLibroView.getTxtCategoria().setText(libro.getCategoria());
                eliminarLibroView.getTxtAnioPublicacion().setText(String.valueOf(libro.getAnioPublicacion()));
            } else {
                eliminarLibroView.mostrarInformacion("msgLibroNoEncontrado");
            }
        } catch (CampoVacioException ex1) {
            eliminarLibroView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            eliminarLibroView.mostrarInformacion(ex2.getMessage());
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
        try {
            String codigoTexto = eliminarLibroView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgLibroCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgLibroCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            boolean confirmado = eliminarLibroView.confirmarEliminacion("msgLibroConfirmarEliminar");
            if (confirmado) {
                libroDAO.eliminar(codigo);
                eliminarLibroView.mostrarInformacion("msgLibroEliminado");
            }
        } catch (CampoVacioException ex1) {
            eliminarLibroView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            eliminarLibroView.mostrarInformacion(ex2.getMessage());
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

    public boolean contieneNumeros(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (Character.isDigit(caracter)) {
                return true;
            }
        }
        return false;
    }

    public boolean esNumeroEntero(String texto) {
        if (texto.isEmpty()) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (!Character.isDigit(caracter)) {
                return false;
            }
        }
        return true;
    }
}
