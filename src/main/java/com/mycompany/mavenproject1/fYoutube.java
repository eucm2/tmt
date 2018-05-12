package com.mycompany.mavenproject1;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import sun.util.calendar.BaseCalendar.Date;

public class fYoutube extends javax.swing.JInternalFrame {

    long rapido;
    long medio;
    long lento;
    long mlento;
    WebDriver driver;
    Connection connection = null;
    Statement statement = null;
    String path_drive = "";
    DefaultTableModel modelo_publicaciones = new DefaultTableModel();
    cControl c = new cControl();
    String userTW = "";
    String passwordTW = "";
    String idPub = "";
    Object[] options = {"Continuar", "Detener"};

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:tmt.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public fYoutube() {

        initComponents();
        modelo_publicaciones.addColumn("id");
        modelo_publicaciones.addColumn("url");
        modelo_publicaciones.addColumn("titulo");
        modelo_publicaciones.addColumn("imagen");
        modelo_publicaciones.addColumn("veces compartido");
        modelo_publicaciones.addColumn("ultima vez compartida");
        
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
                    + "id,"
                    + "user,"
                    + "password,"
                    + "redSocal "
                    + "FROM accesos;";

            ResultSet rsAccesos = statement.executeQuery(queryAccesos);
            //CICLO QUE LLENA TODO EL MODELO
            while (rsAccesos.next()) {
                String redSocal = rsAccesos.getString("redSocal");
                if (redSocal.equals("FB")) {
                    userFB.setText(rsAccesos.getString("user"));
                    passwordFB.setText(rsAccesos.getString("password"));
                    if(rsAccesos.getString("user").length()>0 && rsAccesos.getString("password").length()>0 ){
                        guardarFB.setSelected(true);
                    }
                }
                if (redSocal.equals("GP")) {
                    userGP.setText(rsAccesos.getString("user"));
                    passwordGP.setText(rsAccesos.getString("password"));
                    if(rsAccesos.getString("user").length()>0 && rsAccesos.getString("password").length()>0 ){
                        guardarGP.setSelected(true);
                    }
                }
                if (redSocal.equals("TW")) {
                    userTW = rsAccesos.getString("user");
                    passwordTW = rsAccesos.getString("password");
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
        carga_tabla_publicaciones(" where activo='1' ");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        compartirVideo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        urlVideo = new javax.swing.JTextField();
        pathImagen = new javax.swing.JTextField();
        buscarImagen = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grupoErrores = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        titulo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_publicaciones = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        numero_veces_compartido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ultima_vez_compartida = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        checkTW = new javax.swing.JCheckBox();
        checkFB = new javax.swing.JCheckBox();
        checkGP = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        busca_titulo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        busca_url = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        userFB = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        userGP = new javax.swing.JTextField();
        guardarGP = new javax.swing.JCheckBox();
        guardarFB = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        passwordGP = new javax.swing.JPasswordField();
        passwordFB = new javax.swing.JPasswordField();

        setTitle("Publicar");
        setInheritsPopupMenu(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        compartirVideo.setText("Compartir");
        compartirVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compartirVideoActionPerformed(evt);
            }
        });
        getContentPane().add(compartirVideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 570, -1, -1));

