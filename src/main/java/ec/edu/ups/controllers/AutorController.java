package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.AutorDAO;
import ec.edu.ups.biblioteca.exceptions.CampoVacioException;
import ec.edu.ups.biblioteca.exceptions.DatoInvalidoException;
import ec.edu.ups.biblioteca.exceptions.TextoInvalidoException;
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
        try{
            String codigoTexto= registrarAutorView.getTxtCodigo().getText();
            String nombreCompleto= registrarAutorView.getTxtNombre().getText();
            String anioTexto= registrarAutorView.getTxtAnioNacimiento().getText();
            String nacionalidad = (String) registrarAutorView.getCmbNacionalidad().getSelectedItem();
            
            validarCamposAutor(codigoTexto, nombreCompleto, anioTexto);
            int codigo= Integer.parseInt(codigoTexto.trim());       
            int anioNacimiento= Integer.parseInt(anioTexto.trim());
            Autor autor = new Autor(codigo, nombreCompleto, nacionalidad, anioNacimiento);
            autorDAO.crear(autor);
            registrarAutorView.mostrarInformacion("msgAutorRegistrado");
            registrarAutorView.limpiarCampos();
        }catch(CampoVacioException ex1){
            registrarAutorView.mostrarInformacion(ex1.getMessage());
        }catch(TextoInvalidoException ex2){
            registrarAutorView.mostrarInformacion(ex2.getMessage());
        }catch(DatoInvalidoException ex3){
            registrarAutorView.mostrarInformacion(ex3.getMessage());
        }
    }
    
    public void validarCamposAutor(String codigoTexto, String nombreCompleto, String anioTexto)
                throws CampoVacioException, TextoInvalidoException, DatoInvalidoException {
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgAutorCodigoVacio");
            }
            if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
                throw new CampoVacioException("msgAutorNombreVacio");
            }
            if (anioTexto == null || anioTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgAutorAnioVacio");
            }
            if (contieneNumeros(nombreCompleto)) {
                throw new TextoInvalidoException("msgAutorNombreNumeros");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgAutorCodigoInvalido");
            }
            if (!esNumeroEntero(anioTexto.trim())) {
                throw new DatoInvalidoException("msgAutorAnioInvalido");
            }
            
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
        try{
            String codigoTexto = buscarAutorView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgAutorCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgAutorCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            Autor autor = autorDAO.buscar(codigo);

            if (autor != null) {
                buscarAutorView.getTxtNombre().setText(autor.getNombre());
                buscarAutorView.getTxtNacionalidad().setText(autor.getNacionalidad());
                buscarAutorView.getTxtAnioNacimiento().setText(String.valueOf(autor.getAnioNacimiento()));
            } else {
                buscarAutorView.mostrarInformacion("msgAutorNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            buscarAutorView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            buscarAutorView.mostrarInformacion(ex2.getMessage());
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
        try{
            String codigoTexto = actualizarAutorView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgAutorCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgAutorCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            Autor autor = autorDAO.buscar(codigo);

            if (autor != null) {
                actualizarAutorView.getTxtNombre().setText(autor.getNombre());
                actualizarAutorView.getCmbNacionalidad().setSelectedItem(autor.getNacionalidad());
                actualizarAutorView.getTxtAnioNacimiento().setText(String.valueOf(autor.getAnioNacimiento()));
            } else {
                actualizarAutorView.mostrarInformacion("msgAutorNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            actualizarAutorView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            actualizarAutorView.mostrarInformacion(ex2.getMessage());
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
        try{
            String codigoTexto= actualizarAutorView.getTxtCodigo().getText();
            String nombreCompleto= actualizarAutorView.getTxtNombre().getText();
            String anioTexto= actualizarAutorView.getTxtAnioNacimiento().getText();
            String nacionalidad = (String) actualizarAutorView.getCmbNacionalidad().getSelectedItem();
            
            validarCamposAutor(codigoTexto, nombreCompleto, anioTexto);
            int codigo= Integer.parseInt(codigoTexto.trim());       
            int anioNacimiento= Integer.parseInt(anioTexto.trim());
            Autor autor = new Autor(codigo, nombreCompleto, nacionalidad, anioNacimiento);
            autorDAO.actualizar(codigo, autor);
            actualizarAutorView.mostrarInformacion("msgAutorActualizado");
        }catch(CampoVacioException ex1){
            actualizarAutorView.mostrarInformacion(ex1.getMessage());
        }catch(TextoInvalidoException ex2){
            actualizarAutorView.mostrarInformacion(ex2.getMessage());
        }catch(DatoInvalidoException ex3){
            actualizarAutorView.mostrarInformacion(ex3.getMessage());
        }

        
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
        try{
            String codigoTexto = eliminarAutorView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgAutorCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgAutorCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            Autor autor = autorDAO.buscar(codigo);

            if (autor != null) {
                eliminarAutorView.getTxtNombre().setText(autor.getNombre());
                eliminarAutorView.getTxtNacionalidad().setText(autor.getNacionalidad());
                eliminarAutorView.getTxtAnioNacimiento().setText(String.valueOf(autor.getAnioNacimiento()));
            } else {
                eliminarAutorView.mostrarInformacion("msgAutorNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            eliminarAutorView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            eliminarAutorView.mostrarInformacion(ex2.getMessage());
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
        try{
            String codigoTexto = eliminarAutorView.getTxtCodigo().getText();
            if (codigoTexto == null || codigoTexto.trim().isEmpty()) {
                throw new CampoVacioException("msgAutorCodigoVacio");
            }
            if (!esNumeroEntero(codigoTexto.trim())) {
                throw new DatoInvalidoException("msgAutorCodigoInvalido");
            }
            int codigo = Integer.parseInt(codigoTexto.trim());
            boolean confirmado = eliminarAutorView.confirmarEliminacion("msgAutorConfirmarEliminar");
            if(confirmado){
                autorDAO.eliminar(codigo);
                eliminarAutorView.mostrarInformacion("msgAutorEliminado");
            }
        }catch(CampoVacioException ex1) {
            eliminarAutorView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            eliminarAutorView.mostrarInformacion(ex2.getMessage());
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
    
    public boolean contieneNumeros(String texto){
        for(int i=0; i< texto.length(); i++){
            char caracter= texto.charAt(i);
            if(Character.isDigit(caracter)){
                return true;
            }
        }
        return false;
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
