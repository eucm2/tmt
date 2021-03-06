package com.mycompany.mavenproject1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
//INICIO CLASE

public class kingdomlikes extends javax.swing.JInternalFrame {

    long rapido;
    long medio;
    long lento;
    long mlento;
    WebDriver driver;
    Connection connection = null;
    Statement statement = null;
    String path_drive = "";
    cControl c = new cControl();
    DefaultTableModel modelo=new DefaultTableModel();

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
    ResultSet rsAccesos;
    String userID = "";
    protected ArrayList<Integer> accesosUsados = new ArrayList();
    protected ArrayList<Integer> accesosEjecutado = new ArrayList();
    protected ArrayList<String> accesosUser = new ArrayList();
    protected ArrayList<String> accesosPassword = new ArrayList();

    //CARGADOR
    public kingdomlikes() {
        initComponents();
        //CARGAMOS EL MODELO DE LA TABLA PUBLICACIONES
        modelo.addColumn("id");
        modelo.addColumn("user");
        modelo.addColumn("password");
        modelo.addColumn("redSocal");
        modelo.addColumn("activo");
        //CARGAMOS LA TABLA DE PUBLICACIONES
        carga_tabla();

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

            //CARGAMOS LOS DATOS DE CONFIGURACION COMO LA VELOCIDAD ENTRE ECCIONES Y LA URL DEL PATH DRIVER
            String queryAccesos = "SELECT "
                    + "id, "
                    + "user, "
                    + "password, "
                    + "redSocal "
                    + "FROM accesos "
                    + "where redSocal='KL' and  activo='1' "
                    + "ORDER BY RANDOM() ; ";

            rsAccesos = statement.executeQuery(queryAccesos);
            //CICLO QUE LLENA TODO EL MODELO
            while (rsAccesos.next()) {
                String redSocal = rsAccesos.getString("redSocal");
                if (redSocal.equals("KL")) {
                    userKL.setText(rsAccesos.getString("user"));
                    accesosUser.add(rsAccesos.getString("user"));
                    userID = rsAccesos.getString("id");
                    passwordKL.setText(rsAccesos.getString("password"));
                    accesosPassword.add(rsAccesos.getString("password"));
                    accesosEjecutado.add(0);
                    if (rsAccesos.getString("user").length() > 0 && rsAccesos.getString("password").length() > 0) {
                        guardarFB.setSelected(true);
                    }
                    //break;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGanarPuntos = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        userKL = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        guardarFB = new javax.swing.JCheckBox();
        KLnumPaginas = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnKLCrearPaginas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        KLPaises = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        faltanSegundos = new javax.swing.JLabel();
        checIntercambiarCuentas = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        txtCadaVideos = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_accesos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        passwordKL = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        listYoutubeWeb = new javax.swing.JList<>();
        jLabel19 = new javax.swing.JLabel();

        setTitle("Kingdomlikes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        btnGanarPuntos.setText("Ganar puntos");
        btnGanarPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGanarPuntosActionPerformed(evt);
            }
        });
        getContentPane().add(btnGanarPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        jLabel14.setText("Youtube/Web");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, -1, -1));

