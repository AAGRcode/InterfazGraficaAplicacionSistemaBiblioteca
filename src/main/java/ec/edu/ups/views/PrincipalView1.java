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

    /**
     * Creates new form PrincipalView1
     */
    public PrincipalView1() {
        initComponents();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        UsuarioMenu = new javax.swing.JMenu();
        RegistrarUsuario = new javax.swing.JMenuItem();
        BuscarUsuario = new javax.swing.JMenuItem();
        ActualizarUsuario = new javax.swing.JMenuItem();
        EliminarUsuario = new javax.swing.JMenuItem();
        ListarUsuario = new javax.swing.JMenuItem();
        BibliotecarioMenu = new javax.swing.JMenu();
        RegistrarBibliotecario = new javax.swing.JMenuItem();
        BuscarBibliotecario = new javax.swing.JMenuItem();
        ActualizarBibliotecario = new javax.swing.JMenuItem();
        EliminarBibliotecario = new javax.swing.JMenuItem();
        ListarBibliotecario = new javax.swing.JMenuItem();
        AutorMenu = new javax.swing.JMenu();
        RegistrarAutor = new javax.swing.JMenuItem();
        BuscarAutor = new javax.swing.JMenuItem();
        ActualizarAutor = new javax.swing.JMenuItem();
        EliminarAutor = new javax.swing.JMenuItem();
        ListarAutor = new javax.swing.JMenuItem();
        LibroMenu = new javax.swing.JMenu();
        RegistrarLibro = new javax.swing.JMenuItem();
        BuscarLibro = new javax.swing.JMenuItem();
        ActualizarLibro = new javax.swing.JMenuItem();
        EliminarLibro = new javax.swing.JMenuItem();
        ListarLibro = new javax.swing.JMenuItem();
        PrestamoMenu = new javax.swing.JMenu();
        RegistrarPrestamo = new javax.swing.JMenuItem();
        RegistrarDevolucion = new javax.swing.JMenuItem();
        ListarPrestamo = new javax.swing.JMenuItem();
        IdiomaMenu = new javax.swing.JMenu();
        EspaniolMenu = new javax.swing.JMenuItem();
        InglesMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        UsuarioMenu.setText("Usuario");

        RegistrarUsuario.setText("Registrar");
        RegistrarUsuario.addActionListener(this::RegistrarUsuarioActionPerformed);
        UsuarioMenu.add(RegistrarUsuario);

        BuscarUsuario.setText("Buscar");
        BuscarUsuario.addActionListener(this::BuscarUsuarioActionPerformed);
        UsuarioMenu.add(BuscarUsuario);

        ActualizarUsuario.setText("Actualizar");
        ActualizarUsuario.addActionListener(this::ActualizarUsuarioActionPerformed);
        UsuarioMenu.add(ActualizarUsuario);

        EliminarUsuario.setText("Eliminar");
        EliminarUsuario.addActionListener(this::EliminarUsuarioActionPerformed);
        UsuarioMenu.add(EliminarUsuario);

        ListarUsuario.setText("Listar");
        ListarUsuario.addActionListener(this::ListarUsuarioActionPerformed);
        UsuarioMenu.add(ListarUsuario);

        jMenuBar1.add(UsuarioMenu);

        BibliotecarioMenu.setText("Bibliotecario");

        RegistrarBibliotecario.setText("Registrar");
        RegistrarBibliotecario.addActionListener(this::RegistrarBibliotecarioActionPerformed);
        BibliotecarioMenu.add(RegistrarBibliotecario);

        BuscarBibliotecario.setText("Buscar");
        BuscarBibliotecario.addActionListener(this::BuscarBibliotecarioActionPerformed);
        BibliotecarioMenu.add(BuscarBibliotecario);

        ActualizarBibliotecario.setText("Actualizar");
        ActualizarBibliotecario.addActionListener(this::ActualizarBibliotecarioActionPerformed);
        BibliotecarioMenu.add(ActualizarBibliotecario);

        EliminarBibliotecario.setText("Eliminar");
        EliminarBibliotecario.addActionListener(this::EliminarBibliotecarioActionPerformed);
        BibliotecarioMenu.add(EliminarBibliotecario);

        ListarBibliotecario.setText("Listar");
        ListarBibliotecario.addActionListener(this::ListarBibliotecarioActionPerformed);
        BibliotecarioMenu.add(ListarBibliotecario);

        jMenuBar1.add(BibliotecarioMenu);

        AutorMenu.setText("Autor");

        RegistrarAutor.setText("Registrar");
        RegistrarAutor.addActionListener(this::RegistrarAutorActionPerformed);
        AutorMenu.add(RegistrarAutor);

        BuscarAutor.setText("Buscar");
        BuscarAutor.addActionListener(this::BuscarAutorActionPerformed);
        AutorMenu.add(BuscarAutor);

        ActualizarAutor.setText("Actualizar");
        ActualizarAutor.addActionListener(this::ActualizarAutorActionPerformed);
        AutorMenu.add(ActualizarAutor);

        EliminarAutor.setText("Eliminar");
        EliminarAutor.addActionListener(this::EliminarAutorActionPerformed);
        AutorMenu.add(EliminarAutor);

        ListarAutor.setText("Listar");
        ListarAutor.addActionListener(this::ListarAutorActionPerformed);
        AutorMenu.add(ListarAutor);

        jMenuBar1.add(AutorMenu);

        LibroMenu.setText("Libro");

        RegistrarLibro.setText("Registrar");
        RegistrarLibro.addActionListener(this::RegistrarLibroActionPerformed);
        LibroMenu.add(RegistrarLibro);

        BuscarLibro.setText("Buscar");
        BuscarLibro.addActionListener(this::BuscarLibroActionPerformed);
        LibroMenu.add(BuscarLibro);

        ActualizarLibro.setText("Actualizar");
        ActualizarLibro.addActionListener(this::ActualizarLibroActionPerformed);
        LibroMenu.add(ActualizarLibro);

        EliminarLibro.setText("Eliminar");
        EliminarLibro.addActionListener(this::EliminarLibroActionPerformed);
        LibroMenu.add(EliminarLibro);

        ListarLibro.setText("Listar");
        ListarLibro.addActionListener(this::ListarLibroActionPerformed);
        LibroMenu.add(ListarLibro);

        jMenuBar1.add(LibroMenu);

        PrestamoMenu.setText("Prestamo");

        RegistrarPrestamo.setText("Registrar");
        RegistrarPrestamo.addActionListener(this::RegistrarPrestamoActionPerformed);
        PrestamoMenu.add(RegistrarPrestamo);

        RegistrarDevolucion.setText("Devolver");
        RegistrarDevolucion.addActionListener(this::RegistrarDevolucionActionPerformed);
        PrestamoMenu.add(RegistrarDevolucion);

        ListarPrestamo.setText("Listar");
        ListarPrestamo.addActionListener(this::ListarPrestamoActionPerformed);
        PrestamoMenu.add(ListarPrestamo);

        jMenuBar1.add(PrestamoMenu);

        IdiomaMenu.setText("Idioma");

        EspaniolMenu.setText("Español");
        EspaniolMenu.addActionListener(this::EspaniolMenuActionPerformed);
        IdiomaMenu.add(EspaniolMenu);

        InglesMenu.setText("Ingles");
        InglesMenu.addActionListener(this::InglesMenuActionPerformed);
        IdiomaMenu.add(InglesMenu);

        jMenuBar1.add(IdiomaMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        if (!registrarUsuarioView.isVisible()) {
            desktopPane.remove(registrarUsuarioView);
            registrarUsuarioView.setVisible(true);
            desktopPane.add(registrarUsuarioView);
        }
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

    private void BuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarUsuarioActionPerformed
        if (!buscarUsuarioView.isVisible()) {
            desktopPane.remove(buscarUsuarioView);
            buscarUsuarioView.setVisible(true);
            desktopPane.add(buscarUsuarioView);
        }
    }//GEN-LAST:event_BuscarUsuarioActionPerformed

    private void ActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarUsuarioActionPerformed
        if (!actualizarUsuarioView.isVisible()) {
            desktopPane.remove(actualizarUsuarioView);
            actualizarUsuarioView.setVisible(true);
            desktopPane.add(actualizarUsuarioView);
        }
    }//GEN-LAST:event_ActualizarUsuarioActionPerformed

    private void EliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarUsuarioActionPerformed
        if (!eliminarUsuarioView.isVisible()) {
            desktopPane.remove(eliminarUsuarioView);
            eliminarUsuarioView.setVisible(true);
            desktopPane.add(eliminarUsuarioView);
        }
    }//GEN-LAST:event_EliminarUsuarioActionPerformed

    private void ListarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarUsuarioActionPerformed
        usuarioController.listarUsuarios();
        if (!listarUsuarioView.isVisible()) {
            desktopPane.remove(listarUsuarioView);
            listarUsuarioView.setVisible(true);
            desktopPane.add(listarUsuarioView);
        }
    }//GEN-LAST:event_ListarUsuarioActionPerformed

    private void RegistrarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarBibliotecarioActionPerformed
        if (!registrarBibliotecarioView.isVisible()) {
            desktopPane.remove(registrarBibliotecarioView);
            registrarBibliotecarioView.setVisible(true);
            desktopPane.add(registrarBibliotecarioView);
        }
    }//GEN-LAST:event_RegistrarBibliotecarioActionPerformed

    private void BuscarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBibliotecarioActionPerformed
        if (!buscarBibliotecarioView.isVisible()) {
            desktopPane.remove(buscarBibliotecarioView);
            buscarBibliotecarioView.setVisible(true);
            desktopPane.add(buscarBibliotecarioView);
        }
    }//GEN-LAST:event_BuscarBibliotecarioActionPerformed

    private void ActualizarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarBibliotecarioActionPerformed
        if (!actualizarBibliotecarioView.isVisible()) {
            desktopPane.remove(actualizarBibliotecarioView);
            actualizarBibliotecarioView.setVisible(true);
            desktopPane.add(actualizarBibliotecarioView);
        }
    }//GEN-LAST:event_ActualizarBibliotecarioActionPerformed

    private void EliminarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBibliotecarioActionPerformed
        if (!eliminarBibliotecarioView.isVisible()) {
            desktopPane.remove(eliminarBibliotecarioView);
            eliminarBibliotecarioView.setVisible(true);
            desktopPane.add(eliminarBibliotecarioView);
        }
    }//GEN-LAST:event_EliminarBibliotecarioActionPerformed

    private void ListarBibliotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarBibliotecarioActionPerformed
        bibliotecarioController.listarBibliotecarios();
        if (!listarBibliotecarioView.isVisible()) {
            desktopPane.remove(listarBibliotecarioView);
            listarBibliotecarioView.setVisible(true);
            desktopPane.add(listarBibliotecarioView);
        }
    }//GEN-LAST:event_ListarBibliotecarioActionPerformed

    private void RegistrarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarAutorActionPerformed
        if (!registrarAutorView.isVisible()) {
            desktopPane.remove(registrarAutorView);
            registrarAutorView.setVisible(true);
            desktopPane.add(registrarAutorView);
        }
    }//GEN-LAST:event_RegistrarAutorActionPerformed

    private void BuscarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarAutorActionPerformed
        if (!buscarAutorView.isVisible()) {
            desktopPane.remove(buscarAutorView);
            buscarAutorView.setVisible(true);
            desktopPane.add(buscarAutorView);
        }
    }//GEN-LAST:event_BuscarAutorActionPerformed

    private void ActualizarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarAutorActionPerformed
        if (!actualizarAutorView.isVisible()) {
            desktopPane.remove(actualizarAutorView);
            actualizarAutorView.setVisible(true);
            desktopPane.add(actualizarAutorView);
        }
    }//GEN-LAST:event_ActualizarAutorActionPerformed

    private void EliminarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarAutorActionPerformed
        if (!eliminarAutorView.isVisible()) {
            desktopPane.remove(eliminarAutorView);
            eliminarAutorView.setVisible(true);
            desktopPane.add(eliminarAutorView);
        }
    }//GEN-LAST:event_EliminarAutorActionPerformed

    private void ListarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarAutorActionPerformed
        autorController.listarAutores();
        if (!listarAutorView.isVisible()) {
            desktopPane.remove(listarAutorView);
            listarAutorView.setVisible(true);
            desktopPane.add(listarAutorView);
        }
    }//GEN-LAST:event_ListarAutorActionPerformed

    private void RegistrarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarLibroActionPerformed
        if (!registrarLibroView.isVisible()) {
            desktopPane.remove(registrarLibroView);
            registrarLibroView.setVisible(true);
            desktopPane.add(registrarLibroView);
        }
    }//GEN-LAST:event_RegistrarLibroActionPerformed

    private void BuscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarLibroActionPerformed
        if (!buscarLibroView.isVisible()) {
            desktopPane.remove(buscarLibroView);
            buscarLibroView.setVisible(true);
            desktopPane.add(buscarLibroView);
        }
    }//GEN-LAST:event_BuscarLibroActionPerformed

    private void ActualizarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarLibroActionPerformed
        if (!actualizarLibroView.isVisible()) {
            desktopPane.remove(actualizarLibroView);
            actualizarLibroView.setVisible(true);
            desktopPane.add(actualizarLibroView);
        }
    }//GEN-LAST:event_ActualizarLibroActionPerformed

    private void EliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarLibroActionPerformed
        if (!eliminarLibroView.isVisible()) {
            desktopPane.remove(eliminarLibroView);
            eliminarLibroView.setVisible(true);
            desktopPane.add(eliminarLibroView);
        }
    }//GEN-LAST:event_EliminarLibroActionPerformed

    private void ListarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarLibroActionPerformed
        libroController.listarLibros();
        if (!listarLibroView.isVisible()) {
            desktopPane.remove(listarLibroView);
            listarLibroView.setVisible(true);
            desktopPane.add(listarLibroView);
        }
    }//GEN-LAST:event_ListarLibroActionPerformed

    private void RegistrarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarPrestamoActionPerformed
        registrarPrestamoView.cargarLibros(libroDAO.listar());
        registrarPrestamoView.cargarBibliotecarios(bibliotecarioDAO.listar());
        if (!registrarPrestamoView.isVisible()) {
            desktopPane.remove(registrarPrestamoView);
            registrarPrestamoView.setVisible(true);
            desktopPane.add(registrarPrestamoView);
        }
    }//GEN-LAST:event_RegistrarPrestamoActionPerformed

    private void RegistrarDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarDevolucionActionPerformed
        if (!registrarDevolucionView.isVisible()) {
            desktopPane.remove(registrarDevolucionView);
            registrarDevolucionView.setVisible(true);
            desktopPane.add(registrarDevolucionView);
        }
    }//GEN-LAST:event_RegistrarDevolucionActionPerformed

    private void ListarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarPrestamoActionPerformed
        prestamoController.listarPrestamos();
        if (!listarPrestamoView.isVisible()) {
            desktopPane.remove(listarPrestamoView);
            listarPrestamoView.setVisible(true);
            desktopPane.add(listarPrestamoView);
        }
    }//GEN-LAST:event_ListarPrestamoActionPerformed

    private void EspaniolMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EspaniolMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EspaniolMenuActionPerformed

    private void InglesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InglesMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InglesMenuActionPerformed

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
    private javax.swing.JMenuItem ActualizarAutor;
    private javax.swing.JMenuItem ActualizarBibliotecario;
    private javax.swing.JMenuItem ActualizarLibro;
    private javax.swing.JMenuItem ActualizarUsuario;
    private javax.swing.JMenu AutorMenu;
    private javax.swing.JMenu BibliotecarioMenu;
    private javax.swing.JMenuItem BuscarAutor;
    private javax.swing.JMenuItem BuscarBibliotecario;
    private javax.swing.JMenuItem BuscarLibro;
    private javax.swing.JMenuItem BuscarUsuario;
    private javax.swing.JMenuItem EliminarAutor;
    private javax.swing.JMenuItem EliminarBibliotecario;
    private javax.swing.JMenuItem EliminarLibro;
    private javax.swing.JMenuItem EliminarUsuario;
    private javax.swing.JMenuItem EspaniolMenu;
    private javax.swing.JMenu IdiomaMenu;
    private javax.swing.JMenuItem InglesMenu;
    private javax.swing.JMenu LibroMenu;
    private javax.swing.JMenuItem ListarAutor;
    private javax.swing.JMenuItem ListarBibliotecario;
    private javax.swing.JMenuItem ListarLibro;
    private javax.swing.JMenuItem ListarPrestamo;
    private javax.swing.JMenuItem ListarUsuario;
    private javax.swing.JMenu PrestamoMenu;
    private javax.swing.JMenuItem RegistrarAutor;
    private javax.swing.JMenuItem RegistrarBibliotecario;
    private javax.swing.JMenuItem RegistrarDevolucion;
    private javax.swing.JMenuItem RegistrarLibro;
    private javax.swing.JMenuItem RegistrarPrestamo;
    private javax.swing.JMenuItem RegistrarUsuario;
    private javax.swing.JMenu UsuarioMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
