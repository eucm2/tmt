package com.mycompany.mavenproject1;

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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Clock;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    + "escribirCon "
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

        setTitle("Kingdomlikes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        btnGanarPuntos.setText("Ejecutar ahora");
        btnGanarPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGanarPuntosActionPerformed(evt);
            }
        });
        getContentPane().add(btnGanarPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, -1, -1));

        jLabel14.setText("User KL");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        userKL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userKLKeyPressed(evt);
            }
        });
        getContentPane().add(userKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 290, -1));

        horaActual.setText("Hora actual");
        getContentPane().add(horaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));
        getContentPane().add(passwordKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 300, -1));

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        getContentPane().add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, -1));

        activar.setText("Activar");
        activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activarActionPerformed(evt);
            }
        });
        getContentPane().add(activar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel15.setText("Hora actual.......................................");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        horaIni.setText("Hora Inicial");
        getContentPane().add(horaIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, -1));

        jLabel17.setText("Hora Inicial......................................");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel20.setText("Hora Final.......................................");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        horaFin.setText("Hora Final");
        getContentPane().add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        listaHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Hora"
            }
        ));
        listaHorarios.setName("listaHorarios"); // NOI18N
        jScrollPane1.setViewportView(listaHorarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 400, 180));

        jLabel16.setText("Password KL");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel21.setText("Segundos que faltan para ejeuitar");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        tiempoFaltaEjecutar.setText("Tiempo a ajecutar");
        getContentPane().add(tiempoFaltaEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        lblHoraReiniciar.setText("horaReiniciar");
        getContentPane().add(lblHoraReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 360, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void crearPublicaciones() {

        String listaPub = "";
        String ListIds = "";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //CARGAMOS LOS DATOS DE CONFIGURACION COMO LA VELOCIDAD ENTRE ECCIONES Y LA URL DEL PATH DRIVER
            String queryPub = "SELECT id,url FROM publicaciones where pub_hitleap='0' LIMIT '3'; ";
            ResultSet rsPub = statement.executeQuery(queryPub);
            //CICLO QUE LLENA TODO EL MODELO
            while (rsPub.next()) {
                listaPub = listaPub + rsPub.getString("url") + Keys.ENTER;
                ListIds = ListIds + "'" + rsPub.getString("id") + "'" + ",";
            }
            listaPub = listaPub.substring(0, listaPub.length() - 1);
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
        boolean resultado = c.registraVideos(listaPub);
        //SI ESOS VIDEOS SE AGREGARON EXITOSAMENTE ACTUALIZAMOS EN LA BD ESOS VIDEOS
        if (resultado == true) {
            String sqlListaPub = " update publicaciones set pub_hitleap='1' where id IN (" + ListIds + "); ";
            try (Connection conn = this.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sqlListaPub)) {
                //EJECUTAMOS EL COMANDO
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
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
        reiniciarCronometro();
    }//GEN-LAST:event_activarActionPerformed
    //REINICIA CRONMETRO CADA 24HORAS PARA QUE LA HORA DEL DIA SE LIMPIE
    public void reiniciarCronometro() {
        if (activar.isSelected()) {
            boolean modoPrueba = false;
            
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
                if (modoPrueba == false) {
                    //GENERAMOS LA HORA DE INICIO DE EJECUCION OSEA CADA 24 HORAS
                    horaReiniciar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + reiniciarAlasHoras + ":" + reiniciarAlasMinutos + ":" + reiniciarAlasSegundos);
                    cronometroParaEjecutar();
                }
                if (modoPrueba == true) {
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

        //SI MODO PRUEB ES TRUE EN LUGAR DE HACER EL CICLO CADA HORA LO HACE CADA MINUTO PARA VER COMO FUNCIONA EN TIEMPO REAL
        boolean modoPrueba = false;
        int minuto = Calendar.getInstance().get(Calendar.MINUTE);
        int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
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
        DefaultTableModel model = (DefaultTableModel) listaHorarios.getModel();
        //CONTAMOS LA CANTIDAD DE REGISTROS QUE TIENE LA TLABLA
        int rowCount = model.getRowCount();
        //BORRAMOS TODOS LOS REGISTROS DE LA TABLA
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        //CONTADOR DE HORAS QUE PASAN ENTRE EL INICIO Y EL FINAL
        int contadorHoras = 0;
        Date horaEjecutar = null;
        //BUCLE QUE LLENA LA TABLA CON LAS HORAS ENTRE EL INICIO Y EL FINAL
        for (int i = hora_ini_res; i <= hora_fin_res; i = i + cada_horas) {
            try {
                if (modoPrueba == false) {
                    //GENERAMOS LA HORA DE INICIO DE EJECUCION
                    horaEjecutar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + i + ":" + calHoraIni.get(Calendar.MINUTE) + ":59");
                }
                if (modoPrueba == true) {
                    horaEjecutar = formatoFechaHora.parse(ano + "/" + mes + "/" + dia + " " + hora + ":" + (minuto + i) + ":59");
                }
                //INICIALIZAMOS EL CALENDARIO
                calHorasEjecutar[contadorHoras] = Calendar.getInstance();
                //LE ASIGNAMOS AL CALENDARIO LA HORA DE INICIO DE JECUCION
                calHorasEjecutar[contadorHoras].setTime(horaEjecutar);
            } catch (ParseException ex) {
                Logger.getLogger(hitleap.class.getName()).log(Level.SEVERE, null, ex);
            }
            //ESCRIBIMOS EN LA TABLA LAS HORAS EN LAS QUE SE VA EJECUTAR
            model.addRow(new Object[]{"" + (contadorHoras + 1), calHorasEjecutar[contadorHoras].get(Calendar.YEAR) + " " + calHorasEjecutar[contadorHoras].get(Calendar.MONTH) + " " + calHorasEjecutar[contadorHoras].get(Calendar.DAY_OF_MONTH) + " " + calHorasEjecutar[contadorHoras].get(Calendar.HOUR_OF_DAY) + ":" + calHorasEjecutar[contadorHoras].get(Calendar.MINUTE) + ":" + calHorasEjecutar[contadorHoras].get(Calendar.SECOND)});
            contadorHoras = contadorHoras + 1;
        }
        //CREAMOS ESTA VARIABLE PARA PODER METERLA DENDRO DEL RELOJ
        final int contadorHorasFinal = contadorHoras;
        //INICIALIZAMOS EL TEMPORALIZADOR
        final Timer timer = new Timer();
        //SI EL CHECK ESTA ACTIVO INICIA EL TEMPORALIZADOR

        TimerTask task = new TimerTask() {
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
                    if (segundosFaltanParaEjecutar > 0 && flagSoloElPrimero == false) {
                        //MOSTRAMOS EN LABEL EL TIEMPO QUE FALTA PARA EJECUTARSE EL SIGUIENTE
                        tiempoFaltaEjecutar.setText("" + segundosFaltanParaEjecutar);
                        //COMO YA ENTRO ESTE YA ENTRA NINGUN OTRO
                        flagSoloElPrimero = true;
                    }
                    //SI YA ESTAMOS EN EL MINUTO 0 EJECUTAMOS LA PUBLICACION
                    if (segundosFaltanParaEjecutar >= 0 && segundosFaltanParaEjecutar <= 10) {
                        crearPublicaciones();
                        c.pausa(1000 * 35);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);

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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHoraReiniciar;
    private javax.swing.JTable listaHorarios;
    private javax.swing.JPasswordField passwordKL;
    private javax.swing.JLabel tiempoFaltaEjecutar;
    private javax.swing.JTextField userKL;
    // End of variables declaration//GEN-END:variables
}
