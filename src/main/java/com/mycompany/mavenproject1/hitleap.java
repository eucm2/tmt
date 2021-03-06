package com.mycompany.mavenproject1;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

//INICIO CLASE
public class hitleap extends javax.swing.JInternalFrame {

    long rapido;
    long medio;
    long lento;
    long mlento;
    int hora_ini = 0;
    int hora_ini_variacion = 0;
    int hora_fin = 0;
    int hora_fin_variacion = 0;
    int cada_horas = 0;
    String dias_semana = "";
    WebDriver driver;
    Connection connection = null;
    Statement statement = null;
    String path_drive = "";
    String escribirCon = "";
    int modo_prueba=0;
    int tiempo_x2=0;
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
    public hitleap() {

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
            escribirCon = rs.getString("escribirCon");
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
            
            //ABRIMOS EL CONTRUCTOR Y CAMBIAMOS EL TIPO DE ENVIO DE TEXTO, POR SENKEY O POR JAVASCRIPT
            c.cControl(escribirCon);
            //CARGAMOS LOS DATOS DE CONFIGURACION COMO LA VELOCIDAD ENTRE ECCIONES Y LA URL DEL PATH DRIVER
            String queryAccesos = "SELECT "
                    + "id, "
                    + "user, "
                    + "password, "
                    + "redSocal "
                    + "FROM accesos;";

            ResultSet rsAccesos = statement.executeQuery(queryAccesos);
            //CICLO QUE LLENA TODO EL MODELO
            while (rsAccesos.next()) {
                String redSocal = rsAccesos.getString("redSocal");
                if (redSocal.equals("HL")) {
                    userKL.setText(rsAccesos.getString("user"));
                    passwordKL.setText(rsAccesos.getString("password"));
                    if (rsAccesos.getString("user").length() > 0 && rsAccesos.getString("password").length() > 0) {
                        guardar.setSelected(true);
                    }
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
        horaActual = new javax.swing.JLabel();
        passwordKL = new javax.swing.JPasswordField();
        guardar = new javax.swing.JCheckBox();
        activar = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        horaIni = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        horaFin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaHorarios = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tiempoFaltaEjecutar = new javax.swing.JLabel();
        lblHoraReiniciar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblModo = new javax.swing.JLabel();

        setTitle("Kingdomlikes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        btnGanarPuntos.setText("Ejecutar ahora");
        btnGanarPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGanarPuntosActionPerformed(evt);
            }
        });
        getContentPane().add(btnGanarPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, -1, -1));

