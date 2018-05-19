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
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rapido = new javax.swing.JTextField();
        lento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        medio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        mlento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pathDrive = new javax.swing.JTextField();
        seleccionarImagen = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        max_king = new javax.swing.JTextField();
        min_king = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        hora_fin_variacion = new javax.swing.JTextField();
        hora_ini = new javax.swing.JTextField();
        hora_ini_variacion = new javax.swing.JTextField();
        hora_fin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cada_horas = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tiempo_x2 = new javax.swing.JCheckBox();
        modo_prueba = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        dias_semana = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        escribirCon = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        hora_ini_rs = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        hora_ini_variacion_rs = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cada_horas_rs = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        escribirCon_rs = new javax.swing.JList<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        hora_fin_rs = new javax.swing.JTextField();
        hora_fin_variacion_rs = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        dias_semana_rs = new javax.swing.JList<>();
        jLabel23 = new javax.swing.JLabel();
        modo_prueba_rs = new javax.swing.JCheckBox();
        tiempo_x2_rs = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        cantidad_comparir_fb = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cantidad_comparir_gp = new javax.swing.JTextField();

        setTitle("Configuracion General");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        getContentPane().add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, -1, -1));

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 580, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Rapido");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel5.setText("Lento");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        jPanel1.add(rapido, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 140, -1));
        jPanel1.add(lento, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 140, -1));

        jLabel4.setText("Medio");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));
        jPanel1.add(medio, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 140, -1));

        jLabel6.setText("Muy lento");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));
        jPanel1.add(mlento, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 140, -1));

        jLabel1.setText("Velocidad en milisegundos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel7.setText("Path drive chromedrive");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel1.add(pathDrive, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 510, -1));

        seleccionarImagen.setText("Seleccionar chromedriver");
        seleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarImagenActionPerformed(evt);
            }
        });
        jPanel1.add(seleccionarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jTabbedPane4.addTab("General", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Maximo");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel9.setText("Minimo");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        jPanel2.add(max_king, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 140, -1));
        jPanel2.add(min_king, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 140, -1));

        jTabbedPane4.addTab("Kingdomlikes", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Dias de la semana");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        jLabel11.setText("Hora final variacion");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        jLabel13.setText("Hora inicial");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel12.setText("Hora inicial variacion");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel3.add(hora_fin_variacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 110, -1));
        jPanel3.add(hora_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 110, -1));
        jPanel3.add(hora_ini_variacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 110, -1));
        jPanel3.add(hora_fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 110, -1));

        jLabel14.setText("Cada hora");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        jPanel3.add(cada_horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 110, -1));

        jLabel15.setText("Escribir con");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        tiempo_x2.setText("Tiempo x 2");
        jPanel3.add(tiempo_x2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        modo_prueba.setText("Modo prueba");
        jPanel3.add(modo_prueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        dias_semana.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(dias_semana);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 110, -1));

        jLabel16.setText("Hora final");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        escribirCon.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "javascript", "sendkeys" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(escribirCon);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 110, 50));

        jTabbedPane4.addTab("Hitleap", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setText("Hora inicial....................");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel4.add(hora_ini_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 110, -1));

        jLabel18.setText("Hora inicial variacion.......");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel4.add(hora_ini_variacion_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 110, -1));

        jLabel19.setText("Cada hora......................");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        jPanel4.add(cada_horas_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 110, -1));

        jLabel20.setText("Escribir con....................");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        escribirCon_rs.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "javascript", "sendkeys" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(escribirCon_rs);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 110, 50));

        jLabel21.setText("Hora final................");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jLabel22.setText("Hora final variacion...");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));
        jPanel4.add(hora_fin_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 110, -1));
        jPanel4.add(hora_fin_variacion_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 110, -1));

        dias_semana_rs.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(dias_semana_rs);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 110, -1));

        jLabel23.setText("Dias de la semana...");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        modo_prueba_rs.setText("Modo prueba");
        jPanel4.add(modo_prueba_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        tiempo_x2_rs.setText("Tiempo x 2");
        jPanel4.add(tiempo_x2_rs, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        jLabel24.setText("Cantidad a compartir fb...");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        jPanel4.add(cantidad_comparir_fb, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 110, -1));

        jLabel25.setText("Cantidad a compartir gp...");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));
        jPanel4.add(cantidad_comparir_gp, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 110, -1));

        jTabbedPane4.addTab("Redes sociales", jPanel4);

        getContentPane().add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 630, 310));

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
                    + "mlento,"
                    + "min_king,"
                    + "max_king,"
                    + "hora_ini,"
                    + "hora_ini_variacion,"
                    + "hora_fin,"
                    + "hora_fin_variacion,"
                    + "cada_horas,"
                    + "dias_semana,"
                    + "escribirCon,"
                    + "modo_prueba,"
                    + "tiempo_x2, "
                    + "hora_ini_rs,"
                    + "hora_ini_variacion_rs,"
                    + "hora_fin_rs,"
                    + "hora_fin_variacion_rs,"
                    + "cada_horas_rs,"
                    + "dias_semana_rs,"
                    + "escribirCon_rs,"
                    + "modo_prueba_rs,"
                    + "tiempo_x2_rs,"
                    + "cantidad_comparir_fb,"
                    + "cantidad_comparir_gp "
                    + "FROM configuracion";
            ResultSet rs = statement.executeQuery(query);
            pathDrive.setText(rs.getString("path_drive"));
            rapido.setText(rs.getString("rapido"));
            medio.setText(rs.getString("medio"));
            lento.setText(rs.getString("lento"));
            mlento.setText(rs.getString("mlento"));
            min_king.setText(rs.getString("min_king"));
            max_king.setText(rs.getString("max_king"));
            hora_ini.setText(rs.getString("hora_ini"));
            hora_ini_variacion.setText(rs.getString("hora_ini_variacion"));
            hora_fin.setText(rs.getString("hora_fin"));
            hora_fin_variacion.setText(rs.getString("hora_fin_variacion"));
            cada_horas.setText(rs.getString("cada_horas"));
            escribirCon.setSelectedValue(rs.getString("escribirCon"), false);
            cantidad_comparir_fb.setText(rs.getString("cantidad_comparir_fb"));
            cantidad_comparir_gp.setText(rs.getString("cantidad_comparir_gp"));
            //SI ES 1 ACTIVAMOS EL CHECK DE MODO PRUEBA
            if( Integer.parseInt(rs.getString("modo_prueba"))==1){
                modo_prueba.setSelected(true);
            }
            else{
                modo_prueba.setSelected(false);
            }
            //SI ES 1 ACTIVAMOS EL CHECK DE EL DOBLE DE VELOCIDAD
            if( Integer.parseInt(rs.getString("tiempo_x2"))==1){
                tiempo_x2.setSelected(true);
            }
            else{
                tiempo_x2.setSelected(false);
            }
            //SI EL CAMPO DE DIAS DE LA SEMANA TIENE TEXTO SE COLOCAN LOS VALORES EN EL ELEMENTO
            if(rs.getString("dias_semana").length()>0){
                //DEPENDIENDO DE LOS NUMERO EN LA COLUMNA SE SELECCIONA LOS DIAS DE LA SEMANA
                String[] array_dias_semana = rs.getString("dias_semana").split(",");
                int[] dias = new int[array_dias_semana.length];
                for (int ciclo = 0; ciclo < array_dias_semana.length; ciclo++) {
                    dias[ciclo]= Integer.parseInt(array_dias_semana[ciclo]);
                }
                dias_semana.setSelectedIndices(dias);
            }
            
            hora_ini_rs.setText(rs.getString("hora_ini_rs"));
            hora_ini_variacion_rs.setText(rs.getString("hora_ini_variacion_rs"));
            hora_fin_rs.setText(rs.getString("hora_fin_rs"));
            hora_fin_variacion_rs.setText(rs.getString("hora_fin_variacion_rs"));
            cada_horas_rs.setText(rs.getString("cada_horas_rs"));
            escribirCon_rs.setSelectedValue(rs.getString("escribirCon_rs"), false);
            //SI ES 1 ACTIVAMOS EL CHECK DE MODO PRUEBA
            if( Integer.parseInt(rs.getString("modo_prueba_rs"))==1){
                modo_prueba_rs.setSelected(true);
            }
            else{
                modo_prueba_rs.setSelected(false);
            }
            //SI ES 1 ACTIVAMOS EL CHECK DE EL DOBLE DE VELOCIDAD
            if( Integer.parseInt(rs.getString("tiempo_x2_rs"))==1){
                tiempo_x2_rs.setSelected(true);
            }
            else{
                tiempo_x2_rs.setSelected(false);
            }
            //SI EL CAMPO DE DIAS DE LA SEMANA TIENE TEXTO SE COLOCAN LOS VALORES EN EL ELEMENTO
            if(rs.getString("dias_semana_rs").length()>0){
                //DEPENDIENDO DE LOS NUMERO EN LA COLUMNA SE SELECCIONA LOS DIAS DE LA SEMANA
                String[] array_dias_semana_rs = rs.getString("dias_semana_rs").split(",");
                int[] dias_rs = new int[array_dias_semana_rs.length];
                for (int ciclo_rs = 0; ciclo_rs < array_dias_semana_rs.length; ciclo_rs++) {
                    dias_rs[ciclo_rs]= Integer.parseInt(array_dias_semana_rs[ciclo_rs]);
                }
                dias_semana_rs.setSelectedIndices(dias_rs);
            }

            
            
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
        int Imodo_prueba=0;
        int Itiempo_x2=0;
        String Sdias_semana="";
        if(modo_prueba.isSelected()==true){
            Imodo_prueba=1;
        }else{
            Imodo_prueba=0;
        }
        if(tiempo_x2.isSelected()==true){
            Itiempo_x2=1;
        }else{
            Itiempo_x2=0;
        }
        int []Idias_semana=dias_semana.getSelectedIndices();
        for(int ciclo=0;ciclo<Idias_semana.length;ciclo++){
            if(ciclo==0){
                Sdias_semana=String.valueOf(Idias_semana[ciclo])+"";
            }else if(ciclo>0){
                Sdias_semana=Sdias_semana+","+String.valueOf(Idias_semana[ciclo])+"";
            }
            
        }
        
        int Imodo_prueba_rs=0;
        int Itiempo_x2_rs=0;
        String Sdias_semana_rs="";
        if(modo_prueba_rs.isSelected()==true){
            Imodo_prueba_rs=1;
        }else{
            Imodo_prueba_rs=0;
        }
        if(tiempo_x2_rs.isSelected()==true){
            Itiempo_x2_rs=1;
        }else{
            Itiempo_x2_rs=0;
        }
        int []Idias_semana_rs=dias_semana_rs.getSelectedIndices();
        for(int ciclo_rs=0;ciclo_rs<Idias_semana_rs.length;ciclo_rs++){
            if(ciclo_rs==0){
                Sdias_semana_rs=String.valueOf(Idias_semana_rs[ciclo_rs])+"";
            }else if(ciclo_rs>0){
                Sdias_semana_rs=Sdias_semana_rs+","+String.valueOf(Idias_semana_rs[ciclo_rs])+"";
            }
            
        }
        
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            String Sbrowser_max;
            //GUARDAMOS LOS DATOS DE LA COMPRA
            String query = "UPDATE configuracion SET \n"
                    + "path_drive='" + pathDrive.getText() + "',\n "
                    + "rapido='" + rapido.getText() + "',\n"
                    + "medio='" + medio.getText() + "',\n"
                    + "lento='" + lento.getText() + "',\n"
                    + "mlento='" + mlento.getText() + "',\n"
                    + "min_king='"+min_king.getText()+"',\n"
                    + "max_king='"+max_king.getText()+"',\n"
                    + "hora_ini='"+hora_ini.getText()+"',\n"
                    + "hora_ini_variacion='"+hora_ini_variacion.getText()+"',\n"
                    + "hora_fin='"+hora_fin.getText()+"',\n"
                    + "hora_fin_variacion='"+hora_fin_variacion.getText()+"',\n"
                    + "cada_horas='"+cada_horas.getText()+"',"
                    + "escribirCon='"+escribirCon.getSelectedValue()+"',\n"
                    + "modo_prueba='"+Imodo_prueba+"',\n"
                    + "tiempo_x2='"+Itiempo_x2+"',\n"
                    + "dias_semana='"+Sdias_semana+"',\n"
                    + "hora_ini_rs='"+hora_ini_rs.getText()+"',\n"
                    + "hora_ini_variacion_rs='"+hora_ini_variacion_rs.getText()+"',\n"
                    + "hora_fin_rs='"+hora_fin_rs.getText()+"',\n"
                    + "hora_fin_variacion_rs='"+hora_fin_variacion_rs.getText()+"',\n"
                    + "cada_horas_rs='"+cada_horas_rs.getText()+"',\n"
                    + "escribirCon_rs='"+escribirCon_rs.getSelectedValue()+"',\n"
                    + "modo_prueba_rs='"+Imodo_prueba_rs+"',\n"
                    + "tiempo_x2_rs='"+Itiempo_x2_rs+"',\n"
                    + "dias_semana_rs='"+Sdias_semana_rs+"',\n"
                    + "cantidad_comparir_fb='"+cantidad_comparir_fb.getText()+"',\n"
                    + "cantidad_comparir_gp='"+cantidad_comparir_gp.getText()+"'\n"
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
    private javax.swing.JTextField cada_horas;
    private javax.swing.JTextField cada_horas_rs;
    private javax.swing.JTextField cantidad_comparir_fb;
    private javax.swing.JTextField cantidad_comparir_gp;
    private javax.swing.JList<String> dias_semana;
    private javax.swing.JList<String> dias_semana_rs;
    private javax.swing.JList<String> escribirCon;
    private javax.swing.JList<String> escribirCon_rs;
    private javax.swing.JButton guardar;
    private javax.swing.JTextField hora_fin;
    private javax.swing.JTextField hora_fin_rs;
    private javax.swing.JTextField hora_fin_variacion;
    private javax.swing.JTextField hora_fin_variacion_rs;
    private javax.swing.JTextField hora_ini;
    private javax.swing.JTextField hora_ini_rs;
    private javax.swing.JTextField hora_ini_variacion;
    private javax.swing.JTextField hora_ini_variacion_rs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTextField lento;
    private javax.swing.JTextField max_king;
    private javax.swing.JTextField medio;
    private javax.swing.JTextField min_king;
    private javax.swing.JTextField mlento;
    private javax.swing.JCheckBox modo_prueba;
    private javax.swing.JCheckBox modo_prueba_rs;
    private javax.swing.JTextField pathDrive;
    private javax.swing.JTextField rapido;
    private javax.swing.JButton seleccionarImagen;
    private javax.swing.JCheckBox tiempo_x2;
    private javax.swing.JCheckBox tiempo_x2_rs;
    // End of variables declaration//GEN-END:variables
}
