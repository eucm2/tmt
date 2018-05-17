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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.openqa.selenium.WebDriver;

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
    int hora_ini = 0;
    int hora_ini_variacion = 0;
    int hora_fin = 0;
    int hora_fin_variacion = 0;
    int cada_horas = 0;
    String dias_semana = "";
    String escribirCon = "";
    int modo_prueba=0;
    int tiempo_x2=0;

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
        listaHorarios.getColumn("No").setMaxWidth(40);
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
                    + "mlento, "
                    + "hora_ini, "
                    + "hora_ini_variacion, "
                    + "hora_fin, "
                    + "hora_fin_variacion, "
                    + "cada_horas, "
                    + "dias_semana, "
                    + "escribirCon, "
                    + "modo_prueba, "
                    + "tiempo_x2 "
                    + "FROM configuracion";

            ResultSet rs = statement.executeQuery(query);
            path_drive = rs.getString("path_drive");
            rapido = Long.parseLong(rs.getString("rapido"));
            medio = Long.parseLong(rs.getString("medio"));
            lento = Long.parseLong(rs.getString("lento"));
            mlento = Long.parseLong(rs.getString("mlento"));
            hora_ini = Integer.parseInt(rs.getString("hora_ini"));
            hora_ini_variacion = Integer.parseInt(rs.getString("hora_ini_variacion"));
            hora_fin = Integer.parseInt(rs.getString("hora_fin"));
            hora_fin_variacion = Integer.parseInt(rs.getString("hora_fin_variacion"));
            cada_horas = Integer.parseInt(rs.getString("cada_horas"));
            dias_semana = rs.getString("dias_semana");
            modo_prueba = Integer.parseInt(rs.getString("modo_prueba"));
            tiempo_x2 = Integer.parseInt(rs.getString("tiempo_x2"));
            escribirCon = rs.getString("escribirCon");
            //SI EL MODO PRUEBA ESTA ACTIVO (SE EJECUTAN PUBLICACIONES CADA 2 MINUTOS)
            if(modo_prueba==1){
                //MOSTRAMOS UN LABEL QUE AVISE QUE EL MODO PRUEBA ESTA ACTIVO
                lblModo.setText("Modo prueba activo tiempo por 2 =" + tiempo_x2 );
                lblModo.setBackground(Color.green);
            }
            //SI EL MODO PRUEBA ESTA DESACTIVO (FUNCIONA DE FORMA NORMAL)
            else{
                lblModo.setText("Modo prueba descativo  tiempo por 2 =" + tiempo_x2);
                lblModo.setBackground(Color.red);
            }
            //INICIALIZAMOS E CONTROLADOR CON LA FORMA DE ESCRIBIR QUE ESTA EN LA BD
            c.cControl(escribirCon);
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
        activar = new javax.swing.JCheckBox();
        lblHoraReiniciar = new javax.swing.JLabel();
        btnGanarPuntos = new javax.swing.JButton();
        horaActual = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        horaIni = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        horaFin = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaHorarios = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        tiempoFaltaEjecutar = new javax.swing.JLabel();
        lblModo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setTitle("Publicar");
        setInheritsPopupMenu(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        compartirVideo.setText("Compartir");
        compartirVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compartirVideoActionPerformed(evt);
            }
        });
        getContentPane().add(compartirVideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 420, -1, -1));

        jLabel1.setText("URL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        getContentPane().add(urlVideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 900, -1));
        getContentPane().add(pathImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 770, -1));

        buscarImagen.setText("Buscar imagen");
        buscarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarImagenActionPerformed(evt);
            }
        });
        getContentPane().add(buscarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 330, -1, -1));

        jLabel2.setText("Path imagen");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jScrollPane1.setViewportView(grupoErrores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 910, 60));

        jLabel3.setText("Grupos con errores");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel4.setText("Titulo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 900, -1));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1060, 150));

        jLabel5.setText("numero_veces_compartido");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));
        getContentPane().add(numero_veces_compartido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 900, -1));

        jLabel6.setText("Compartir en");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));
        getContentPane().add(ultima_vez_compartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 900, -1));

        jLabel7.setText("Ultima vez compartido");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        checkTW.setText("Twitter");
        getContentPane().add(checkTW, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, -1, -1));

        checkFB.setSelected(true);
        checkFB.setText("Facebook");
        getContentPane().add(checkFB, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, -1, -1));

        checkGP.setText("Google +");
        getContentPane().add(checkGP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, -1, -1));

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

        activar.setText("Activar");
        activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activarActionPerformed(evt);
            }
        });
        getContentPane().add(activar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 70, 20));

        lblHoraReiniciar.setText("horaReiniciar");
        getContentPane().add(lblHoraReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 360, 20));

        btnGanarPuntos.setText("Ejecutar ahora");
        btnGanarPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGanarPuntosActionPerformed(evt);
            }
        });
        getContentPane().add(btnGanarPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 550, -1, -1));

        horaActual.setText("Hora actual");
        getContentPane().add(horaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 590, -1, -1));

        jLabel16.setText("Hora actual.............");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, -1, -1));

        horaIni.setText("Hora Inicial");
        getContentPane().add(horaIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 620, -1, -1));

        jLabel17.setText("Hora Inicial.............");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, -1));

        jLabel20.setText("Hora Final.......................................");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 590, -1, -1));

        horaFin.setText("Hora Final");
        getContentPane().add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 590, -1, -1));

        listaHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Hora"
            }
        ));
        listaHorarios.setName("listaHorarios"); // NOI18N
        jScrollPane3.setViewportView(listaHorarios);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 590, 310, 130));

        jLabel21.setText("Segundos que faltan para ejeuitar");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, -1, -1));

        tiempoFaltaEjecutar.setText("Tiempo a ajecutar");
        getContentPane().add(tiempoFaltaEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 620, -1, -1));

        lblModo.setBackground(new java.awt.Color(102, 102, 255));
        lblModo.setText("modo");
        lblModo.setOpaque(true);
        getContentPane().add(lblModo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, -1, -1));

        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 760, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void compartirVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compartirVideoActionPerformed
        //COMPARTIMOS LOS ARTICULOS EN FB
        compartirPublicacion("manual");

    }//GEN-LAST:event_compartirVideoActionPerformed

    public void compartirPublicacion(String ejecutadoDeForma){
        String idPubCompartir="";
        //SI LA FORMA DE COMPARTIR FUE DE FORMA MANUAL OSEA CON UN CLICK SE EJECUTA EL REGISTRO SELECCIONADO
        if(ejecutadoDeForma.equals("manual")){
            //SACAMOS EL REGISTRO SELECCIONADO
            int rowSel = tabla_publicaciones.getSelectedRow();
            //SCAMOS EL ID DE LA PUBLICACION
            idPubCompartir = tabla_publicaciones.getValueAt(rowSel, 0).toString();
        }
        //SI EL COMPARTIR VIENE DESE CRONOMETRO SE SELECCIONA EL SIGUIENTE ARTICULO EN LA LISTA
        else if(ejecutadoDeForma.equals("cronometro")){
            idPubCompartir = sigArtAcomparir("FB");
            //FILTRAMOS LA TABLA POR ESTE ID
            buscarTablaPorId(idPubCompartir);
        }
        //SACAMOS EL NUMERO DE GRUPOS EN LOS QUE SE VA A COMPARTIR ESTE ARTICULO
        int numeroCompartidasFB = 0;
        int numeroCompartidasGP = 0;
        //NUMERO DE ARTICULOS A COMPARTIR POR TANDA
        int numeroAcompartir=3;
        //SI ESTA ACTIVO EL CHECKBOX DE COMPARTIR EN FB LO COMPARTIMOS EN FB
        if (checkFB.isSelected()) {
            numeroCompartidasFB = c.numeroCompartidasFB(idPubCompartir);
            if (numeroCompartidasFB == 0) {
                int res = JOptionPane.showOptionDialog(null, "Este articulo no se compartira en ningun grupo de FB\nDesea continuar con cancelar", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                //SI SE DIO CLICK EN DETENER NOS SALIMOS DE ESTA FUNCION
                if (res != 0) {
                    return;
                }
            }
        }
        //SI ESTA ACTIVO EL CHECKBOX DE COMPARTIR EN GOOGLE+ LO COMPARTIMOS EN FB
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
        //tabla_publicaciones.setValueAt(fechaHoy, rowSel, 5);
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
                //SACAMOS LA LISTA DE LOS ID'S DE LOS GRUPOS QUE YA SE COMPARTIERON EN ESTE ARTICULO DE FB
                String idsYaCompartidos=yaSeCompartioEn(idPubCompartir,"FB");
                //COMENZAMOS A COMPARTIR EL VIDEO
                String [] errorYlistaGrupos = c.compartirFB(urlVideo.getText(), pathImagen.getText(), titulo.getText(), idPubCompartir,numeroAcompartir,idsYaCompartidos);
                if (errorYlistaGrupos == null) {
                    //ACTUALIZAMOS ESTE ARTICULO Y LO MARCAMOS COMO YA COMPARTIDO
                    articuloYaCompartido(idPubCompartir,"FB");
                    //CERRAMOS EL NAVEGADOR
                    c.cerrarNavegador();
                    //COMO ESTE ARTICULO YA ESTA COMPARTIDO REGRESAMOS AL PRINCIPIO A COMPARTIR EL SIGUIENTE
                    compartirPublicacion("cronometro");
                }
                grupoErrores.setText(errorYlistaGrupos[0]);
                numeroNoCompartidoFB = countLines(errorYlistaGrupos[0]);
                //AGREGAMOS LOS GRUPOS YA COMPARTIDOS DE ESTE ARTICULO
                actualizaYaPublicadoEnFB(errorYlistaGrupos[1],idPubCompartir);
                //SI NO ESTA SELECCIONADO GP CERRAMOS EL NAVEGADOR
                if (checkGP.isSelected()==false) {
                    c.cerrarNavegador();
                }
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
            /*
            //SUMAMOS LAS COMPARTIDAS MENOS LAS NO COMPARTIDAS
            int totalCompartidas = numeroCompartidasFB + numeroCompartidasGP - numeroNoCompartidoFB - numeroNoCompartidoGP;
            editar_numero_veces_compartido(idPubCompartir, "" + totalCompartidas + "");
            int selRow = tabla_publicaciones.getSelectedRow();
            tabla_publicaciones.setValueAt(totalCompartidas, selRow, 4);
            */
        }

        
    }

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

    private void activarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarActionPerformed
        reiniciarCronometro();
    }//GEN-LAST:event_activarActionPerformed

    private void btnGanarPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGanarPuntosActionPerformed
        
        compartirPublicacion("manual");
        
    }//GEN-LAST:event_btnGanarPuntosActionPerformed
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

    public void buscarTablaPorId(String id) {
        String where = "";
        where = where + " WHERE id = '" + id + "' ";
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
    //ACTUALIZAMOS ESTE ARTICULO Y LO MARCAMOS COMO YA COMPARTIDO
    public void articuloYaCompartido(String idPubCompartir,String tipo){
        String campo="";
        //DEPENDIENDO DE LA RED SOCIAL ENVIA SE ACTUALIZA EL CAMPO EN ESPESIFICO
        if(tipo.equals("FB")){
            campo="terminado_compartir_fb";
        }else if(tipo.equals("GP")){
            campo="terminado_compartir_gp";
        }
        String sql = "UPDATE publicaciones SET "
                + campo + " = '1'  "
                + "WHERE id = " + idPubCompartir;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }
    public void actualizaYaPublicadoEnFB(String listaIdsGrupos,String idPub){
        String sql = "UPDATE publicaciones SET  ya_publicado_en_fb=ya_publicado_en_fb||'"+listaIdsGrupos+"' WHERE id='"+idPub+"'; ";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //ENTREGAMOS UN STRING SEPARADO POR COMAS DE TODOS LOS GRUPOS DONDE YA SE PUBLICO UN ARTICULO
    public String yaSeCompartioEn(String idPub,String tipo) {
        String id = "";
        int vecesCompartido=0;
        String ya_publicado="";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //SACAMOS LA LISTA DE TODOS LOS GRUPOS DE FACEBOOK
            String query = "  SELECT " +
            "publicaciones.id, " +
            "publicaciones.ya_publicado_en_fb, " +
            "publicaciones.ya_publicado_en_gp " +
            "FROM " +
            "publicaciones " +
            "WHERE " +
            "publicaciones.id = '"+idPub+"';  ";
            ResultSet rs = statement.executeQuery(query);
            //RETORNAMOS SOLO LOS ARTICULOS DE DE FB
            if(tipo.equals("FB")==true){
                return Objects.toString(rs.getString("ya_publicado_en_fb"),"");
            }
            //RETORNAMOS SOLO LOS ARTICULOS DE DE GP
            else if(tipo.equals("GP")==true){
                return Objects.toString(rs.getString("ya_publicado_en_gp"),"");
            }

        }
        catch (NoSuchElementException e) {
            int res = JOptionPane.showOptionDialog(null, "Desea continuar o detener? \n error= " + e, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (res == 1) {
                driver.quit();
            }
        } catch (Exception e) {
            int res = JOptionPane.showOptionDialog(null, "Desea continuar o detener? \n error= " + e, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (res == 1) {
                driver.quit();
            }
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
        return "";
    }
    //ENTREGAMOS EL ID DEL ARTICULO QUE AUN NO SE HA PUBLICADO EN TODOS LOS GRUPOS
    public String sigArtAcomparir(String tipo) {
        
        String campo="";
        //DEPENDIENDO DE LA RED SOCIAL ENVIA SE ACTUALIZA EL CAMPO EN ESPESIFICO
        if(tipo.equals("FB")){
            campo="terminado_compartir_fb";
        }else if(tipo.equals("GP")){
            campo="terminado_compartir_gp";
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //SACAMOS LA LISTA DE TODOS LOS GRUPOS DE FACEBOOK 
            String query = " SELECT\n" +
            "publicaciones.id,\n" +
            "publicaciones.titulo,\n" +
            "publicaciones.imagen,\n" +
            "publicaciones.url,\n" +
            "publicaciones.orden\n" +
            "FROM publicaciones\n" +
            "WHERE\n" +
            "publicaciones."+campo+" = 0 AND\n" +
            "publicaciones.activo = 1\n" +
            "order by orden\n" +
            "LIMIT 1; ";
            ResultSet rs = statement.executeQuery(query);
            String[] datos = new String[3];
            return rs.getString("id");
        }
        catch (NoSuchElementException e) {
            int res = JOptionPane.showOptionDialog(null, "Desea continuar o detener? \n error= " + e, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (res == 1) {
                driver.quit();
            }
        } catch (Exception e) {
            int res = JOptionPane.showOptionDialog(null, "Desea continuar o detener? \n error= " + e, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (res == 1) {
                driver.quit();
            }
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
        return "";
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

    
    
    
    //REINICIA CRONMETRO CADA 24HORAS PARA QUE LA HORA DEL DIA SE LIMPIE
    public void reiniciarCronometro() {
        if (activar.isSelected()) {
            final int reiniciarAlasHoras = 23;
            final int reiniciarAlasMinutos = 59;
            final int reiniciarAlasSegundos = 59;

            int minuto = Calendar.getInstance().get(Calendar.MINUTE);
            int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int ano = Calendar.getInstance().get(Calendar.YEAR);
            int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

            java.util.Date horaReiniciar = null;
            DateFormat formatoFechaHora = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
            try {
                if (modo_prueba == 0) {
                    //GENERAMOS LA HORA DE INICIO DE EJECUCION OSEA CADA 24 HORAS
                    horaReiniciar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + reiniciarAlasHoras + ":" + reiniciarAlasMinutos + ":" + reiniciarAlasSegundos);
                    cronometroParaEjecutar();
                }
                if (modo_prueba == 1) {
                    horaReiniciar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + hora + ":" + minuto + ":59");
                }
            } catch (ParseException ex) {
                Logger.getLogger(hitleap.class.getName()).log(Level.SEVERE, null, ex);
            }
            final Calendar calHorasReiniciar = Calendar.getInstance();
            calHorasReiniciar.setTime(horaReiniciar);
            final Timer timerReiniciar = new Timer();
            TimerTask taskReiniciar = new TimerTask() {
                @Override
                //INICIA EL TEMPORALIZADOR
                public void run() {
                    //INIZIALIZAMOS EL CALENDARIO CON LA HORA ACTUAL
                    final Calendar calHoraActualReiniciar = Calendar.getInstance();
                    long segundosFaltanParaReiniciar = (calHorasReiniciar.getTimeInMillis() - calHoraActualReiniciar.getTimeInMillis()) / (1000);
                    //SI ESTA ACTIVO EL TIEMPO POR 2 EN LA BD HACEMOS QUE EL TIEMPO DE REINICIO SEA DE LA MITAD
                    if(tiempo_x2==1){
                        segundosFaltanParaReiniciar=segundosFaltanParaReiniciar/2;
                    }
                    lblHoraReiniciar.setText("Segundos que faltan para reiniciar=" + segundosFaltanParaReiniciar);
                    //SI YA ESTAMOS EN EL MINUTO 0 EJECUTAMOS LA PUBLICACION
                    if (segundosFaltanParaReiniciar >= 0 && segundosFaltanParaReiniciar <= 10) {
                        //COMENZAMOS LA CUENTA REGRESIVA PARA EJECUTAR EL PUBLICAR VIDEOS
                        cronometroParaEjecutar();
                        c.pausa(1000 * 335);
                        timerReiniciar.cancel();
                        timerReiniciar.purge();
                        //REINICIAMOS ESTE MISMO CRONOMETRO CADA 24 HORAS PARA QUE SE ACTUALICE LA HORA Y EL DIA A EJECUTAR
                        reiniciarCronometro();
                    }

                }
            };
            timerReiniciar.scheduleAtFixedRate(taskReiniciar, 1000, 1000);
        }
    }

    
    
    
    int counter = 10;
    Boolean isIt = false;

    Calendar calHoraIni = Calendar.getInstance();
    Calendar calHoraFin = Calendar.getInstance();
    Calendar[] calHorasEjecutar = new Calendar[25];

    //CALCULA EL TIEMPO ENTRE UNA EJECUCION Y OTRA
    public void cronometroParaEjecutar() {
        int minuto = Calendar.getInstance().get(Calendar.MINUTE);
        int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //CUENTA EL NUMERO DE VES QUE SE PUBLICA PARA EVITAR QUE SE PUBLIQUE MAS DE 2 VECES
        int intNumeroPublicadas=0;
        DateFormat formatoFechaHora = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        //ASIGNAMOS LA CANTIDAD DE HORAS QUE VA A VARIAR LA HORA DE INICIO Y FIN
        int hora_ini_variacion_res = (int) (Math.random() * (hora_ini_variacion * 2)) - hora_ini_variacion;
        int hora_fin_variacion_res = (int) (Math.random() * (hora_fin_variacion * 2)) - hora_fin_variacion;
        //COLOCAMOS LA HORA EN QUE VA A COMENZAR Y TERMINAL EL PROCESO
        int hora_ini_res = hora_ini + hora_ini_variacion_res;
        int hora_fin_res = hora_fin + hora_fin_variacion_res;
        //LE COLOCAMOS AL CALENDARIO LA HORA INICIAL Y FINAL
        calHoraIni.set(Calendar.HOUR_OF_DAY, hora_ini_res);
        calHoraFin.set(Calendar.HOUR_OF_DAY, hora_fin_res);
        //COLOCAMOS LA HORA ACTUAL EN EL LABEL
        horaIni.setText(calHoraIni.get(Calendar.HOUR_OF_DAY) + ":" + calHoraIni.get(Calendar.MINUTE) + ":" + calHoraIni.get(Calendar.SECOND));
        horaFin.setText(calHoraFin.get(Calendar.HOUR_OF_DAY) + ":" + calHoraFin.get(Calendar.MINUTE) + ":" + calHoraFin.get(Calendar.SECOND));
        //INICIALIZAMOS EL MODELO DE LA TABLA
        DefaultTableModel modelTablaConHorarios = (DefaultTableModel) listaHorarios.getModel();
        
        //CONTAMOS LA CANTIDAD DE REGISTROS QUE TIENE LA TLABLA
        int rowCount = modelTablaConHorarios.getRowCount();
        //BORRAMOS TODOS LOS REGISTROS DE LA TABLA
        for (int i = rowCount - 1; i >= 0; i--) {
            modelTablaConHorarios.removeRow(i);
        }
        //CONTADOR DE HORAS QUE PASAN ENTRE EL INICIO Y EL FINAL
        int contadorHoras = 0;
        java.util.Date horaEjecutar = null;
        //BUCLE QUE LLENA LA TABLA CON LAS HORAS ENTRE EL INICIO Y EL FINAL
        for (int cadaXcantidadHoras = hora_ini_res; cadaXcantidadHoras <= hora_fin_res; cadaXcantidadHoras = cadaXcantidadHoras + cada_horas) {
            try {
                //SI NO ES MODO PRUEBA HACEMOS QUE SE EJECUTEN 
                if (modo_prueba == 0) {
                    //GENERAMOS LA HORA DE INICIO DE EJECUCION
                    horaEjecutar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + cadaXcantidadHoras + ":" + calHoraIni.get(Calendar.MINUTE) + ":59");
                }
                //SI ES MODO PRUEBA COLOCAMOS LA FECHAS PARA QUE SE EJECUTEN CADA 2 MINUTOS
                if (modo_prueba == 1) {
                    horaEjecutar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + hora + ":" + (minuto + cadaXcantidadHoras) + ":59");
                }
                //INICIALIZAMOS EL CALENDARIO
                calHorasEjecutar[contadorHoras] = Calendar.getInstance();
                //LE ASIGNAMOS AL CALENDARIO LA HORA DE INICIO DE JECUCION
                calHorasEjecutar[contadorHoras].setTime(horaEjecutar);
            } catch (ParseException ex) {
                Logger.getLogger(hitleap.class.getName()).log(Level.SEVERE, null, ex);
            }
            //ESCRIBIMOS EN LA TABLA LAS HORAS EN LAS QUE SE VA EJECUTAR
            modelTablaConHorarios.addRow(new Object[]{"" + (contadorHoras + 1), calHorasEjecutar[contadorHoras].get(Calendar.YEAR) + "-" + calHorasEjecutar[contadorHoras].get(Calendar.MONTH) + "-" + calHorasEjecutar[contadorHoras].get(Calendar.DAY_OF_MONTH) + " " + calHorasEjecutar[contadorHoras].get(Calendar.HOUR_OF_DAY) + ":" + calHorasEjecutar[contadorHoras].get(Calendar.MINUTE) + ":" + calHorasEjecutar[contadorHoras].get(Calendar.SECOND)});
            contadorHoras = contadorHoras + 1;
        }
        //CREAMOS ESTA VARIABLE PARA PODER METERLA DENDRO DEL RELOJ
        final int contadorHorasFinal = contadorHoras;
        //INICIALIZAMOS EL TEMPORALIZADOR
        final Timer temporalizador = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            //INICIA EL TEMPORALIZADOR
            public void run() {
                //VARIABLE QUE SE USA SOLO PARA MOSTRAR 1 SIGUIENTE HORARIO A EJECUTAR
                boolean flagSoloElPrimero = false;
                //INICIALIZAMOS EL CALENDARIOACTUAL CON LA HORA ACTUAL
                final Calendar calHoraActual = Calendar.getInstance();
                //COLOCAMOS EN EL LABEL LA HORA ACTUAL
                horaActual.setText(
                        calHoraActual.get(Calendar.YEAR) + "/"
                        + calHoraActual.get(Calendar.MONTH) + "/"
                        + calHoraActual.get(Calendar.DAY_OF_MONTH) + " "
                        + calHoraActual.get(Calendar.HOUR_OF_DAY) + ":"
                        + calHoraActual.get(Calendar.MINUTE) + ":"
                        + calHoraActual.get(Calendar.SECOND)
                );
                //CICLAMOS POR CADA HORA PUESTA (SI ALGUN HORARIO SE AJUSTA CON LA HORA ACTUAL SE EJECUTA LA CREACION DE PUBLICACIONES)
                for (int j = 0; j < contadorHorasFinal; j++) {
                    //SACAMOS LOS SEGUNDOS QUE FALTAN PARA EJECUTAR
                    long segundosFaltanParaEjecutar = (calHorasEjecutar[j].getTimeInMillis() - calHoraActual.getTimeInMillis()) / (1000);
                    //SI ESTA ACTIVA EN LA BD EN TEIMPO POR 2 EL TIEMPO QUE SE EJECUTAN LOS COCESOS SE DIVIDE ENTRE 2
                    if(tiempo_x2==1){
                        segundosFaltanParaEjecutar=segundosFaltanParaEjecutar/2;
                    }
                    if (segundosFaltanParaEjecutar > 0 && flagSoloElPrimero == false) {
                        //MOSTRAMOS EN LABEL EL TIEMPO QUE FALTA PARA EJECUTARSE EL SIGUIENTE
                        tiempoFaltaEjecutar.setText("" + segundosFaltanParaEjecutar);
                        //COMO YA ENTRO ESTE YA ENTRA NINGUN OTRO
                        flagSoloElPrimero = true;
                    }
                    //SI YA ESTAMOS EN EL MINUTO 0 EJECUTAMOS LA PUBLICACION
                    if (segundosFaltanParaEjecutar >= 0 && segundosFaltanParaEjecutar <= 10) {
                        temporalizador.cancel();
                        temporalizador.purge();
                        compartirPublicacion("cronometro");
                        //ESPERAMOS 35 SEGUNDOS DESPUES DE HABER PUBLICADO LOS VIDEOS PARA DAR TIEMPO AL CRONOMETRO DE REINICIO A QUE TRABAJE
                        c.pausa(1000 * 35);
                        //SI NO ES MODO PRUEBA SE EJECUTA DE NUEVO EL CONOMETRO PARA EJECUTAR
                        if(modo_prueba==0){
                            cronometroParaEjecutar();
                        }
                    }
                }
            }
        };
        temporalizador.scheduleAtFixedRate(tarea, 1000, 1000);

    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JCheckBox activar;
    private javax.swing.JButton btnGanarPuntos;
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
    private javax.swing.JLabel horaActual;
    private javax.swing.JLabel horaFin;
    private javax.swing.JLabel horaIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblHoraReiniciar;
    private javax.swing.JLabel lblModo;
    private javax.swing.JTable listaHorarios;
    private javax.swing.JTextField numero_veces_compartido;
    private javax.swing.JPasswordField passwordFB;
    private javax.swing.JPasswordField passwordGP;
    private javax.swing.JTextField pathImagen;
    private javax.swing.JTable tabla_publicaciones;
    private javax.swing.JLabel tiempoFaltaEjecutar;
    private javax.swing.JTextField titulo;
    private javax.swing.JTextField ultima_vez_compartida;
    private javax.swing.JTextField urlVideo;
    private javax.swing.JTextField userFB;
    private javax.swing.JTextField userGP;
    // End of variables declaration//GEN-END:variables
}
