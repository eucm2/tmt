package com.mycompany.mavenproject1;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class fConfiguracion extends javax.swing.JInternalFrame {

    Connection connection = null;
    Statement statement = null;
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pathDrive = new javax.swing.JTextField();
        seleccionarImagen = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        rapido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        medio = new javax.swing.JTextField();
        mlento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setTitle("Configuracion General");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        getContentPane().add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));

        jLabel1.setText("Velocidad en milisegundos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        getContentPane().add(pathDrive, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 510, -1));

        seleccionarImagen.setText("Seleccionar chromedriver");
        seleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarImagenActionPerformed(evt);
            }
        });
        getContentPane().add(seleccionarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jLabel3.setText("Rapido");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        getContentPane().add(rapido, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 140, -1));

        jLabel5.setText("Lento");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));
        getContentPane().add(lento, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 140, -1));

        jLabel4.setText("Medio");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));
        getContentPane().add(medio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 140, -1));
        getContentPane().add(mlento, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 140, -1));

        jLabel6.setText("Muy lento");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        jLabel7.setText("Path drive chromedrive");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public fConfiguracion() {
        initComponents();
        
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
            String query = "SELECT "
                    + "path_drive,"
                    + "path_excel,"
                    + "rapido,"
                    + "medio,"
                    + "lento,"
                    + "mlento "
                    + "FROM configuracion";
            ResultSet rs = statement.executeQuery(query);
            pathDrive.setText(rs.getString("path_drive"));
            rapido.setText(rs.getString("rapido"));
            medio.setText(rs.getString("medio"));
            lento.setText(rs.getString("lento"));
            mlento.setText(rs.getString("mlento"));

            query = "SELECT id,nombre,tipo1,valor1,tipo2,valor2,tipo3,valor3,tipo4,valor4,tipo5,valor5 FROM capabilities";
            rs = statement.executeQuery(query);
            DefaultTableModel dfm=new DefaultTableModel();
            dfm.setColumnIdentifiers(new Object[]{"id","nombre","tipo1","valor1","tipo2","valor2","tipo3","valor3","tipo4","valor4","tipo5","valor5"});
            while (rs.next()) {
                dfm.addRow(new Object[]{rs.getString("id"),rs.getString("nombre"),rs.getString("tipo1"),rs.getString("valor1"),rs.getString("tipo2"),rs.getString("valor2"),rs.getString("tipo3"),rs.getString("valor3"),rs.getString("tipo4"),rs.getString("valor4"),rs.getString("tipo5"),rs.getString("valor5")});   
            }
            //DEPENDIENDO DEL SISTEMA OPERATIVO QUE SE ESTE USANDO TOMAMOS EL CHROMEDRIVER
            if (rs.getString("path_drive").isEmpty() == true) {
                if (System.getProperty("os.name").startsWith("Windows")) {
                    pathDrive.setText(System.getProperty("user.dir") + "\\chromedriver.exe");
                } else if (System.getProperty("os.name").startsWith("Linux")) {
                    pathDrive.setText(System.getProperty("user.dir") + "/chromedriver");
                } else {
                    pathDrive.setText(System.getProperty("user.dir") + "/chromedriver_mac");
                }
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


    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            String Slocal_remoto;
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            String Sbrowser_max;
            //GUARDAMOS LOS DATOS DE LA COMPRA
            String query = "UPDATE configuracion SET "
                    + "path_drive='" + pathDrive.getText() + "', "
                    + "rapido='" + rapido.getText() + "',"
                    + "medio='" + medio.getText() + "',"
                    + "lento='" + lento.getText() + "',"
                    + "mlento='" + mlento.getText() + "' "
                    + "WHERE (id=1)";
            statement.executeQuery(query);
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }


    }//GEN-LAST:event_guardarActionPerformed

    private void seleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarImagenActionPerformed
        //AL HACER CLICK EN EL BOTON DE SELECCIONAR IMAGEN TOMAMOS ESA IMAGEN A SUBIR
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            pathDrive.setText(selectedFile.getPath());
        }
    }//GEN-LAST:event_seleccionarImagenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField lento;
    private javax.swing.JTextField medio;
    private javax.swing.JTextField mlento;
    private javax.swing.JTextField pathDrive;
    private javax.swing.JTextField rapido;
    private javax.swing.JButton seleccionarImagen;
    // End of variables declaration//GEN-END:variables
}
