package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.BibliotecarioDAO;
import ec.edu.ups.biblioteca.exceptions.CampoVacioException;
import ec.edu.ups.biblioteca.exceptions.CaracterInvalidoException;
import ec.edu.ups.biblioteca.exceptions.CedulaInvalidaException;
import ec.edu.ups.biblioteca.exceptions.DatoInvalidoException;
import ec.edu.ups.biblioteca.exceptions.FormatoInvalidoException;
import ec.edu.ups.biblioteca.exceptions.TextoInvalidoException;
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
        try {
            String nombreCompleto = registrarBibliotecarioView.getTxtNombre().getText();
            String cedula = registrarBibliotecarioView.getTxtCedula().getText();
            String edadTexto = registrarBibliotecarioView.getTxtEdad().getText();
            String codigoEmpleado = registrarBibliotecarioView.getTxtCodigoEmpleado().getText();

            validarCamposBibliotecario(nombreCompleto, cedula, edadTexto, codigoEmpleado);

            int edad = Integer.parseInt(edadTexto.trim());
            Bibliotecario bibliotecario = new Bibliotecario(nombreCompleto, cedula, edad, codigoEmpleado);
            bibliotecarioDAO.crear(bibliotecario);
            registrarBibliotecarioView.mostrarInformacion("msgBibliotecarioRegistrado");
            registrarBibliotecarioView.limpiarCampos();
        }catch(CampoVacioException ex1) {
            registrarBibliotecarioView.mostrarInformacion(ex1.getMessage());
        }catch(TextoInvalidoException ex2) {
            registrarBibliotecarioView.mostrarInformacion(ex2.getMessage());
        }catch(DatoInvalidoException ex3) {
            registrarBibliotecarioView.mostrarInformacion(ex3.getMessage());
        }catch(CaracterInvalidoException ex4) {
            registrarBibliotecarioView.mostrarInformacion(ex4.getMessage());
        }catch(CedulaInvalidaException ex5) {
            registrarBibliotecarioView.mostrarInformacion(ex5.getMessage());
        }catch(FormatoInvalidoException ex6){
            registrarBibliotecarioView.mostrarInformacion(ex6.getMessage());
        }
    }
    
    public void validarCamposBibliotecario(String nombreCompleto, String cedula, String edadTexto, String codigoEmpleado)
            throws CampoVacioException, TextoInvalidoException, DatoInvalidoException, CaracterInvalidoException, CedulaInvalidaException, FormatoInvalidoException{
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new CampoVacioException("msgBibliotecarioCedulaVacio");
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new CampoVacioException("msgBibliotecarioNombreVacio");
        }
        if (edadTexto == null || edadTexto.trim().isEmpty()) {
            throw new CampoVacioException("msgBibliotecarioEdadVacio");
        }
        if (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) {
            throw new CampoVacioException("msgBibliotecarioCodigoVacio");
        }
        if (contieneNumeros(nombreCompleto)) {
                throw new TextoInvalidoException("msgBibliotecarioNombreNumeros");
           }
        if (!esNumeroEntero(cedula.trim())) {
            throw new DatoInvalidoException("msgBibliotecarioCedulaInvalida");
        }
        if (!esNumeroEntero(edadTexto.trim())) {
            throw new DatoInvalidoException("msgBibliotecarioEdadInvalida");
        }
        if (codigoEmpleado.trim().length() > 10) {
            throw new DatoInvalidoException("msgBibliotecarioCodigoLongitud");
        }
        if(!caracterValido(nombreCompleto)){
            throw new CaracterInvalidoException("msgBibliotecarioNombreLongitud");
        }
        if (!esCedulaValida(cedula.trim())) {
        throw new CedulaInvalidaException("msgBibliotecarioCedulaNoValida");
        }
        if(!iniciaConEMP(codigoEmpleado)){
            throw new FormatoInvalidoException("msgBibliotecarioCodigoFormato");
        }
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
        try{
            String codigoEmpleado = buscarBibliotecarioView.getTxtCodigo().getText();
            if (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) {
                throw new CampoVacioException("msgBibliotecarioCodigoVacio");
            }
            Bibliotecario bibliotecario = bibliotecarioDAO.buscar(codigoEmpleado.trim());

            if (bibliotecario != null) {
                buscarBibliotecarioView.getTxtNombre().setText(bibliotecario.getNombre());
                buscarBibliotecarioView.getTxtCedula().setText(bibliotecario.getCedula());
                buscarBibliotecarioView.getTxtEdad().setText(String.valueOf(bibliotecario.getEdad()));
            } else {
                buscarBibliotecarioView.mostrarInformacion("msgBibliotecarioNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            buscarBibliotecarioView.mostrarInformacion(ex1.getMessage());
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
        try{
            String codigoEmpleado = actualizarBibliotecarioView.getTxtCodigo().getText();
            if (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) {
                throw new CampoVacioException("msgBibliotecarioCodigoVacio");
            }
            Bibliotecario bibliotecario = bibliotecarioDAO.buscar(codigoEmpleado.trim());

            if (bibliotecario != null) {
                actualizarBibliotecarioView.getTxtNombre().setText(bibliotecario.getNombre());
                actualizarBibliotecarioView.getTxtCedula().setText(bibliotecario.getCedula());
                actualizarBibliotecarioView.getTxtEdad().setText(String.valueOf(bibliotecario.getEdad()));
            } else {
                actualizarBibliotecarioView.mostrarInformacion("msgBibliotecarioNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            actualizarBibliotecarioView.mostrarInformacion(ex1.getMessage());
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
        try{
            String nombreCompleto = actualizarBibliotecarioView.getTxtNombre().getText();
            String cedula = actualizarBibliotecarioView.getTxtCedula().getText();
            String edadTexto = actualizarBibliotecarioView.getTxtEdad().getText();
            String codigoEmpleado = actualizarBibliotecarioView.getTxtCodigo().getText();
            
            validarCamposBibliotecario(nombreCompleto, cedula, edadTexto, codigoEmpleado);
            
            int edad = Integer.parseInt(edadTexto.trim());
            Bibliotecario bibliotecario = new Bibliotecario(nombreCompleto, cedula, edad, codigoEmpleado);
            bibliotecarioDAO.actualizar(codigoEmpleado, bibliotecario);
            actualizarBibliotecarioView.mostrarInformacion("msgBibliotecarioActualizado");
        }catch(CampoVacioException ex1){
            actualizarBibliotecarioView.mostrarInformacion(ex1.getMessage());
        }catch(TextoInvalidoException ex2){
            actualizarBibliotecarioView.mostrarInformacion(ex2.getMessage());
        }catch(DatoInvalidoException ex3){
            actualizarBibliotecarioView.mostrarInformacion(ex3.getMessage());
        }catch(CaracterInvalidoException ex4){
            actualizarBibliotecarioView.mostrarInformacion(ex4.getMessage());
        }catch(CedulaInvalidaException ex5){
            actualizarBibliotecarioView.mostrarInformacion(ex5.getMessage());
        }catch(FormatoInvalidoException ex6){
            actualizarBibliotecarioView.mostrarInformacion(ex6.getMessage());
        }   
        

        
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
        try{
            String codigoEmpleado = eliminarBibliotecarioView.getTxtCodigo().getText();
            if (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) {
                throw new CampoVacioException("msgBibliotecarioCodigoVacio");
            }
            Bibliotecario bibliotecario = bibliotecarioDAO.buscar(codigoEmpleado.trim());

            if (bibliotecario != null) {
                eliminarBibliotecarioView.getTxtNombre().setText(bibliotecario.getNombre());
                eliminarBibliotecarioView.getTxtCedula().setText(bibliotecario.getCedula());
                eliminarBibliotecarioView.getTxtEdad().setText(String.valueOf(bibliotecario.getEdad()));
            } else {
                eliminarBibliotecarioView.mostrarInformacion("msgBibliotecarioNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            eliminarBibliotecarioView.mostrarInformacion(ex1.getMessage());
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
        try{
            String codigoEmpleado = eliminarBibliotecarioView.getTxtCodigo().getText();
            if (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) {
                throw new CampoVacioException("msgBibliotecarioCodigoVacio");
            }
            boolean confirmado = eliminarBibliotecarioView.confirmarEliminacion("msgBibliotecarioConfirmarEliminar");
            if (confirmado) {
                bibliotecarioDAO.eliminar(codigoEmpleado.trim());
                eliminarBibliotecarioView.mostrarInformacion("msgBibliotecarioEliminado");
            }
        }catch (CampoVacioException ex1) {
            eliminarBibliotecarioView.mostrarInformacion(ex1.getMessage());
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
    
    public boolean caracterValido(String texto){
        return texto.trim().length()<= 60;
    }
    
    public boolean esCedulaValida(String cedula){
        if (cedula.length() != 10) {
            return false;
        }
        if (!esNumeroEntero(cedula)) {
            return false;
        }
        int[] digitos = new int[10];
        for (int i = 0; i < 10; i++) {
            digitos[i] = Character.getNumericValue(cedula.charAt(i));
        }
        int codigoProvincia = digitos[0] * 10 + digitos[1];
        if (codigoProvincia < 1 || codigoProvincia > 24) {
            return false;
        }
        if (digitos[2] >= 6) {
            return false;
        }
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int valor = digitos[i];
            if (i % 2 == 0) {
                valor = valor * 2;
                if (valor > 9) {
                    valor = valor - 9;
                }
            }
            suma = suma + valor;
        }
        int decenaSuperior = ((suma / 10) + 1) * 10;
        int digitoVerificador = decenaSuperior - suma;
        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }
        return digitoVerificador == digitos[9];
    }
    
    public boolean iniciaConEMP(String texto) {
        return texto.trim().toUpperCase().startsWith("EMP");
    }
}