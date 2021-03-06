package com.mycompany.mavenproject1;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

public class fInterfas extends javax.swing.JFrame {

    Connection connection = null;
    Statement statement = null;
    String path_drive = "";
    Object[] options = {"Continuar", "Detener"};

    public fInterfas() {
        initComponents();
        /*
        kingdomlikes t=new kingdomlikes();
        t.setResizable(rootPaneCheckingEnabled);
        t.setMaximizable(rootPaneCheckingEnabled);
        t.setIconifiable(rootPaneCheckingEnabled);
        t.setVisible(rootPaneCheckingEnabled);
        t.setClosable(rootPaneCheckingEnabled);
        t.toFront();
        contenedor.add(t);
         */
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(fInterfas.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //CREA UNA BD (SI NO EXISTE) EN DONDE SE ENCUENTRE LA APLICACION
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            ResultSet rs = statement.executeQuery("SELECT path_drive,rapido,medio,lento,mlento FROM configuracion");
            String error = "";
            if (rs.getString("path_drive").isEmpty() && rs.getString("path_drive") != null) {
                error = "Falta path_drive \n";
            }
            if (rs.getString("rapido").isEmpty() && rs.getString("rapido") != null) {
                error = "Falta tiempo rapido \n";
            }
            if (rs.getString("medio").isEmpty() && rs.getString("medio") != null) {
                error = "Falta tiempo medio \n";
            }
            if (rs.getString("lento").isEmpty() && rs.getString("lento") != null) {
                error = "Falta tiempo lento \n";
            }
            if (rs.getString("mlento").isEmpty() && rs.getString("mlento") != null) {
                error = "Falta tiempo  \n";
            }
            if (!error.isEmpty()) {
                showMessageDialog(null, error);
                fConfiguracion v = new fConfiguracion();
                v.setResizable(rootPaneCheckingEnabled);
                v.setMaximizable(rootPaneCheckingEnabled);
                v.setIconifiable(rootPaneCheckingEnabled);
                v.setVisible(rootPaneCheckingEnabled);
                v.setClosable(rootPaneCheckingEnabled);
                contenedor.add(v);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        contenedor = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuITwitter = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1169, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 829, Short.MAX_VALUE)
        );

        getContentPane().add(contenedor, java.awt.BorderLayout.PAGE_START);

        jMenu1.setText("Videos");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Youtube");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("LinKedIn");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuITwitter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuITwitter.setText("Twitter");
        jMenuITwitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuITwitterActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuITwitter);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Configuracion");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("General");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Publicaciones");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Categorias");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Grupos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Kingdomlikes");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Hitleap");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Escribe Texto");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Accesos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        fYoutube v = new fYoutube();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);
        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        fConfiguracion v = new fConfiguracion();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);
        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        publicaciones v = new publicaciones();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);
        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        categorias v = new categorias();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);
        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        grupos v = new grupos();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);
        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        king();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public void king() {
        kingdomlikes v = new kingdomlikes();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);
        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();
    }

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        hitleap v = new hitleap();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);
        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        pruebaNavegador v = new pruebaNavegador();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);

        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        accesos v = new accesos();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);

        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        fLinkedin v = new fLinkedin();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);

        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuITwitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuITwitterActionPerformed
        fTwiter v = new fTwiter();
        v.setResizable(rootPaneCheckingEnabled);
        v.setMaximizable(rootPaneCheckingEnabled);
        v.setIconifiable(rootPaneCheckingEnabled);
        v.setVisible(rootPaneCheckingEnabled);
        v.setClosable(rootPaneCheckingEnabled);

        contenedor.add(v);
        v.toFront();
        v.requestFocus();
        v.repaint();
    }//GEN-LAST:event_jMenuITwitterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static String accion = "";

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fInterfas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fInterfas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fInterfas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fInterfas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                fInterfas v = new fInterfas();
                //new fInterfas().setVisible(true);
                v.setVisible(true);
                v.setTitle(accion);
            }
        });
        fInterfas inter = new fInterfas();
        
        //Buccle que recibe todos los argumentos
        for (int i = 0; i < args.length; i++) {
            accion = args[i];
            //Si el argumento es king ejecuta kingdomlikes
            if (accion.equals("king")) {
                kingdomlikes v = new kingdomlikes();
                v.setResizable(true);
                v.setMaximizable(true);
                v.setIconifiable(true);
                v.setVisible(true);
                v.setClosable(true);
                v.toFront();
                v.requestFocus();
                v.repaint();
                v.intercambiarCuentas();
            }
            //Si el argumento es face ejecuta facebook
            if (accion.equals("face")) {
                
                fYoutube v = new fYoutube();
                v.setResizable(true);
                v.setMaximizable(true);
                v.setIconifiable(true);
                v.setVisible(true);
                v.setClosable(true);
                //contenedor.add(v);
                v.toFront();
                v.requestFocus();
                v.repaint();
                v.reiniciarCronometro();
                v.cronometroParaEjecutar();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane contenedor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuITwitter;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
