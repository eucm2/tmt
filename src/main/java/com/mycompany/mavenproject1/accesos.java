package com.mycompany.mavenproject1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//INICIO CLASE
public class accesos extends javax.swing.JInternalFrame {
    Connection connection = null;
    Statement statement = null;
    DefaultTableModel modelo=new DefaultTableModel();
    //VARIABLE QUE DETECTA SI SE CAMBIO EL TEXTO Y HACE UN UPDATE
    int cambiaTexto=0;
    //REGISTRO DONDE SE CAMBIO EL TEXTO
    int row_index=0;
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
    public accesos() {
        initComponents();
        //CARGAMOS EL MODELO DE LA TABLA PUBLICACIONES
        modelo.addColumn("id");
        modelo.addColumn("user");
        modelo.addColumn("password");
        modelo.addColumn("redSocal");
        modelo.addColumn("activo");
        //CARGAMOS LA TABLA DE PUBLICACIONES
        carga_tabla();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        password = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        redSocal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        activo = new javax.swing.JTextField();

        setTitle("Accesos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("password");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));
        getContentPane().add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 230, -1));

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        getContentPane().add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaMouseEntered(evt);
            }
        });
        tabla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaPropertyChange(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 740, 250));

        jLabel2.setText("User");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 230, -1));

        jLabel3.setText("Social");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        getContentPane().add(redSocal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 230, -1));

        jLabel4.setText("activo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));
        getContentPane().add(activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 230, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //BOTON AGREGAR
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        //VARIABLE QUE GUARDA TODOS LOS ERRORES QUE SURJAN
        String mensajeError="";
        if(user.getText().length()==0){
            mensajeError=mensajeError+"Campo Nombre es obligatorio\n";
        }
        //SI EL MENSAJE ERROR ESTA VACIO ES QUE NO HUBO NINGUN ERROR Y PROCEDE A INSERTAR LOS DATOS DE LA PUBLICACION
        int errorleng=mensajeError.length();
        if(mensajeError.length()==0){
            agregar(user.getText(),password.getText(),redSocal.getText(),activo.getText());
        }
        //SI EL EMNSAJE DE ERROR TIENE CONTENIDO MANDAMOS UN MENSAJE DE ERROR
        else{
            Object[] options = {mensajeError};
            JOptionPane.showMessageDialog(rootPane, options,"Error",0);
        }
    }//GEN-LAST:event_agregarActionPerformed
    //BOTON BORRAR UNA PUBLICACION
    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        try{
            //SACAMOS EL ID A BORRAR
            int idBorrar= Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            //BORRAMOS EL REGISTRO DE LA BD
            borrar(idBorrar);
            //ELIMINAMOS DE LA JTABLE EL REGISTRO SELECCIONADO DE LA JTABLE
            modelo.removeRow(tabla.getSelectedRow());
        }catch(Exception e){
            Object[] options = {"No hay ningun registro seleccionado"};
            JOptionPane.showMessageDialog(rootPane, options,"Error",0);
        }
    }//GEN-LAST:event_borrarActionPerformed
    //EN CASO DE QUE SE CAMBIE EL TEXTO DE LA TABLA
    private void tablaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaPropertyChange
        //SI SE CAMBIA EL TEXTO
        if (tabla.isEditing()){
            //DETIENE EL EVENTO CAMBIAR TEXTO
            tabla.getCellEditor().stopCellEditing();
            //HABILITA LA BANDERA QUE SE CAMBIO EL TEXTO PARA QUE CUANDO SE CAMBIE EL ROW SE ACTUALICE
            cambiaTexto=1;
            //SE GUARDA EL NUMERO DEL REGISTRO QUE SE CAMBIO
            row_index=tabla.getSelectedRow();
        }

    }//GEN-LAST:event_tablaPropertyChange
    //EN CASO DE QUE SE CAMBIE EL TEXTO DE LA TABLA
    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        row_index=tabla.getSelectedRow();
        int idPub= Integer.parseInt(tabla.getModel().getValueAt(row_index, 0).toString());
        if(cambiaTexto==1){
            //TOMAMOS EL ID DEL REGISTRO QUE SE EDITO
            String Suser = tabla.getModel().getValueAt(row_index, 1).toString();
            String Spassword = tabla.getModel().getValueAt(row_index, 2).toString();
            String SredSocal = tabla.getModel().getValueAt(row_index, 3).toString();
            String Sactivo = tabla.getModel().getValueAt(row_index, 4).toString();
            this.editar(idPub,Suser,Spassword,SredSocal,Sactivo);
            //UNA VEZ QUE SE ACTUALIZARON LOS REGISTROS REGREAMOS LA BANDERA A 0 PARA QUE NO SE ACTUALICEN HASTA CAMBIAR EL TEXTO
            cambiaTexto=0;
        }
    }//GEN-LAST:event_tablaKeyPressed

    private void tablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseEntered
        if(cambiaTexto==1){
            //UNA VEZ QUE SE ACTUALIZARON LOS REGISTROS REGREAMOS LA BANDERA A 0 PARA QUE NO SE ACTUALICEN HASTA CAMBIAR EL TEXTO
            cambiaTexto=0;
        }
    }//GEN-LAST:event_tablaMouseEntered

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        row_index=tabla.getSelectedRow();
        int idPub= Integer.parseInt(tabla.getModel().getValueAt(row_index, 0).toString());
    }//GEN-LAST:event_tablaMouseClicked
    /*
    *
    *FUNCIONES AGREGAR BORRAR EDITAR
    *
    */
    //FUNCION QUE RESIBE EL ID A BORRAR
    public void borrar(int id) {
        String sql = "DELETE FROM accesos WHERE id='"+id+"';";
        //SI LA CONEXION ES EXITOSA
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL QUERY
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //FUNCION EDITAR
    public void editar(int id, String user, String password, String redSocal, String activo) {
        String sql = "UPDATE accesos SET "
                + "user = '"+ user +"',  "
                + "password = '"+ password +"',  "
                + "redSocal = '"+ redSocal +"',  "
                + "activo = '"+ activo +"'  "
                + "WHERE id = "+id;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //FUNCION AGREGAR
    public void agregar(String user,String password,String redSocal,String activo) {
        String sql = "INSERT INTO accesos "
                + "(    user,       password      ,redSocal       ,    activo) values "
                + "('"+ user +"','"+ password +"','"+ redSocal +"','"+ activo +"')";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
            //BORRAR EL MODELO Y CARGARLO DE NUEVO
            carga_tabla();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //CARGA LA TABLA CATEGORIAS
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
                    + "FROM accesos;";
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
            tabla.setModel(modelo);
            //OCULTAMOS EL ID EN LA TABLA
            //tabla.getColumn("id").setMaxWidth(0);
            user.setText("");
            password.setText("");
            redSocal.setText("");
            activo.setText("");
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
    private javax.swing.JTextField activo;
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField password;
    private javax.swing.JTextField redSocal;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