        jLabel14.setText("User KL");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        userKL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userKLKeyPressed(evt);
            }
        });
        getContentPane().add(userKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 290, -1));

        horaActual.setText("Hora actual");
        getContentPane().add(horaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));
        getContentPane().add(passwordKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 300, -1));

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        getContentPane().add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, -1));

        activar.setText("Activar");
        activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activarActionPerformed(evt);
            }
        });
        getContentPane().add(activar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel15.setText("Hora actual.......................................");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        horaIni.setText("Hora Inicial");
        getContentPane().add(horaIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        jLabel17.setText("Hora Inicial......................................");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel20.setText("Hora Final.......................................");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        horaFin.setText("Hora Final");
        getContentPane().add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        listaHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Hora"
            }
        ));
        listaHorarios.setName("listaHorarios"); // NOI18N
        jScrollPane1.setViewportView(listaHorarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 400, 180));

        jLabel16.setText("Password KL");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jLabel21.setText("Segundos que faltan para ejeuitar");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        tiempoFaltaEjecutar.setText("Tiempo a ajecutar");
        getContentPane().add(tiempoFaltaEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, -1, -1));

        lblHoraReiniciar.setText("horaReiniciar");
        getContentPane().add(lblHoraReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 360, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 550, -1, -1));

        lblModo.setBackground(new java.awt.Color(102, 102, 255));
        lblModo.setText("modo");
        lblModo.setOpaque(true);
        getContentPane().add(lblModo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void crearPublicaciones() {
        //LISTA DE URL DE PUBLICACIONES
        String listaUrlPub = "";
        //LISTA DE LOS IDS DE PUBLICACIONES A ACTUALIZAR
        String ListIds = "";
        //LISTA DE PUBLICACIONES A ENVIAR VIA MAIL
        String listaPubMail = "";
        //CUENTA EL NUMERO DE PUBLICACIONES
        int contPublicaciones=0;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //CARGAMOS LOS DATOS DE CONFIGURACION COMO LA VELOCIDAD ENTRE ECCIONES Y LA URL DEL PATH DRIVER
            String queryPub = "SELECT id,url FROM publicaciones where pub_hitleap='0' LIMIT '3'; ";
            ResultSet rsPub = statement.executeQuery(queryPub);
            //CICLO QUE LLENA 3 PUBLICACIONES A METER EN HITLEAP
            while (rsPub.next()) {
                listaUrlPub = listaUrlPub + rsPub.getString("url") + "\\r/";
                ListIds = ListIds + "'" + rsPub.getString("id") + "'" + ",";
                listaPubMail = listaPubMail + rsPub.getString("url") + " </br> \n ";
                contPublicaciones++;
            }
            //SI YA NO HAY PUBLICACIONES
            if(contPublicaciones==0){
                //MANDAMOS UNA NOTIFICACION POR CORREO
                c.mandaMail("eucm2g@gmail.com","Ya no hay publicaciones para poner en hitleap","tmt","Ya no hay publicaciones para poner en hitleap");
                return;
            }
            //QUITAMOS LA ULTIMA LETRA (QUE ES UN RETURNCAR Y UN ,)
            listaUrlPub = listaUrlPub.substring(0, listaUrlPub.length() - 1);
            ListIds = ListIds.substring(0, ListIds.length() - 1);
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
        //INICIALIZAMOS EL NAVEGADOR
        c.inicializarWebdriver(path_drive);
        //COLOCAMOS LOS ACCESOS
        c.accedeHL(userKL.getText(), passwordKL.getText());
        //AGREGAMOS LOS VIDEOS A A HITLEAP
        boolean resultado = c.registraVideos(listaUrlPub);
        //SI ESOS VIDEOS SE AGREGARON EXITOSAMENTE ACTUALIZAMOS EN LA BD ESOS VIDEOS
        if (resultado == true) {
            c.mandaMail("eucm2g@gmail.com","Videos publicados","tmt","Se publicaron estos videos </br>"+listaPubMail+" ");
            //QUERY QUE ACTUALIZA MARCA COMO PUBLICADO EL VIDEO QUE SE ACABA DE PUBLICAR EN HITLEAP
            String sqlListaPub = " update publicaciones set pub_hitleap='1' where id IN (" + ListIds + "); ";
            try (Connection conn = this.connect();
                //ACTUALIZAMOS TODOS LOS VIDEOS QUE SE PUBLICARON
                PreparedStatement pstmt = conn.prepareStatement(sqlListaPub)) {
                //EJECUTAMOS EL COMANDO
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            c.mandaMail("eucm2g@gmail.com","Videos NO publicados","tmt fallo","NO se publicaron estos videos </br>"+listaPubMail+" ");
        }
        c.pausa(mlento);
        c.cerrarNavegador();

    }


    private void btnGanarPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGanarPuntosActionPerformed

        crearPublicaciones();

    }//GEN-LAST:event_btnGanarPuntosActionPerformed

    private void userKLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKLKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_userKLKeyPressed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        String errorFalta = "";
        if (userKL.getText().length() == 0) {
            errorFalta = errorFalta + "Falta username kingdomlikes\n";
        }
        if (passwordKL.getText().length() == 0) {
            errorFalta = errorFalta + "Falta password kingdomlikes\n";
        }
        if (guardar.isSelected()) {
            //SI FALTA USER O PASSWORD MUESTRA UN MENSAJE DE ERROR
            if (errorFalta.length() > 0) {
                JOptionPane.showMessageDialog(null, errorFalta);
                guardar.setSelected(false);
            } //SI NO HAY ERROR GUARDAMOS USER Y PASS DE FB
            else {
                guardarUserPass(userKL.getText(), passwordKL.getText(), "HL");
            }
        } else {
            guardarUserPass("", "", "HL");
        }
    }//GEN-LAST:event_guardarActionPerformed


    private void activarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarActionPerformed
        //CUANDO HACEMOS CLICK EN EL CHECKBOX DE REINICIAR CRONOMETRO Y ES MOD PRUEBA SE INICIALIZA EL CRONOMETRO PARA EJECUTAR
        reiniciarCronometro();
        if (modo_prueba == 0) {
            cronometroParaEjecutar();
        }
    }//GEN-LAST:event_activarActionPerformed

    
    


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

            Date horaReiniciar = null;
            DateFormat formatoFechaHora = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
            try {
                if (modo_prueba == 0) {
                    //GENERAMOS LA HORA DE INICIO DE EJECUCION OSEA CADA 24 HORAS
                    horaReiniciar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + reiniciarAlasHoras + ":" + reiniciarAlasMinutos + ":" + reiniciarAlasSegundos);
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
        Date horaEjecutar = null;
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
                    horaEjecutar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + hora + ":" + (minuto + (contadorHoras*2)) + ":59");
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
                        crearPublicaciones();
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

    //AGREGAMOS EN LA BD LA PUBLICACION
    public void guardarUserPass(String SuserKL, String SpasswordKL, String redSocal) {

        String sql = "UPDATE accesos SET "
                + "user = '" + SuserKL + "',  "
                + "password = '" + SpasswordKL + "',  "
                + "redSocal = '" + redSocal + "'  "
                + "WHERE redSocal = '" + redSocal + "';";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activar;
    private javax.swing.JButton btnGanarPuntos;
    private javax.swing.JCheckBox guardar;
    private javax.swing.JLabel horaActual;
    private javax.swing.JLabel horaFin;
    private javax.swing.JLabel horaIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHoraReiniciar;
    private javax.swing.JLabel lblModo;
    private javax.swing.JTable listaHorarios;
    private javax.swing.JPasswordField passwordKL;
    private javax.swing.JLabel tiempoFaltaEjecutar;
    private javax.swing.JTextField userKL;
    // End of variables declaration//GEN-END:variables
}
