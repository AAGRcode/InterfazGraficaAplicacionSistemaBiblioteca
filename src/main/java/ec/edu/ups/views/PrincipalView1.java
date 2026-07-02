/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.ups.views;

import ec.edu.ups.biblioteca.dao.AutorDAO;
import ec.edu.ups.biblioteca.dao.AutorDAOMemoria;
import ec.edu.ups.biblioteca.dao.BibliotecarioDAO;
import ec.edu.ups.biblioteca.dao.BibliotecarioDAOMemoria;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.dao.LibroDAOMemoria;
import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.PrestamoDAOMemoria;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAOMemoria;
import ec.edu.ups.controllers.AutorController;
import ec.edu.ups.controllers.BibliotecarioController;
import ec.edu.ups.controllers.LibroController;
import ec.edu.ups.controllers.PrestamoController;
import ec.edu.ups.controllers.UsuarioController;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author USUARIO
 */
public class PrincipalView1 extends javax.swing.JFrame {

    private UsuarioDAO usuarioDAO;
    private RegistrarUsuarioView registrarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private ActualizarUsuarioView actualizarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private ListarUsuarioView listarUsuarioView;
    private UsuarioController usuarioController;

    private BibliotecarioDAO bibliotecarioDAO;
    private RegistrarBibliotecarioView registrarBibliotecarioView;
    private BuscarBibliotecarioView buscarBibliotecarioView;
    private ActualizarBibliotecarioView actualizarBibliotecarioView;
    private EliminarBibliotecarioView eliminarBibliotecarioView;
    private ListarBibliotecarioView listarBibliotecarioView;
    private BibliotecarioController bibliotecarioController;

    private AutorDAO autorDAO;
    private RegistrarAutorView registrarAutorView;
    private BuscarAutorView buscarAutorView;
    private ActualizarAutorView actualizarAutorView;
    private EliminarAutorView eliminarAutorView;
    private ListarAutorView listarAutorView;
    private AutorController autorController;

    private LibroDAO libroDAO;
    private RegistrarLibroView registrarLibroView;
    private BuscarLibroView buscarLibroView;
    private ActualizarLibroView actualizarLibroView;
    private EliminarLibroView eliminarLibroView;
    private ListarLibroView listarLibroView;
    private LibroController libroController;