        jLabel1.setText("URL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));
        getContentPane().add(urlVideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 900, -1));
        getContentPane().add(pathImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 770, -1));

        buscarImagen.setText("Buscar imagen");
        buscarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarImagenActionPerformed(evt);
            }
        });
        getContentPane().add(buscarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 480, -1, -1));

        jLabel2.setText("Path imagen");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        jScrollPane1.setViewportView(grupoErrores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 1050, 128));

        jLabel3.setText("Grupo con errores");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, -1, -1));

        jLabel4.setText("Titulo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 900, -1));

        tabla_publicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "URL", "Titulo", "Imagen", "Palabras_plus", "ultima_vez_compartida"
            }
        ));
        tabla_publicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_publicacionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla_publicacionesMouseEntered(evt);
            }
        });
        tabla_publicaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_publicacionesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla_publicacionesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_publicaciones);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1060, 300));

        jLabel5.setText("numero_veces_compartido");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));
        getContentPane().add(numero_veces_compartido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 900, -1));

        jLabel6.setText("Compartir en");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, -1, -1));
        getContentPane().add(ultima_vez_compartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 540, 900, -1));

        jLabel7.setText("Ultima vez compartido");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        checkTW.setSelected(true);
        checkTW.setText("Twitter");
        getContentPane().add(checkTW, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 580, -1, -1));

        checkFB.setSelected(true);
        checkFB.setText("Facebook");
        getContentPane().add(checkFB, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 580, -1, -1));

        checkGP.setSelected(true);
        checkGP.setText("Google +");
        getContentPane().add(checkGP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 580, -1, -1));

        jLabel11.setText("Buscar por titulo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        busca_titulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_tituloKeyPressed(evt);
            }
        });
        getContentPane().add(busca_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 380, -1));

        jLabel8.setText("Buscar por url");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        busca_url.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_urlKeyPressed(evt);
            }
        });
        getContentPane().add(busca_url, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 340, -1));

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        getContentPane().add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, -1, -1));

        jLabel12.setText("Password FB");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        userFB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userFBKeyPressed(evt);
            }
        });
        getContentPane().add(userFB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, -1));

        jLabel13.setText("Password G+");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

        userGP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userGPKeyPressed(evt);
            }
        });
        getContentPane().add(userGP, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 150, -1));

        guardarGP.setText("Guardar G+");
        guardarGP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarGPActionPerformed(evt);
            }
        });
        getContentPane().add(guardarGP, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 30, -1, -1));

        guardarFB.setText("Guardar FB");
        guardarFB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarFBActionPerformed(evt);
            }
        });
        getContentPane().add(guardarFB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jLabel14.setText("User FB");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel15.setText("User G+");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));
        getContentPane().add(passwordGP, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 140, -1));
        getContentPane().add(passwordFB, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 140, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void compartirVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compartirVideoActionPerformed

        //SACAMOS EL REGISTRO SELECCIONADO
        int rowSel = tabla_publicaciones.getSelectedRow();
        //SCAMOS EL ID DE LA PUBLICACION
        String idPubCompartir = tabla_publicaciones.getValueAt(rowSel, 0).toString();
        //SACAMOS EL NUMERO DE VECES QUE SE VA A COMPARTIR ESTE ARTICULO
        int numeroCompartidasFB = 0;
        int numeroCompartidasGP = 0;
        if (checkFB.isSelected()) {
            numeroCompartidasFB = c.numeroCompartidasFB(idPubCompartir);
            if (numeroCompartidasFB == 0) {
                int res = JOptionPane.showOptionDialog(null, "Este articulo no se compartio ninguna vez en FB\nDesea continuar con cancelar", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                //SI SE DIO CLICK EN DETENER NOS SALIMOS DE ESTA FUNCION
                if (res != 0) {
                    return;
                }
            }
        }
        if (checkGP.isSelected()) {
            numeroCompartidasGP = c.numeroCompartidasGP(idPubCompartir);
            //SI EL ARTICULO NO TIENE GRUPOS DONDE COMPARTIRSE MANDAMOS UN MENAJE DE ERROR
            if (numeroCompartidasGP == 0) {
                int res = JOptionPane.showOptionDialog(null, "Este articulo no se compartio ninguna vez en G+\nDesea continuar con cancelar", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                //SI SE DIO CLICK EN DETENER NOS SALIMOS DE ESTA FUNCION
                if (res != 0) {
                    return;
                }
            }
        }
        //SACAMOS LA FECHA DE HOY
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHoy = format1.format(cal.getTime());
        //EDITAMOS EN LA BD LA FECHA DE LA ULTIMA VEZ PUBLICADO (OSEA HOY)
        editar_ultima_vez(idPubCompartir, fechaHoy);
        //ACTUALIZAMOS EL JTABLE, EL REGISTRO DE ESTA PUBLICACION CON LA FECHA DE HOY
        tabla_publicaciones.setValueAt(fechaHoy, rowSel, 5);
        //VARIABLE QUE ALAMACENA LOS INPUTS QUE NO SE HAN PUESTO Y MUESTRA UN MENSAJE TEXTAREA CON ESOS GRUPOS
        String mensajeError = "";
        //VALIDAMOS QUE EL ARTICULO TENGA TITULO Y URL
        if (urlVideo.getText().length() == 0) {
            mensajeError = mensajeError + "La URL es obligatoria\n";
        }
        if (titulo.getText().length() == 0) {
            mensajeError = mensajeError + "El titulo es obligatoria\n";
        }
        //SI FALTA UN CAMPO OBLIGATORIO SE MUESTRA UN MENSAJE CON ESE ERROR
        if (mensajeError.length() > 0) {
            JOptionPane.showOptionDialog(null, mensajeError, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        } //SI NO FALTA NINGUN DATO OBLIGATORIO PROCEDEMOS A PUBLICAR
        else {
            int numeroNoCompartidoFB = 0;
            int numeroNoCompartidoGP = 0;
            //INICIALIZAMOS EL CHROMEDRIVER
            c.inicializarWebdriver(path_drive);
            //SI EL CHECK DE FB ESTA ACTIVO PUBLICAMOS EN FB FFFFBBBBBB
            if (checkFB.isSelected()) {
                //NOS LOGUEAMOS EN FB O ESPERAMOS A QUE EL USUARIO TERMINE DE LOGUEARSE
                c.accedeFB(userFB.getText(), passwordFB.getText());
                //COMENZAMOS A COMPARTIR EL VIDEO
                String grupoError = c.compartirFB(urlVideo.getText(), pathImagen.getText(), titulo.getText(), idPubCompartir);
                grupoErrores.setText(grupoError);
                numeroNoCompartidoFB = countLines(grupoError);
            }
            //SI EL CHECK DE G+ ESTA ACTIVO PUBLICAMOS EN G+ GGGG+++++++
            if (checkGP.isSelected()) {
                //NOS LOGUEAMOS EN FB O ESPERAMOS A QUE EL USUARIO TERMINE DE LOGUEARSE
                c.accedeGP(userGP.getText(), passwordGP.getText());
                //COMENZAMOS A COMPARTIR EL VIDEO
                String grupoError = c.compartirGP(urlVideo.getText(), pathImagen.getText(), titulo.getText(), idPubCompartir);
                grupoErrores.setText(grupoErrores.getText() + grupoError);
                numeroNoCompartidoGP = countLines(grupoError);
            }
            //SUMAMOS LAS COMPARTIDAS MENOS LAS NO COMPARTIDAS
            int totalCompartidas = numeroCompartidasFB + numeroCompartidasGP - numeroNoCompartidoFB - numeroNoCompartidoGP;
            editar_numero_veces_compartido(idPubCompartir, "" + totalCompartidas + "");
            int selRow = tabla_publicaciones.getSelectedRow();
            tabla_publicaciones.setValueAt(totalCompartidas, selRow, 4);

        }


    }//GEN-LAST:event_compartirVideoActionPerformed
    //NUMERO DE LINEAS DE UN STRING
    private static int countLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        //LE RESTAMOS 1 PORQUE SIEMPRE CUANTA AUQUE ESTE VACIO
        return lines.length-1;
    }

    public void setClipboardContents(String aString) {
        StringSelection stringSelection = new StringSelection(aString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void buscarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarImagenActionPerformed

        //AL HACER CLICK EN EL BOTON DE SELECCIONAR IMAGEN TOMAMOS ESA IMAGEN A SUBIR
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            pathImagen.setText(selectedFile.getPath());
        }

    }//GEN-LAST:event_buscarImagenActionPerformed
    private void tabla_publicacionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_publicacionesKeyPressed


    }//GEN-LAST:event_tabla_publicacionesKeyPressed

    private void tabla_publicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_publicacionesMouseClicked
        System.out.println("com.mycompany.mavenproject1.fYoutube.tabla_publicacionesMouseClicked()");
        System.out.println(tabla_publicaciones.getSelectedRow());
        asigna_tabla_publicaciones_input_text();
        
    }//GEN-LAST:event_tabla_publicacionesMouseClicked
    private void tabla_publicacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_publicacionesMouseEntered
        System.out.println("com.mycompany.mavenproject1.fYoutube.tabla_publicacionesMouseEntered()");
        System.out.println(tabla_publicaciones.getSelectedRow());
        asigna_tabla_publicaciones_input_text();
    }//GEN-LAST:event_tabla_publicacionesMouseEntered

    private void tabla_publicacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_publicacionesKeyReleased
        System.out.println("com.mycompany.mavenproject1.fYoutube.tabla_publicacionesKeyPressed()");
        System.out.println(tabla_publicaciones.getSelectedRow());
        asigna_tabla_publicaciones_input_text();
    }//GEN-LAST:event_tabla_publicacionesKeyReleased

    private void busca_tituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_tituloKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            filtrar();
        }
    }//GEN-LAST:event_busca_tituloKeyPressed

    private void busca_urlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_urlKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            filtrar();
        }
    }//GEN-LAST:event_busca_urlKeyPressed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        filtrar();
    }//GEN-LAST:event_BuscarActionPerformed

    private void userFBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userFBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_userFBKeyPressed

    private void userGPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userGPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_userGPKeyPressed

    private void guardarFBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarFBActionPerformed
        String errorFalta="";
        if(userFB.getText().length()==0){
            errorFalta=errorFalta+"Falta username Facebook\n";
        }
        if(passwordFB.getText().length()==0){
            errorFalta=errorFalta+"Falta password Facebook\n";
        }
        if(guardarFB.isSelected()){
            //SI FALTA USER O PASSWORD MUESTRA UN MENSAJE DE ERROR
            if(errorFalta.length()>0){
                JOptionPane.showMessageDialog(null, errorFalta);
                guardarFB.setSelected(false);
            }
            //SI NO HAY ERROR GUARDAMOS USER Y PASS DE FB
            else{
                guardarUserPass(userFB.getText(),passwordFB.getText(),"FB");
            }            
        }
        else{
            guardarUserPass("","","FB");
        }
    }//GEN-LAST:event_guardarFBActionPerformed

    private void guardarGPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarGPActionPerformed
        String errorFalta="";
        if(userGP.getText().length()==0){
            errorFalta=errorFalta+"Falta username Google +\n";
        }
        if(passwordGP.getText().length()==0){
            errorFalta=errorFalta+"Falta password Google +\n";
        }
        if(guardarGP.isSelected()){
            //SI FALTA USER O PASSWORD MUESTRA UN MENSAJE DE ERROR
            if(errorFalta.length()>0){
                JOptionPane.showMessageDialog(null, errorFalta);
                guardarGP.setSelected(false);
            }
            //SI NO HAY ERROR GUARDAMOS USER Y PASS DE GP
            else{
                guardarUserPass(userGP.getText(),passwordGP.getText(),"GP");
            }
        }
        else{
            guardarUserPass("","","GP");
        }

    }//GEN-LAST:event_guardarGPActionPerformed
    //COLOCAMOS EL TEXTO DE LA TABLA EN CADA INPUT TEXT
    public void asigna_tabla_publicaciones_input_text() {
        try {
            idPub = tabla_publicaciones.getValueAt(tabla_publicaciones.getSelectedRow(), 0).toString();
            urlVideo.setText(tabla_publicaciones.getValueAt(tabla_publicaciones.getSelectedRow(), 1).toString());
            titulo.setText(tabla_publicaciones.getValueAt(tabla_publicaciones.getSelectedRow(), 2).toString());
            pathImagen.setText(tabla_publicaciones.getValueAt(tabla_publicaciones.getSelectedRow(), 3).toString());
            numero_veces_compartido.setText(tabla_publicaciones.getValueAt(tabla_publicaciones.getSelectedRow(), 4).toString());
            ultima_vez_compartida.setText(tabla_publicaciones.getValueAt(tabla_publicaciones.getSelectedRow(), 5).toString());
            checkFB.setText("Facebook="+c.numeroCompartidasFB(idPub)+"");
            checkGP.setText("Google +="+c.numeroCompartidasGP(idPub)+"");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void filtrar() {
        String where = "";
        //SI EL TEXTO DE BUSCAR TIENE TEXTO AGREGAMOS ESE TYEXTO A LA BUSQUEDA
        if (busca_titulo.getText().length() > 0) {
            where = where + " titulo LIKE '%" + busca_titulo.getText() + "%' ";
        }
        if (busca_url.getText().length() > 0) {
            if (where.length() > 0) {
                where = where + " and url  LIKE '%" + busca_url.getText() + "%' ";
            } else {
                where = where + " url  LIKE '%" + busca_url.getText() + "%' ";
            }
        }
        if (where.length() > 0) {
            where = " where " + where +" and activo='1' ";
        }
        else{
            where = " where activo='1' ";
        }
        carga_tabla_publicaciones(where);
    }

    //CARGA PUBLICACIONES
    public void carga_tabla_publicaciones(String where) {
        String Dato[] = new String[6];
        modelo_publicaciones.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS PUBLICACIONES
            String query = "SELECT "
                    + "id,"
                    + "url,"
                    + "imagen,"
                    + "titulo,"
                    + "numero_veces_compartido, "
                    + "ultima_vez_compartida "
                    + "FROM publicaciones "
                    + where
                    + "order by orden;";
            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0] = rs.getString("id");
                Dato[1] = rs.getString("url");
                Dato[2] = rs.getString("titulo");
                Dato[3] = rs.getString("imagen");
                Dato[4] = rs.getString("numero_veces_compartido");
                Dato[5] = rs.getString("ultima_vez_compartida");
                modelo_publicaciones.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla_publicaciones.setModel(modelo_publicaciones);
            tabla_publicaciones.getColumn("id").setMaxWidth(30);
            tabla_publicaciones.getColumn("imagen").setMaxWidth(30);
            //OCULTAMOS EL ID EN LA TABLA
            //tabla.getColumn("id").setMaxWidth(0);
            urlVideo.setText("");
            titulo.setText("");
            pathImagen.setText("");
            ultima_vez_compartida.setText("");
            tabla_publicaciones.changeSelection(0, 0, false, false);
            asigna_tabla_publicaciones_input_text();
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

    //EDITA FECHA DE ULTIMA VEZ QUE SE COMPARTIO
    public void editar_ultima_vez(String id, String ultima_vez_compartida) {
        String sql = "UPDATE publicaciones SET "
                + "ultima_vez_compartida = '" + ultima_vez_compartida + "'  "
                + "WHERE id = " + id;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //EDITA FECHA DE ULTIMA VEZ QUE SE COMPARTIO
    public void editar_numero_veces_compartido(String id, String numero_veces_compartido) {
        String sql = "UPDATE publicaciones SET "
                + "numero_veces_compartido = '" + numero_veces_compartido + "'  "
                + "WHERE id = " + id;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //AGREGAMOS EN LA BD LA PUBLICACION
    public void guardarUserPass(String SuserFB,String SpasswordFB,String redSocal) {
        
        String sql = "UPDATE accesos SET "
                + "user = '" + SuserFB + "',  "
                + "password = '" + SpasswordFB + "',  "
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
    private javax.swing.JButton Buscar;
    private javax.swing.JTextField busca_titulo;
    private javax.swing.JTextField busca_url;
    private javax.swing.JButton buscarImagen;
    private javax.swing.JCheckBox checkFB;
    private javax.swing.JCheckBox checkGP;
    private javax.swing.JCheckBox checkTW;
    private javax.swing.JButton compartirVideo;
    private javax.swing.JTextPane grupoErrores;
    private javax.swing.JCheckBox guardarFB;
    private javax.swing.JCheckBox guardarGP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField numero_veces_compartido;
    private javax.swing.JPasswordField passwordFB;
    private javax.swing.JPasswordField passwordGP;
    private javax.swing.JTextField pathImagen;
    private javax.swing.JTable tabla_publicaciones;
    private javax.swing.JTextField titulo;
    private javax.swing.JTextField ultima_vez_compartida;
    private javax.swing.JTextField urlVideo;
    private javax.swing.JTextField userFB;
    private javax.swing.JTextField userGP;
    // End of variables declaration//GEN-END:variables
}
