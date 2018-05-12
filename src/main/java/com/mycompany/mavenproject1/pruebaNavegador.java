package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
//INICIO CLASE

public class pruebaNavegador extends javax.swing.JInternalFrame {

    long rapido;
    long medio;
    long lento;
    long mlento;
    WebDriver driver;
    Connection connection = null;
    Statement statement = null;
    String path_drive = "";
    cControl c = new cControl();

    //VARIABLE QUE DETECTA SI SE CAMBIO EL TEXTO Y HACE UN UPDATE
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:tmt.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //CARGADOR
    public pruebaNavegador() {
        initComponents();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //CARGAMOS LOS DATOS DE CONFIGURACION COMO LA VELOCIDAD ENTRE ECCIONES Y LA URL DEL PATH DRIVER
            String query = "SELECT "
                    + "path_drive,"
                    + "rapido,"
                    + "medio,"
                    + "lento,"
                    + "mlento "
                    + "FROM configuracion";

            ResultSet rs = statement.executeQuery(query);
            path_drive = rs.getString("path_drive");
            rapido = Long.parseLong(rs.getString("rapido"));
            medio = Long.parseLong(rs.getString("medio"));
            lento = Long.parseLong(rs.getString("lento"));
            mlento = Long.parseLong(rs.getString("mlento"));

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

        jLabel1 = new javax.swing.JLabel();
        btnEscribeTexto = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        textoEscribir = new javax.swing.JTextField();

        setTitle("Kingdomlikes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        btnEscribeTexto.setText("Escribir texto");
        btnEscribeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscribeTextoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEscribeTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, -1));

        jLabel14.setText("Texto");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        textoEscribir.setText("Texto a escribir en el navegador https://www.youtube.com/watch?v=5HWN052-VX4&t=1s");
        textoEscribir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoEscribirKeyPressed(evt);
            }
        });
        getContentPane().add(textoEscribir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 550, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEscribeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscribeTextoActionPerformed
        c.inicializarWebdriver(path_drive);
        c.buscaGoogle(textoEscribir.getText());
    }//GEN-LAST:event_btnEscribeTextoActionPerformed

    private void textoEscribirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoEscribirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoEscribirKeyPressed
    //AGREGAMOS EN LA BD LA PUBLICACION
    public void guardarUserPass(String SuserKL,String SpasswordKL,String redSocal) {
        
        String sql = "UPDATE accesos SET "
                + "user = '" + SuserKL + "',  "
                + "password = '" + SpasswordKL + "',  "
                + "redSocal = '" + redSocal + "'  "
                + "WHERE redSocal = '" + redSocal +"';";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEscribeTexto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JTextField textoEscribir;
    // End of variables declaration//GEN-END:variables
}