    private PrestamoDAO prestamoDAO;
    private RegistrarPrestamoView registrarPrestamoView;
    private RegistrarDevolucionView registrarDevolucionView;
    private ListarPrestamoView listarPrestamoView;
    private PrestamoController prestamoController;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PrincipalView1.class.getName());

    public void cambiarIdioma(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("ec.edu.ups.biblioteca.i18n.mensajes", locale);
        usuarioMenu.setText(bundle.getString("usuarioMenu"));
        registrarUsuario.setText(bundle.getString("registrarUsuario"));
        buscarUsuario.setText(bundle.getString("buscarUsuario"));
        actualizarUsuario.setText(bundle.getString("actualizarUsuario"));
        eliminarUsuario.setText(bundle.getString("eliminarUsuario"));
        listarUsuario.setText(bundle.getString("listarUsuario"));
        bibliotecarioMenu.setText(bundle.getString("bibliotecarioMenu"));
        registrarBibliotecario.setText(bundle.getString("registrarBibliotecario"));
        buscarBibliotecario.setText(bundle.getString("buscarBibliotecario"));
        actualizarBibliotecario.setText(bundle.getString("actualizarBibliotecario"));
        eliminarBibliotecario.setText(bundle.getString("eliminarBibliotecario"));
        listarBibliotecario.setText(bundle.getString("listarBibliotecario"));
        autorMenu.setText(bundle.getString("autorMenu"));
        registrarAutor.setText(bundle.getString("registrarAutor"));
        buscarAutor.setText(bundle.getString("buscarAutor"));
        actualizarAutor.setText(bundle.getString("actualizarAutor"));
        eliminarAutor.setText(bundle.getString("eliminarAutor"));
        listarAutor.setText(bundle.getString("listarAutor"));
        libroMenu.setText(bundle.getString("libroMenu"));
        registrarLibro.setText(bundle.getString("registrarLibro"));
        buscarLibro.setText(bundle.getString("buscarLibro"));
        actualizarLibro.setText(bundle.getString("actualizarLibro"));
        eliminarLibro.setText(bundle.getString("eliminarLibro"));
        listarLibro.setText(bundle.getString("listarLibro"));
        prestamoMenu.setText(bundle.getString("prestamoMenu"));
        registrarPrestamo.setText(bundle.getString("registrarPrestamo"));
        registrarDevolucion.setText(bundle.getString("registrarDevolucion"));
        listarPrestamo.setText(bundle.getString("listarPrestamo"));
        idiomaMenu.setText(bundle.getString("idiomaMenu"));
        EspaniolMenu.setText(bundle.getString("idiomaEspanolMenuItem"));
        InglesMenu.setText(bundle.getString("idiomaInglesMenuItem"));
    }

    /**
     * Creates new form PrincipalView1
     */
    public PrincipalView1() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        usuarioDAO = new UsuarioDAOMemoria();
        bibliotecarioDAO = new BibliotecarioDAOMemoria();
        autorDAO = new AutorDAOMemoria();
        libroDAO = new LibroDAOMemoria();
        prestamoDAO = new PrestamoDAOMemoria();

        registrarUsuarioView = new RegistrarUsuarioView();
        buscarUsuarioView = new BuscarUsuarioView();
        actualizarUsuarioView = new ActualizarUsuarioView();
        eliminarUsuarioView = new EliminarUsuarioView();
        listarUsuarioView = new ListarUsuarioView();

        desktopPane.add(registrarUsuarioView);
        desktopPane.add(buscarUsuarioView);
        desktopPane.add(actualizarUsuarioView);
        desktopPane.add(eliminarUsuarioView);
        desktopPane.add(listarUsuarioView);

        usuarioController = new UsuarioController(usuarioDAO, registrarUsuarioView, buscarUsuarioView, actualizarUsuarioView, eliminarUsuarioView, listarUsuarioView);

        registrarBibliotecarioView = new RegistrarBibliotecarioView();
        buscarBibliotecarioView = new BuscarBibliotecarioView();
        actualizarBibliotecarioView = new ActualizarBibliotecarioView();
        eliminarBibliotecarioView = new EliminarBibliotecarioView();
        listarBibliotecarioView = new ListarBibliotecarioView();

        desktopPane.add(registrarBibliotecarioView);
        desktopPane.add(buscarBibliotecarioView);
        desktopPane.add(actualizarBibliotecarioView);
        desktopPane.add(eliminarBibliotecarioView);
        desktopPane.add(listarBibliotecarioView);

        bibliotecarioController = new BibliotecarioController(bibliotecarioDAO, registrarBibliotecarioView, buscarBibliotecarioView, actualizarBibliotecarioView, eliminarBibliotecarioView, listarBibliotecarioView);

        registrarAutorView = new RegistrarAutorView();
        buscarAutorView = new BuscarAutorView();
        actualizarAutorView = new ActualizarAutorView();
        eliminarAutorView = new EliminarAutorView();
        listarAutorView = new ListarAutorView();

        desktopPane.add(registrarAutorView);
        desktopPane.add(buscarAutorView);
        desktopPane.add(actualizarAutorView);
        desktopPane.add(eliminarAutorView);
        desktopPane.add(listarAutorView);

        autorController = new AutorController(autorDAO, registrarAutorView, buscarAutorView, actualizarAutorView, eliminarAutorView, listarAutorView);

        registrarLibroView = new RegistrarLibroView();
        buscarLibroView = new BuscarLibroView();
        actualizarLibroView = new ActualizarLibroView();
        eliminarLibroView = new EliminarLibroView();
        listarLibroView = new ListarLibroView();

        desktopPane.add(registrarLibroView);
        desktopPane.add(buscarLibroView);
        desktopPane.add(actualizarLibroView);
        desktopPane.add(eliminarLibroView);
        desktopPane.add(listarLibroView);

        libroController = new LibroController(libroDAO, registrarLibroView, buscarLibroView, actualizarLibroView, eliminarLibroView, listarLibroView);

        registrarPrestamoView = new RegistrarPrestamoView();
        registrarDevolucionView = new RegistrarDevolucionView();
        listarPrestamoView = new ListarPrestamoView();

        desktopPane.add(registrarPrestamoView);
        desktopPane.add(registrarDevolucionView);
        desktopPane.add(listarPrestamoView);

        prestamoController = new PrestamoController(prestamoDAO, usuarioDAO, registrarPrestamoView, registrarDevolucionView, listarPrestamoView);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        lblFondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        usuarioMenu = new javax.swing.JMenu();
        registrarUsuario = new javax.swing.JMenuItem();
        buscarUsuario = new javax.swing.JMenuItem();
        actualizarUsuario = new javax.swing.JMenuItem();
        eliminarUsuario = new javax.swing.JMenuItem();
        listarUsuario = new javax.swing.JMenuItem();
        bibliotecarioMenu = new javax.swing.JMenu();
        registrarBibliotecario = new javax.swing.JMenuItem();
        buscarBibliotecario = new javax.swing.JMenuItem();
        actualizarBibliotecario = new javax.swing.JMenuItem();
        eliminarBibliotecario = new javax.swing.JMenuItem();
        listarBibliotecario = new javax.swing.JMenuItem();
        autorMenu = new javax.swing.JMenu();
        registrarAutor = new javax.swing.JMenuItem();
        buscarAutor = new javax.swing.JMenuItem();
        actualizarAutor = new javax.swing.JMenuItem();
        eliminarAutor = new javax.swing.JMenuItem();
        listarAutor = new javax.swing.JMenuItem();
        libroMenu = new javax.swing.JMenu();
        registrarLibro = new javax.swing.JMenuItem();
        buscarLibro = new javax.swing.JMenuItem();
        actualizarLibro = new javax.swing.JMenuItem();
        eliminarLibro = new javax.swing.JMenuItem();
        listarLibro = new javax.swing.JMenuItem();
        prestamoMenu = new javax.swing.JMenu();
        registrarPrestamo = new javax.swing.JMenuItem();
        registrarDevolucion = new javax.swing.JMenuItem();
        listarPrestamo = new javax.swing.JMenuItem();
        idiomaMenu = new javax.swing.JMenu();
        EspaniolMenu = new javax.swing.JMenuItem();
        InglesMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                desktopPaneComponentResized(evt);
            }
        });

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/biblioteca/imagen/Biblioteca.jpg"))); // NOI18N

        desktopPane.setLayer(lblFondo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 465, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, Short.MAX_VALUE)
        );

        getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

        usuarioMenu.setText("Usuario");

        registrarUsuario.setText("Registrar");
        registrarUsuario.addActionListener(this::registrarUsuarioActionPerformed);
        usuarioMenu.add(registrarUsuario);

        buscarUsuario.setText("Buscar");
        buscarUsuario.addActionListener(this::buscarUsuarioActionPerformed);
        usuarioMenu.add(buscarUsuario);

        actualizarUsuario.setText("Actualizar");
        actualizarUsuario.addActionListener(this::actualizarUsuarioActionPerformed);
        usuarioMenu.add(actualizarUsuario);

        eliminarUsuario.setText("Eliminar");
        eliminarUsuario.addActionListener(this::eliminarUsuarioActionPerformed);
        usuarioMenu.add(eliminarUsuario);

        listarUsuario.setText("Listar");
        listarUsuario.addActionListener(this::listarUsuarioActionPerformed);
        usuarioMenu.add(listarUsuario);

        jMenuBar1.add(usuarioMenu);

        bibliotecarioMenu.setText("Bibliotecario");

        registrarBibliotecario.setText("Registrar");
        registrarBibliotecario.addActionListener(this::registrarBibliotecarioActionPerformed);
        bibliotecarioMenu.add(registrarBibliotecario);

        buscarBibliotecario.setText("Buscar");
        buscarBibliotecario.addActionListener(this::buscarBibliotecarioActionPerformed);
        bibliotecarioMenu.add(buscarBibliotecario);

        actualizarBibliotecario.setText("Actualizar");
        actualizarBibliotecario.addActionListener(this::actualizarBibliotecarioActionPerformed);
        bibliotecarioMenu.add(actualizarBibliotecario);

        eliminarBibliotecario.setText("Eliminar");
        eliminarBibliotecario.addActionListener(this::eliminarBibliotecarioActionPerformed);
        bibliotecarioMenu.add(eliminarBibliotecario);

        listarBibliotecario.setText("Listar");
        listarBibliotecario.addActionListener(this::listarBibliotecarioActionPerformed);
        bibliotecarioMenu.add(listarBibliotecario);

        jMenuBar1.add(bibliotecarioMenu);

        autorMenu.setText("Autor");

        registrarAutor.setText("Registrar");
        registrarAutor.addActionListener(this::registrarAutorActionPerformed);
        autorMenu.add(registrarAutor);

        buscarAutor.setText("Buscar");
        buscarAutor.addActionListener(this::buscarAutorActionPerformed);
        autorMenu.add(buscarAutor);

        actualizarAutor.setText("Actualizar");
        actualizarAutor.addActionListener(this::actualizarAutorActionPerformed);
        autorMenu.add(actualizarAutor);

        eliminarAutor.setText("Eliminar");
        eliminarAutor.addActionListener(this::eliminarAutorActionPerformed);
        autorMenu.add(eliminarAutor);

        listarAutor.setText("Listar");
        listarAutor.addActionListener(this::listarAutorActionPerformed);
        autorMenu.add(listarAutor);

        jMenuBar1.add(autorMenu);

        libroMenu.setText("Libro");

        registrarLibro.setText("Registrar");
        registrarLibro.addActionListener(this::registrarLibroActionPerformed);
        libroMenu.add(registrarLibro);

        buscarLibro.setText("Buscar");
        buscarLibro.addActionListener(this::buscarLibroActionPerformed);
        libroMenu.add(buscarLibro);

        actualizarLibro.setText("Actualizar");
        actualizarLibro.addActionListener(this::actualizarLibroActionPerformed);
        libroMenu.add(actualizarLibro);

        eliminarLibro.setText("Eliminar");
        eliminarLibro.addActionListener(this::eliminarLibroActionPerformed);
        libroMenu.add(eliminarLibro);

        listarLibro.setText("Listar");
        listarLibro.addActionListener(this::listarLibroActionPerformed);
        libroMenu.add(listarLibro);

        jMenuBar1.add(libroMenu);

        prestamoMenu.setText("Prestamo");

        registrarPrestamo.setText("Registrar");
        registrarPrestamo.addActionListener(this::registrarPrestamoActionPerformed);
        prestamoMenu.add(registrarPrestamo);

        registrarDevolucion.setText("Devolver");
        registrarDevolucion.addActionListener(this::registrarDevolucionActionPerformed);
        prestamoMenu.add(registrarDevolucion);

        listarPrestamo.setText("Listar");
        listarPrestamo.addActionListener(this::listarPrestamoActionPerformed);
        prestamoMenu.add(listarPrestamo);

        jMenuBar1.add(prestamoMenu);

        idiomaMenu.setText("Idioma");

        EspaniolMenu.setText("Español");
        EspaniolMenu.addActionListener(this::EspaniolMenuActionPerformed);
        idiomaMenu.add(EspaniolMenu);

        InglesMenu.setText("Ingles");
        InglesMenu.addActionListener(this::InglesMenuActionPerformed);
        idiomaMenu.add(InglesMenu);

        jMenuBar1.add(idiomaMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarUsuarioActionPerformed
        registrarUsuarioView.setVisible(true);
        registrarUsuarioView.moveToFront();
    }//GEN-LAST:event_registrarUsuarioActionPerformed

    private void buscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarUsuarioActionPerformed
        buscarUsuarioView.setVisible(true);
        buscarUsuarioView.moveToFront();
    }//GEN-LAST:event_buscarUsuarioActionPerformed

    private void actualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarUsuarioActionPerformed
        actualizarUsuarioView.setVisible(true);
        actualizarUsuarioView.moveToFront();
    }//GEN-LAST:event_actualizarUsuarioActionPerformed

    private void eliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarUsuarioActionPerformed
        eliminarUsuarioView.setVisible(true);
        eliminarUsuarioView.moveToFront();
    }//GEN-LAST:event_eliminarUsuarioActionPerformed

    private void listarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarUsuarioActionPerformed
        usuarioController.listarUsuarios();
        listarUsuarioView.setVisible(true);
        listarUsuarioView.moveToFront();
    }//GEN-LAST:event_listarUsuarioActionPerformed

    private void registrarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarBibliotecarioActionPerformed
        registrarBibliotecarioView.setVisible(true);
        registrarBibliotecarioView.moveToFront();
    }//GEN-LAST:event_registrarBibliotecarioActionPerformed

    private void buscarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBibliotecarioActionPerformed
        buscarBibliotecarioView.setVisible(true);
        buscarBibliotecarioView.moveToFront();
    }//GEN-LAST:event_buscarBibliotecarioActionPerformed

    private void actualizarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarBibliotecarioActionPerformed
       actualizarBibliotecarioView.setVisible(true);
       actualizarBibliotecarioView.moveToFront();
    }//GEN-LAST:event_actualizarBibliotecarioActionPerformed

    private void eliminarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBibliotecarioActionPerformed
        eliminarBibliotecarioView.setVisible(true);
        eliminarBibliotecarioView.moveToFront();
    }//GEN-LAST:event_eliminarBibliotecarioActionPerformed

    private void listarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarBibliotecarioActionPerformed
        bibliotecarioController.listarBibliotecarios();
        listarBibliotecarioView.setVisible(true);
        listarBibliotecarioView.moveToFront();
        
    }//GEN-LAST:event_listarBibliotecarioActionPerformed

    private void registrarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarAutorActionPerformed
        registrarAutorView.setVisible(true);
        registrarAutorView.moveToFront();
        
    }//GEN-LAST:event_registrarAutorActionPerformed

    private void buscarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarAutorActionPerformed
        buscarAutorView.setVisible(true);
        buscarAutorView.moveToFront();
    }//GEN-LAST:event_buscarAutorActionPerformed

    private void actualizarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarAutorActionPerformed
        actualizarAutorView.setVisible(true);
        actualizarAutorView.moveToFront();
        
    }//GEN-LAST:event_actualizarAutorActionPerformed

    private void eliminarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarAutorActionPerformed
        eliminarAutorView.setVisible(true);
        eliminarAutorView.moveToFront();
        
    }//GEN-LAST:event_eliminarAutorActionPerformed

    private void listarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarAutorActionPerformed
        autorController.listarAutores();
        listarAutorView.setVisible(true);
        listarAutorView.moveToFront();
    }//GEN-LAST:event_listarAutorActionPerformed

    private void registrarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarLibroActionPerformed
        registrarLibroView.setVisible(true);
        registrarLibroView.moveToFront();
    }//GEN-LAST:event_registrarLibroActionPerformed

    private void buscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarLibroActionPerformed
        buscarLibroView.setVisible(true);
        buscarLibroView.moveToFront();
        
    }//GEN-LAST:event_buscarLibroActionPerformed

    private void actualizarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarLibroActionPerformed
        actualizarLibroView.setVisible(true);
        actualizarLibroView.moveToFront();
    }//GEN-LAST:event_actualizarLibroActionPerformed

    private void eliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarLibroActionPerformed
        eliminarLibroView.setVisible(true);
        eliminarLibroView.moveToFront();
       
    }//GEN-LAST:event_eliminarLibroActionPerformed

    private void listarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarLibroActionPerformed
        libroController.listarLibros();
        listarLibroView.setVisible(true);
        listarLibroView.moveToFront();
        
    }//GEN-LAST:event_listarLibroActionPerformed

    private void registrarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarPrestamoActionPerformed
        registrarPrestamoView.cargarLibros(libroDAO.listar());
        registrarPrestamoView.cargarBibliotecarios(bibliotecarioDAO.listar());
        registrarPrestamoView.setVisible(true);
        registrarPrestamoView.moveToFront();
        
    }//GEN-LAST:event_registrarPrestamoActionPerformed

    private void registrarDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarDevolucionActionPerformed
        registrarDevolucionView.setVisible(true);
        registrarDevolucionView.moveToFront();
        
    }//GEN-LAST:event_registrarDevolucionActionPerformed

    private void listarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarPrestamoActionPerformed
        prestamoController.listarPrestamos();
        listarPrestamoView.setVisible(true);
        listarPrestamoView.moveToFront();
        
    }//GEN-LAST:event_listarPrestamoActionPerformed

    private void EspaniolMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EspaniolMenuActionPerformed
        Locale locale = new Locale("es", "EC");
        cambiarIdioma(locale);
        registrarUsuarioView.cambiarIdioma(locale);
        buscarUsuarioView.cambiarIdioma(locale);
        actualizarUsuarioView.cambiarIdioma(locale);
        eliminarUsuarioView.cambiarIdioma(locale);
        listarUsuarioView.cambiarIdioma(locale);
        registrarBibliotecarioView.cambiarIdioma(locale);
        buscarBibliotecarioView.cambiarIdioma(locale);
        actualizarBibliotecarioView.cambiarIdioma(locale);
        eliminarBibliotecarioView.cambiarIdioma(locale);
        listarBibliotecarioView.cambiarIdioma(locale);
        registrarAutorView.cambiarIdioma(locale);
        buscarAutorView.cambiarIdioma(locale);
        actualizarAutorView.cambiarIdioma(locale);
        eliminarAutorView.cambiarIdioma(locale);
        listarAutorView.cambiarIdioma(locale);
        registrarLibroView.cambiarIdioma(locale);
        buscarLibroView.cambiarIdioma(locale);
        actualizarLibroView.cambiarIdioma(locale);
        eliminarLibroView.cambiarIdioma(locale);
        listarLibroView.cambiarIdioma(locale);
        registrarPrestamoView.cambiarIdioma(locale);
        registrarDevolucionView.cambiarIdioma(locale);
        listarPrestamoView.cambiarIdioma(locale);
    }//GEN-LAST:event_EspaniolMenuActionPerformed

    private void InglesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InglesMenuActionPerformed
        Locale locale = new Locale("en", "US");
        cambiarIdioma(locale);
        registrarUsuarioView.cambiarIdioma(locale);
        buscarUsuarioView.cambiarIdioma(locale);
        actualizarUsuarioView.cambiarIdioma(locale);
        eliminarUsuarioView.cambiarIdioma(locale);
        listarUsuarioView.cambiarIdioma(locale);
        registrarBibliotecarioView.cambiarIdioma(locale);
        buscarBibliotecarioView.cambiarIdioma(locale);
        actualizarBibliotecarioView.cambiarIdioma(locale);
        eliminarBibliotecarioView.cambiarIdioma(locale);
        listarBibliotecarioView.cambiarIdioma(locale);
        registrarAutorView.cambiarIdioma(locale);
        buscarAutorView.cambiarIdioma(locale);
        actualizarAutorView.cambiarIdioma(locale);
        eliminarAutorView.cambiarIdioma(locale);
        listarAutorView.cambiarIdioma(locale);
        registrarLibroView.cambiarIdioma(locale);
        buscarLibroView.cambiarIdioma(locale);
        actualizarLibroView.cambiarIdioma(locale);
        eliminarLibroView.cambiarIdioma(locale);
        listarLibroView.cambiarIdioma(locale);
        registrarPrestamoView.cambiarIdioma(locale);
        registrarDevolucionView.cambiarIdioma(locale);
        listarPrestamoView.cambiarIdioma(locale);
    }//GEN-LAST:event_InglesMenuActionPerformed

    private void desktopPaneComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_desktopPaneComponentResized
        lblFondo.setBounds(0, 0, desktopPane.getWidth(), desktopPane.getHeight());
        java.awt.Image imagenOriginal = new javax.swing.ImageIcon(getClass().getResource("/ec/edu/ups/biblioteca/imagen/Biblioteca.jpg")).getImage();
        java.awt.Image imagenEscalada = imagenOriginal.getScaledInstance(desktopPane.getWidth(), desktopPane.getHeight(), java.awt.Image.SCALE_SMOOTH);
        lblFondo.setIcon(new javax.swing.ImageIcon(imagenEscalada));
    }//GEN-LAST:event_desktopPaneComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PrincipalView1().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem EspaniolMenu;
    private javax.swing.JMenuItem InglesMenu;
    private javax.swing.JMenuItem actualizarAutor;
    private javax.swing.JMenuItem actualizarBibliotecario;
    private javax.swing.JMenuItem actualizarLibro;
    private javax.swing.JMenuItem actualizarUsuario;
    private javax.swing.JMenu autorMenu;
    private javax.swing.JMenu bibliotecarioMenu;
    private javax.swing.JMenuItem buscarAutor;
    private javax.swing.JMenuItem buscarBibliotecario;
    private javax.swing.JMenuItem buscarLibro;
    private javax.swing.JMenuItem buscarUsuario;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem eliminarAutor;
    private javax.swing.JMenuItem eliminarBibliotecario;
    private javax.swing.JMenuItem eliminarLibro;
    private javax.swing.JMenuItem eliminarUsuario;
    private javax.swing.JMenu idiomaMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JMenu libroMenu;
    private javax.swing.JMenuItem listarAutor;
    private javax.swing.JMenuItem listarBibliotecario;
    private javax.swing.JMenuItem listarLibro;
    private javax.swing.JMenuItem listarPrestamo;
    private javax.swing.JMenuItem listarUsuario;
    private javax.swing.JMenu prestamoMenu;
    private javax.swing.JMenuItem registrarAutor;
    private javax.swing.JMenuItem registrarBibliotecario;
    private javax.swing.JMenuItem registrarDevolucion;
    private javax.swing.JMenuItem registrarLibro;
    private javax.swing.JMenuItem registrarPrestamo;
    private javax.swing.JMenuItem registrarUsuario;
    private javax.swing.JMenu usuarioMenu;
    // End of variables declaration//GEN-END:variables
}
