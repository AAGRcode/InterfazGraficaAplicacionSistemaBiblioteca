package ec.edu.ups.controllers;

import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.exceptions.CampoVacioException;
import ec.edu.ups.biblioteca.exceptions.CaracterInvalidoException;
import ec.edu.ups.biblioteca.exceptions.CedulaInvalidaException;
import ec.edu.ups.biblioteca.exceptions.CorreoInvalidoException;
import ec.edu.ups.biblioteca.exceptions.DatoInvalidoException;
import ec.edu.ups.biblioteca.exceptions.TextoInvalidoException;
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
        try{
            String nombreCompleto = registrarUsuarioView.getTxtNombre().getText();
            String cedula = registrarUsuarioView.getTxtCedula().getText();
            String edadTexto = registrarUsuarioView.getTxtEdad().getText();
            String correoElectronico = registrarUsuarioView.getTxtCorreo().getText();
            
            validarCamposUsuario(nombreCompleto, cedula, edadTexto, correoElectronico);
            
            int edad = Integer.parseInt(edadTexto.trim());
            Usuario usuario = new Usuario(nombreCompleto, cedula, edad, correoElectronico);
            usuarioDAO.crear(usuario);
            registrarUsuarioView.mostrarInformacion("msgUsuarioRegistrado");
            registrarUsuarioView.limpiarCampos();
        }catch(CampoVacioException ex1){
            registrarUsuarioView.mostrarInformacion(ex1.getMessage());
        }catch(TextoInvalidoException ex2){
            registrarUsuarioView.mostrarInformacion(ex2.getMessage());
        }catch(DatoInvalidoException ex3){
            registrarUsuarioView.mostrarInformacion(ex3.getMessage());
        }catch(CaracterInvalidoException ex4){
            registrarUsuarioView.mostrarInformacion(ex4.getMessage());
        }catch(CedulaInvalidaException ex5){
            registrarUsuarioView.mostrarInformacion(ex5.getMessage());
        }catch(CorreoInvalidoException ex6){
            registrarUsuarioView.mostrarInformacion(ex6.getMessage());
        }
    }
    
    public void validarCamposUsuario(String nombreCompleto, String cedula, String edadTexto, String correoElectronico)
            throws CampoVacioException, TextoInvalidoException, DatoInvalidoException, CaracterInvalidoException, CedulaInvalidaException, CorreoInvalidoException{
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new CampoVacioException("msgUsuarioCedulaVacio");
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new CampoVacioException("msgUsuarioNombreVacio");
        }
        if (edadTexto == null || edadTexto.trim().isEmpty()) {
            throw new CampoVacioException("msgUsuarioEdadVacio");
        }
        if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
            throw new CampoVacioException("msgUsuarioCorreoVacio");
        }
        if (contieneNumeros(nombreCompleto)) {
                throw new TextoInvalidoException("msgUsuarioNombreNumeros");
           }
        if (!esNumeroEntero(cedula.trim())) {
            throw new DatoInvalidoException("msgUsuarioCedulaInvalida");
        }
        if (!esNumeroEntero(edadTexto.trim())) {
            throw new DatoInvalidoException("msgUsuarioEdadInvalida");
        }
        if(!caracterValido(nombreCompleto)){
            throw new CaracterInvalidoException("msgUsuarioNombreLongitud");
        }
        if(!caracterValido(correoElectronico)){
            throw new CaracterInvalidoException("msgUsuarioCorreoLongitud");
        }
        if (!esCedulaValida(cedula.trim())) {
            throw new CedulaInvalidaException("msgUsuarioCedulaNoValida");
        }
        if (!esCorreoValido(correoElectronico)) {
            throw new CorreoInvalidoException("msgUsuarioCorreoNoValido");
        }
        
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
        try{
            String cedula = buscarUsuarioView.getTxtCedula().getText();
            if (cedula == null || cedula.trim().isEmpty()) {
                throw new CampoVacioException("msgUsuarioCedulaVacio");
            }
            if (!esNumeroEntero(cedula.trim())) {
                throw new DatoInvalidoException("msgUsuarioCedulaInvalida");
            }
            Usuario usuario = usuarioDAO.buscar(cedula.trim());

            if (usuario != null) {
                buscarUsuarioView.getTxtNombre().setText(usuario.getNombreCompleto());
                buscarUsuarioView.getTxtEdad().setText(String.valueOf(usuario.getEdad()));
                buscarUsuarioView.getTxtCorreo().setText(usuario.getCorreoElectronico());
            } else {
                buscarUsuarioView.mostrarInformacion("msgUsuarioNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            buscarUsuarioView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            buscarUsuarioView.mostrarInformacion(ex2.getMessage());
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
        try{
            String cedula = actualizarUsuarioView.getTxtCedula().getText();
            if (cedula == null || cedula.trim().isEmpty()) {
                throw new CampoVacioException("msgUsuarioCedulaVacio");
            }
            if (!esNumeroEntero(cedula.trim())) {
                throw new DatoInvalidoException("msgUsuarioCedulaInvalida");
            }
            Usuario usuario = usuarioDAO.buscar(cedula.trim());

            if (usuario != null) {
                actualizarUsuarioView.getTxtNombre().setText(usuario.getNombreCompleto());
                actualizarUsuarioView.getTxtEdad().setText(String.valueOf(usuario.getEdad()));
                actualizarUsuarioView.getTxtCorreo().setText(usuario.getCorreoElectronico());
            } else {
                actualizarUsuarioView.mostrarInformacion("msgUsuarioNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            actualizarUsuarioView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            actualizarUsuarioView.mostrarInformacion(ex2.getMessage());
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
        try{
            String nombreCompleto = actualizarUsuarioView.getTxtNombre().getText();
            String cedula = actualizarUsuarioView.getTxtCedula().getText();
            String edadTexto = actualizarUsuarioView.getTxtEdad().getText();
            String correoElectronico = actualizarUsuarioView.getTxtCorreo().getText();
            
            validarCamposUsuario(nombreCompleto, cedula, edadTexto, correoElectronico);
            
            int edad = Integer.parseInt(edadTexto.trim());
            Usuario usuario = new Usuario(nombreCompleto, cedula, edad, correoElectronico);
            usuarioDAO.actualizar(cedula, usuario);
            actualizarUsuarioView.mostrarInformacion("msgUsuarioActualizado");
        }catch(CampoVacioException ex1){
            actualizarUsuarioView.mostrarInformacion(ex1.getMessage());
        }catch(TextoInvalidoException ex2){
            actualizarUsuarioView.mostrarInformacion(ex2.getMessage());
        }catch(DatoInvalidoException ex3){
            actualizarUsuarioView.mostrarInformacion(ex3.getMessage());
        }catch(CaracterInvalidoException ex4){
            registrarUsuarioView.mostrarInformacion(ex4.getMessage());
        }catch(CedulaInvalidaException ex5){
        actualizarUsuarioView.mostrarInformacion(ex5.getMessage());
        }catch(CorreoInvalidoException ex6){
        actualizarUsuarioView.mostrarInformacion(ex6.getMessage());
        }
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
        try{
            String cedula = eliminarUsuarioView.getTxtCedulaBuscada().getText();
            if (cedula == null || cedula.trim().isEmpty()) {
                throw new CampoVacioException("msgUsuarioCedulaVacio");
            }
            if (!esNumeroEntero(cedula.trim())) {
                throw new DatoInvalidoException("msgUsuarioCedulaInvalida");
            }
            Usuario usuario = usuarioDAO.buscar(cedula.trim());

            if (usuario != null) {
                eliminarUsuarioView.getTxtCedula().setText(usuario.getCedula());
                eliminarUsuarioView.getTxtNombre().setText(usuario.getNombreCompleto());
                eliminarUsuarioView.getTxtEdad().setText(String.valueOf(usuario.getEdad()));
                eliminarUsuarioView.getTxtCorreo().setText(usuario.getCorreoElectronico());
            } else {
                eliminarUsuarioView.mostrarInformacion("msgUsuarioNoEncontrado");
            }
        }catch (CampoVacioException ex1) {
            eliminarUsuarioView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            eliminarUsuarioView.mostrarInformacion(ex2.getMessage());
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
        try{
            String cedula = eliminarUsuarioView.getTxtCedulaBuscada().getText();
            if (cedula == null || cedula.trim().isEmpty()) {
                throw new CampoVacioException("msgUsuarioCedulaVacio");
            }
            if (!esNumeroEntero(cedula.trim())) {
                throw new DatoInvalidoException("msgUsuarioCedulaInvalida");
            }
            boolean confirmado = eliminarUsuarioView.confirmarEliminacion("msgUsuarioConfirmarEliminar");
            if (confirmado) {
                usuarioDAO.eliminar(cedula.trim());
                eliminarUsuarioView.mostrarInformacion("msgUsuarioEliminado");
            }
        }catch (CampoVacioException ex1) {
            eliminarUsuarioView.mostrarInformacion(ex1.getMessage());
        } catch (DatoInvalidoException ex2) {
            eliminarUsuarioView.mostrarInformacion(ex2.getMessage());
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
    
    public boolean esCorreoValido(String correo){
    if (correo == null || correo.trim().isEmpty()) {
        return false;
    }
    return correo.trim().contains("@");
}
}