        userKL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userKLKeyPressed(evt);
            }
        });
        getContentPane().add(userKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, -1));

        jLabel12.setText("Password KL");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        guardarFB.setText("Guardar");
        guardarFB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarFBActionPerformed(evt);
            }
        });
        getContentPane().add(guardarFB, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        KLnumPaginas.setText("5");
        KLnumPaginas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KLnumPaginasKeyPressed(evt);
            }
        });
        getContentPane().add(KLnumPaginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 240, -1));

        jLabel15.setText("User KL");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnKLCrearPaginas.setText("Crear paginas");
        btnKLCrearPaginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLCrearPaginasActionPerformed(evt);
            }
        });
        getContentPane().add(btnKLCrearPaginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, -1, -1));

        KLPaises.setColumns(20);
        KLPaises.setRows(5);
        KLPaises.setText("Mexico\nArgentina\nSpain\nChile\nColombia\nUnited States");
        jScrollPane1.setViewportView(KLPaises);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 130, 110));

        jLabel16.setText("Numero de paginas a crear");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        faltanSegundos.setText("faltan");
        getContentPane().add(faltanSegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, -1, -1));

        checIntercambiarCuentas.setSelected(true);
        checIntercambiarCuentas.setText("Intercambiar cuentas");
        checIntercambiarCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checIntercambiarCuentasActionPerformed(evt);
            }
        });
        getContentPane().add(checIntercambiarCuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel17.setText("cada");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, -1, -1));

        txtCadaVideos.setText("15");
        getContentPane().add(txtCadaVideos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 60, -1));

        jLabel18.setText("segundos");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        tabla_accesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "user", "password", "redSocial", "activo"
            }
        ));
        tabla_accesos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_accesosMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_accesosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla_accesosMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_accesos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 600, 160));

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 600, -1, -1));
        getContentPane().add(passwordKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 230, -1));

        listYoutubeWeb.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "YouTube Views", "Web Traffic" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listYoutubeWeb.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(listYoutubeWeb);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 140, 70));

        jLabel19.setText("Paises");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean arrayContiene(int[] array, int numero) {
        int i = 0;
        while (i < array.length) {
            if (array[i] == numero) {
                return true;
            }
        }
        return false;
    }


    private void btnGanarPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGanarPuntosActionPerformed

        c.inicializarWebdriver(path_drive);
        c.accedeKL(userKL.getText(), passwordKL.getText());
        c.clickVideos();


    }//GEN-LAST:event_btnGanarPuntosActionPerformed

    private void userKLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKLKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_userKLKeyPressed

    private void guardarFBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarFBActionPerformed
        String errorFalta = "";
        if (userKL.getText().length() == 0) {
            errorFalta = errorFalta + "Falta username kingdomlikes\n";
        }
        if (passwordKL.getText().length() == 0) {
            errorFalta = errorFalta + "Falta password kingdomlikes\n";
        }
        if (guardarFB.isSelected()) {
            //SI FALTA USER O PASSWORD MUESTRA UN MENSAJE DE ERROR
            if (errorFalta.length() > 0) {
                JOptionPane.showMessageDialog(null, errorFalta);
                guardarFB.setSelected(false);
            } //SI NO HAY ERROR GUARDAMOS USER Y PASS DE FB
            else {
                guardarUserPass(userKL.getText(), passwordKL.getText(), "KL");
            }
        } else {
            guardarUserPass("", "", "KL");
        }
    }//GEN-LAST:event_guardarFBActionPerformed

    private void KLnumPaginasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KLnumPaginasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KLnumPaginasKeyPressed

    private void btnKLCrearPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLCrearPaginasActionPerformed
        c.inicializarWebdriver(path_drive);
        c.accedeKL(userKL.getText(), passwordKL.getText());
        int tabs = Integer.parseInt(KLnumPaginas.getText());
        String tipo=listYoutubeWeb.getSelectedValue();
        c.creaVideos(KLPaises.getText(), tabs,tipo);
    }//GEN-LAST:event_btnKLCrearPaginasActionPerformed

    private void checIntercambiarCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checIntercambiarCuentasActionPerformed

        intercambiarCuentas();
    }//GEN-LAST:event_checIntercambiarCuentasActionPerformed

    private void tabla_accesosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_accesosMouseClicked
        asigna_tabla_accesos_input_text();
    }//GEN-LAST:event_tabla_accesosMouseClicked

    private void tabla_accesosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_accesosMouseEntered
        asigna_tabla_accesos_input_text();
    }//GEN-LAST:event_tabla_accesosMouseEntered

    private void tabla_accesosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_accesosMouseReleased
        asigna_tabla_accesos_input_text();
    }//GEN-LAST:event_tabla_accesosMouseReleased
    
    //COLOCAMOS EL TEXTO DE LA TABLA EN CADA INPUT TEXT
    public void asigna_tabla_accesos_input_text() {
        try {
            String idPub = tabla_accesos.getValueAt(tabla_accesos.getSelectedRow(), 0).toString();
            userKL.setText(tabla_accesos.getValueAt(tabla_accesos.getSelectedRow(), 1).toString());
            passwordKL.setText(tabla_accesos.getValueAt(tabla_accesos.getSelectedRow(), 2).toString());
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    
    public void intercambiarCuentas(){
        if (checIntercambiarCuentas.isSelected()) {
            // Bucle infinito
            while (true) {
                
                c.escribeLog("\\n\\rSe inicio kl\\n\\r","tmtkl.log");
                
                //Variable que alamacena si ya se ejecuto el ganar puntos
                boolean seEjecuto = false;
                //Bucle de todos los usuarios
                for (int i = 0; i < accesosEjecutado.size(); i++) {
                    //Si este usuario no se ha ejecutado se ejecuta y se guarda
                    if (accesosEjecutado.get(i) == 0) {
                        //Guardamos como que este usuario ya se ejecuto
                        accesosEjecutado.set(i, 1);
                        //Ejecuta funcion
                        seEjecuto = true;
                        //Colocamos el user en el text
                        userKL.setText(accesosUser.get(i));
                        //Colocamos el pass en el text
                        passwordKL.setText(accesosPassword.get(i));
                        c.cerrarNavegador();
                        c.inicializarWebdriver(path_drive);
                        c.accedeKL(userKL.getText(), passwordKL.getText());
                        //Ganamos punto con un limite de videos a ver
                        c.clickVideosLimite(Integer.parseInt(txtCadaVideos.getText()),0,0);
                        c.escribeLog("\\n\\rFin usuario "+userKL.getText()+" kl\\n\\r","tmtkl.log");
                        //c.pausa(2000);
                    }
                }
                //Si nunca se ejecuto el ganar puntos reseteamos
                if (seEjecuto == false) {
                    //Regresamos a 0 todos los usuarios para que se puedan ejecutar de nuevo
                    for (int i = 0; i < accesosEjecutado.size(); i++) {
                        accesosEjecutado.set(i, 0);
                    }
                }
            }
        }        
    }
//AGREGAMOS EN LA BD LA PUBLICACION
    public void guardarUserPass(String SuserKL, String SpasswordKL, String redSocal) {

        String sql = "UPDATE accesos SET "
                + "user = '" + SuserKL + "',  "
                + "password = '" + SpasswordKL + "',  "
                + "redSocal = '" + redSocal + "'  "
                + "WHERE id = '" + userID + "';";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void carga_tabla() {
        String Dato[]=new String[5];
        modelo.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS PUBLICACIONES
            String query = "SELECT "
                    + "id, "
                    + "user, "
                    + "password, "
                    + "redSocal, "
                    + "activo "
                    + "FROM accesos "
                    + "WHERE redSocal='KL';";
            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0]=rs.getString("id");
                Dato[1]=rs.getString("user");
                Dato[2]=rs.getString("password");
                Dato[3]=rs.getString("redSocal");
                Dato[4]=rs.getString("activo");
                modelo.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla_accesos.setModel(modelo);
            //OCULTAMOS EL ID EN LA TABLA
            //tabla.getColumn("id").setMaxWidth(0);
            userKL.setText("");
            passwordKL.setText("");
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea KLPaises;
    private javax.swing.JTextField KLnumPaginas;
    private javax.swing.JButton btnGanarPuntos;
    private javax.swing.JButton btnKLCrearPaginas;
    private javax.swing.JCheckBox checIntercambiarCuentas;
    private javax.swing.JLabel faltanSegundos;
    private javax.swing.JCheckBox guardarFB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listYoutubeWeb;
    private javax.swing.JTextField passwordKL;
    private javax.swing.JTable tabla_accesos;
    private javax.swing.JTextField txtCadaVideos;
    private javax.swing.JTextField userKL;
    // End of variables declaration//GEN-END:variables
}
