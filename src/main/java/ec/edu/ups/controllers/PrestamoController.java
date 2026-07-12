package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.exceptions.CampoVacioException;
import ec.edu.ups.biblioteca.exceptions.DatoInvalidoException;
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
import java.util.Locale;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrestamoController {
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private RegistrarPrestamoView registrarPrestamoView;
    private RegistrarDevolucionView registrarDevolucionView;
    private ListarPrestamoView listarPrestamoView;
    private Locale idiomaActual = new Locale("es","EC");

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

    public Locale getIdiomaActual() {
        return idiomaActual;
    }

    public void setIdiomaActual(Locale idiomaActual) {
        this.idiomaActual = idiomaActual;
    }
    
    public void registrarPrestamo() {
        try{
            String cedula = registrarPrestamoView.getTxtCedula().getText();
            if (cedula == null || cedula.trim().isEmpty()) {
                throw new CampoVacioException("msgPrestamoCedulaVacio");
            }
            if (!esNumeroEntero(cedula.trim())) {
                throw new DatoInvalidoException("msgPrestamoCedulaInvalida");
            }
            Usuario usuario = usuarioDAO.buscar(cedula.trim());
            Libro libro = (Libro) registrarPrestamoView.getCmbLibro().getSelectedItem();
            Bibliotecario bibliotecario = (Bibliotecario) registrarPrestamoView.getCmbBibliotecario().getSelectedItem();
            String dia = (String) registrarPrestamoView.getCmbDia().getSelectedItem();
            String mes = (String) registrarPrestamoView.getCmbMes().getSelectedItem();
            String anio = (String) registrarPrestamoView.getCmbAño().getSelectedItem();
            String fechaDevolucion = dia + "/" + mes + "/" + anio;
            if (usuario != null) {
            Prestamo prestamo = new Prestamo(0, libro, usuario, bibliotecario, fechaDevolucion);
            prestamoDAO.crear(prestamo);
            registrarPrestamoView.mostrarInformacion("msgPrestamoRegistrado");
            registrarPrestamoView.limpiarCampos();
            listarPrestamos();
            } else {
                registrarPrestamoView.mostrarInformacion("msgPrestamoUsuarioNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            registrarPrestamoView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            registrarPrestamoView.mostrarInformacion(ex2.getMessage());
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
        try{
            String idTexto = registrarDevolucionView.getTxtCodigo().getText();

            if (idTexto == null || idTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgPrestamoCodigoVacio");
            }
            if (!esNumeroEntero(idTexto.trim())) {
                throw new DatoInvalidoException("msgPrestamoCodigoInvalido");
            }

            int id = Integer.parseInt(idTexto.trim());
            Prestamo prestamo = prestamoDAO.buscar(id);

            if (prestamo != null) {
                if (prestamo.isDevuelto()) {
                    registrarDevolucionView.mostrarInformacion("msgPrestamoYaDevuelto");
                } else {
                    registrarDevolucionView.getTxtLibro().setText(prestamo.getLibro().getTitulo());
                    registrarDevolucionView.getTxtUsuario().setText(prestamo.getUsuario().toString());
                    registrarDevolucionView.getTxtDevolucion().setText(prestamo.getFechaDevolucion());
                }
            } else {
                registrarDevolucionView.mostrarInformacion("msgPrestamoNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            registrarDevolucionView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            registrarDevolucionView.mostrarInformacion(ex2.getMessage());
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
        try{
            String idTexto = registrarDevolucionView.getTxtCodigo().getText();

            if (idTexto == null || idTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgPrestamoCodigoVacio");
            }
            if (!esNumeroEntero(idTexto.trim())) {
                throw new DatoInvalidoException("msgPrestamoCodigoInvalido");
            }

            int id = Integer.parseInt(idTexto.trim());
            Prestamo prestamo = prestamoDAO.buscar(id);

            if (prestamo != null) {
                prestamo.setDevuelto(true);
                prestamoDAO.actualizar(id, prestamo);
                registrarDevolucionView.mostrarInformacion("msgDevolucionRegistrada");
                registrarDevolucionView.limpiarCampos();
                listarPrestamos();
            } else {
                registrarDevolucionView.mostrarInformacion("msgPrestamoNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            registrarDevolucionView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            registrarDevolucionView.mostrarInformacion(ex2.getMessage());
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
    public boolean esNumeroEntero(String texto){
        if(texto.isEmpty()){
            return false;
        }
        for(int i=0; i<texto.length(); i++){
            char caracter= texto.charAt(i);
            if(!Character.isDigit(caracter)){
                return false;
            }
        }
        return true;
    }
}
