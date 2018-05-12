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
        modelo.addColumn("nombre");
        //CARGAMOS LA TABLA DE PUBLICACIONES
        carga_tabla();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setTitle("Accesos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 640, -1));

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        getContentPane().add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "Titulo", "URL", "Imagen", "Palabras_plis"
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 740, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //BOTON AGREGAR
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        //VARIABLE QUE GUARDA TODOS LOS ERRORES QUE SURJAN
        String mensajeError="";
        if(nombre.getText().length()==0){
            mensajeError=mensajeError+"Campo Nombre es obligatorio\n";
        }
        //SI EL MENSAJE ERROR ESTA VACIO ES QUE NO HUBO NINGUN ERROR Y PROCEDE A INSERTAR LOS DATOS DE LA PUBLICACION
        int errorleng=mensajeError.length();
        if(mensajeError.length()==0){
            agregar(nombre.getText());
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
            String Snombre = tabla.getModel().getValueAt(row_index, 1).toString();
            this.editar(idPub,Snombre);
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
        String sql = "DELETE FROM categoria WHERE id='"+id+"';";
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
    public void editar(int id, String nombre) {
        String sql = "UPDATE categoria SET "
                + "nombre = '"+ nombre +"'  "
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
    public void agregar(String nombre) {
        String sql = "INSERT INTO categoria "
                + "(    nombre) values "
                + "('"+ nombre +"')";
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
        String Dato[]=new String[2];
        modelo.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS PUBLICACIONES
            String query = "SELECT "
                    + "id, "
                    + "nombre "
                    + "FROM categoria;";
            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0]=rs.getString("id");
                Dato[1]=rs.getString("nombre");
                modelo.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla.setModel(modelo);
            //OCULTAMOS EL ID EN LA TABLA
            //tabla.getColumn("id").setMaxWidth(0);
            nombre.setText("");
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
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
