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

public class publicaciones extends javax.swing.JInternalFrame {

    Connection connection = null;
    Statement statement = null;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo_cat_pub = new DefaultTableModel();
    DefaultTableModel modelo_cat = new DefaultTableModel();
    //VARIABLE QUE DETECTA SI SE CAMBIO EL TEXTO Y HACE UN UPDATE
    int cambiaTexto = 0;

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
    public publicaciones() {
        initComponents();
        //CARGAMOS EL MODELO DE LA TABLA PUBLICACIONES
        modelo.addColumn("id");
        modelo.addColumn("titulo");
        modelo.addColumn("url");
        modelo.addColumn("imagen");
        modelo.addColumn("ultima_vez_compartida");
        modelo.addColumn("numero_veces_compartido");
        modelo.addColumn("orden");
        modelo.addColumn("activo");
        //CARGAMOS EL MODELO DE LA TABLA CATEGORIAS DE LA PUBLICACION
        modelo_cat_pub.addColumn("id");
        modelo_cat_pub.addColumn("idCat");
        modelo_cat_pub.addColumn("idPub");
        modelo_cat_pub.addColumn("nombre");
        //CARGAMOS EL MODELO DE LA TABLA CATEGORIAS DISPONIBLES PARA ESA PUBLICACION
        modelo_cat.addColumn("idCat");
        modelo_cat.addColumn("nombre");
        //CARGAMOS LA TABLA DE PUBLICACIONES
        carga_tabla("");
        editar.setEnabled(false);
        borrar.setEnabled(false);
        agregar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        url = new javax.swing.JTextField();
        imagen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        titulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_cat_pub = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        quita_cat = new javax.swing.JButton();
        agrega_cat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_cat_exist = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        orden = new javax.swing.JTextField();
        ultima_vez_compartida = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        editar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        numero_veces_compartido = new javax.swing.JTextField();
        busca_titulo = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        busca_url = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        activo = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();

        setTitle("Publicaciones");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("URL*");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        url.setEnabled(false);
        getContentPane().add(url, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 870, -1));

        imagen.setEnabled(false);
        getContentPane().add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 870, -1));

        jLabel2.setText("Imagen");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        titulo.setEnabled(false);
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 870, -1));

        jLabel3.setText("Titulo*");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        getContentPane().add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Titulo", "URL", "Imagen", "Palabras_plus", "ultima_vez_compartida", "numero_veces_compartido", "Orden", "Activo"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1020, 220));

        tabla_cat_pub.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "idCat", "idPub", "nombre"
            }
        ));
        jScrollPane3.setViewportView(tabla_cat_pub);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 480, 150));

        jLabel5.setText("Categorias existentes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, -1, -1));

        jLabel6.setText("Categorias existentes del la publicacion");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        quita_cat.setText(">>");
        quita_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quita_catActionPerformed(evt);
            }
        });
        getContentPane().add(quita_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 580, -1, -1));

        agrega_cat.setText("<<");
        agrega_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agrega_catActionPerformed(evt);
            }
        });
        getContentPane().add(agrega_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 550, -1, -1));

        tabla_cat_exist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "idCat", "nombre"
            }
        ));
        jScrollPane2.setViewportView(tabla_cat_exist);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, 430, 240));

        jLabel7.setText("Buscar por url");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, -1, -1));

        orden.setEnabled(false);
        getContentPane().add(orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 420, -1));

        ultima_vez_compartida.setEnabled(false);
        getContentPane().add(ultima_vez_compartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 870, -1));

        jLabel8.setText("Ultima vez compartida");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, -1));

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        getContentPane().add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, -1, -1));

        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel9.setText("Numero veces compartido");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, -1));

        numero_veces_compartido.setEnabled(false);
        getContentPane().add(numero_veces_compartido, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 870, -1));

        busca_titulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_tituloKeyPressed(evt);
            }
        });
        getContentPane().add(busca_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 380, -1));

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        getContentPane().add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, -1, -1));

        busca_url.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_urlKeyPressed(evt);
            }
        });
        getContentPane().add(busca_url, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 340, -1));

        jLabel10.setText("Orden*");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel11.setText("Buscar por titulo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        activo.setSelected(true);
        activo.setText("Activo");
        activo.setEnabled(false);
        getContentPane().add(activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, -1, -1));

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 740, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //BOTON AGREGAR
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        //VARIABLE QUE GUARDA TODOS LOS ERRORES QUE SURJAN
        String mensajeError = "";
        if (url.getText().length() == 0) {
            mensajeError = mensajeError + "Campo URL es obligatorio\n";
        }
        if (titulo.getText().length() == 0) {
            mensajeError = mensajeError + "Campo Titulo es obligatorio\n";
        }
        //SI EL MENSAJE ERROR ESTA VACIO ES QUE NO HUBO NINGUN ERROR Y PROCEDE A INSERTAR LOS DATOS DE LA PUBLICACION
        int errorleng = mensajeError.length();
        if (mensajeError.length() == 0) {
            int Iactivo;
            if(activo.isSelected()) Iactivo=1; else Iactivo=0;
            agregar(url.getText(), imagen.getText(), titulo.getText(), ultima_vez_compartida.getText(), numero_veces_compartido.getText(), orden.getText(),Iactivo);
        } //SI EL EMNSAJE DE ERROR TIENE CONTENIDO MANDAMOS UN MENSAJE DE ERROR
        else {
            Object[] options = {mensajeError};
            JOptionPane.showMessageDialog(rootPane, options, "Error", 0);
        }
        //DESHABILITAMOS LOS TEXTFIELD PARA EVITAR QUE SE ESCRIBA
        habilitaText(false);
        //QUITAMOS EL TEXTO EN LOS TEXTFIELD
        borraText();
        agregar.setEnabled(false);
        borrar.setEnabled(false);
        editar.setEnabled(false);
        nuevo.setEnabled(true);
    }//GEN-LAST:event_agregarActionPerformed
    //BOTON BORRAR UNA PUBLICACION
    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        try {
            //SACAMOS EL ID A BORRAR
            int idBorrar = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            //BORRAMOS EL REGISTRO DE LA BD
            borrar(idBorrar);
            //ELIMINAMOS DE LA JTABLE EL REGISTRO SELECCIONADO DE LA JTABLE
            modelo.removeRow(tabla.getSelectedRow());
        } catch (Exception e) {
            Object[] options = {"No hay ningun registro seleccionado"};
            JOptionPane.showMessageDialog(rootPane, options, "Error", 0);
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        //COLOCAMOS LOS VALORES DE LA TABLA EN LOS INPUT TEXT
        asigna_tabla_publicaciones_input_text();
        int idPub = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        carga_tabla_cat_pub(idPub);
        carga_tabla_cat(idPub);
        agregar.setEnabled(false);
        editar.setEnabled(true);
        nuevo.setEnabled(true);
        borrar.setEnabled(true);
        habilitaText(true);
    }//GEN-LAST:event_tablaMouseClicked

    private void agrega_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agrega_catActionPerformed
        int idPub=0;
        for ( int registroPub : tabla.getSelectedRows() ) {
            idPub = Integer.parseInt(tabla.getModel().getValueAt(registroPub, 0).toString());
            for ( int registroCat : tabla_cat_exist.getSelectedRows() ) {
                try {
                    int idCat = Integer.parseInt(tabla_cat_exist.getValueAt(registroCat, 0).toString());
                    agrega_cat(idCat, idPub);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
        carga_tabla_cat_pub(idPub);
        carga_tabla_cat(idPub);
    }//GEN-LAST:event_agrega_catActionPerformed

    private void quita_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quita_catActionPerformed
        int idPub =0;
        for ( int registroPub : tabla.getSelectedRows() ) {
            for (int registroCat : tabla_cat_pub.getSelectedRows()) {
                try {
                    //SACAMOS EL ID DE CATEGORIA Y EL ID DE PUBLICACION DE ESTA PUBLICACION
                    int idCat = Integer.parseInt(tabla_cat_pub.getValueAt(registroCat, 1).toString());
                    int idPubCat = Integer.parseInt(tabla_cat_pub.getValueAt(registroCat, 2).toString());
                    idPub = Integer.parseInt(tabla.getModel().getValueAt(registroPub, 0).toString());
                    quita_cat(idCat, idPub);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
        //SACAMOS EL ID DE LA PUCLICACION DE LA PRIMER TABLA
        //int idPub = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        carga_tabla_cat_pub(idPub);
        carga_tabla_cat(idPub);
    }//GEN-LAST:event_quita_catActionPerformed
    //LIBERAR LA TECLA DE LA TABLA SE ACTUALIZAN LOS INPUTTEXT Y LAS CATEGORIAS
    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        //COLOCAMOS LOS VALORES DE LA TABLA EN LOS INPUT TEXT
        asigna_tabla_publicaciones_input_text();
        int idPub = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        carga_tabla_cat_pub(idPub);
        carga_tabla_cat(idPub);
        agregar.setEnabled(false);
        editar.setEnabled(true);
        nuevo.setEnabled(true);
        borrar.setEnabled(true);
        habilitaText(true);

    }//GEN-LAST:event_tablaKeyReleased
    //
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        //SACAMOS EL ID DE LA PUBLICACION DE LA TABLA
        int idPub = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        int selRow = tabla.getSelectedRow();
        int Iactivo;
        if(activo.isSelected()) Iactivo=1; else Iactivo=0;
        //PASAMOS LOS VALORES DE LOS INPUTSTEXT A LA FUNCION EDITAR
        editar(idPub, url.getText(), imagen.getText(), titulo.getText(),  ultima_vez_compartida.getText(), numero_veces_compartido.getText(), orden.getText(),Iactivo);
        modelo.setValueAt(tabla, selRow, 1);
        tabla.setValueAt(titulo.getText(), selRow, 1);
        tabla.setValueAt(url.getText(), selRow, 2);
        tabla.setValueAt(imagen.getText(), selRow, 3);
        tabla.setValueAt(ultima_vez_compartida.getText(), selRow, 4);
        tabla.setValueAt(numero_veces_compartido.getText(), selRow, 5);
        tabla.setValueAt(orden.getText(), selRow, 6);
        tabla.setValueAt(Iactivo, selRow, 7);
    }//GEN-LAST:event_editarActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        //COLOCAMOS TODOS LOS TEXTFIEL VACIOS
        habilitaText(true);
        borraText();
        //SACAMOS EL ULTIMO NUME DE ORDEN
        int ultimo=ultimoOrden();
        //LE SUMAMOS 1
        ultimo++;
        orden.setText(""+ultimo+"");
        orden.setEnabled(true);
        agregar.setEnabled(true);
        borrar.setEnabled(false);
        nuevo.setEnabled(false);

    }//GEN-LAST:event_nuevoActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        filtrar();
    }//GEN-LAST:event_BuscarActionPerformed

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
    /*
    *
    *FUNCIONES AGREGAR BORRAR EDITAR
    *
    */
    //HABILITA O DESHABILITA LOS TEXTOS
    public void habilitaText(boolean siNo){
        //COLOCAMOS TODOS LOS TEXTFIEL VACIOS
        titulo.setEnabled(siNo);
        url.setEnabled(siNo);
        imagen.setEnabled(siNo);
        ultima_vez_compartida.setEnabled(siNo);
        numero_veces_compartido.setEnabled(siNo);
        orden.setEnabled(siNo);
        activo.setEnabled(siNo);
    }
    public void borraText(){
        //COLOCAMOS TODOS LOS TEXTFIEL VACIOS
        titulo.setText("");
        url.setText("");
        imagen.setText("");
        ultima_vez_compartida.setText("");
        numero_veces_compartido.setText("");
    }
    
    
    //REGRESA EL ULTIMO NUMERO DEL ORDEN DE LAS PUBLICACIONES
    public int ultimoOrden(){
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS PUBLICACIONES
            String query = "SELECT MAX(orden) as ultimo FROM publicaciones; ";
            ResultSet rs = statement.executeQuery(query);
            return rs.getInt("ultimo");
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
        return 0;
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
            where = " where " + where;
        }
        carga_tabla(where);
    }

    //FUNCION QUE RESIBE EL ID A BORRAR
    public void borrar(int id) {
        String sql = "DELETE FROM publicaciones WHERE id='" + id + "';";
        //SI LA CONEXION ES EXITOSA
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL QUERY
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void quita_cat(int idCat, int idPub) {
        String sql = "delete from \"pub-cat\" WHERE \"pub-cat\".idCat='" + idCat + "' and \"pub-cat\".idPub='" + idPub + "';";
        //SI LA CONEXION ES EXITOSA
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL QUERY
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //AGREGA LA CATEGORIA SELECCIONADA EN LA TABLA DE DE CATEGORIAS DE LA PUBLICACION
    public void agrega_cat(int idCat, int idPub) {
        String sql = "insert into \"pub-cat\" (idCat,idPub) VALUES ('" + idCat + "','" + idPub + "');";
        //SI LA CONEXION ES EXITOSA
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL QUERY
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //EDITAMOS EN LA BD LA PUBLICACION
    public void editar(int id, String url, String imagen, String titulo, String ultima_vez_compartida, String numero_veces_compartido, String orden, int Iactivo) {
        String sql = "UPDATE publicaciones SET "
                + "url = '" + url + "',  "
                + "imagen = '" + imagen + "',  "
                + "titulo = '" + titulo + "',  "
                + "ultima_vez_compartida = '" + ultima_vez_compartida + "',  "
                + "numero_veces_compartido = '" + numero_veces_compartido + "',  "
                + "activo = '" + Iactivo + "',  "
                + "orden = '" + orden + "'  "
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
    public void agregar(String url, String imagen, String titulo, String ultima_vez_compartida, String numero_veces_compartido, String orden,int Iactivo) {
        if (numero_veces_compartido.length() == 0) {
            numero_veces_compartido = "0";
        }
        String sql = "INSERT INTO publicaciones "
                + "(    url,           imagen,          titulo,          ultima_vez_compartida,          numero_veces_compartido,          orden,          activo) values "
                + "('" + url + "','" + imagen + "','" + titulo + "','" + ultima_vez_compartida + "','" + numero_veces_compartido + "','" + orden + "','" + Iactivo + "')";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
            //BORRAR EL MODELO Y CARGARLO DE NUEVO
            carga_tabla("");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //CARGA PUBLICACIONES
    public void carga_tabla(String where) {
        String Dato[] = new String[8];
        modelo.setRowCount(0);
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
                    + "ultima_vez_compartida, "
                    + "numero_veces_compartido, "
                    + "activo, "
                    + "orden "
                    + "FROM publicaciones "
                    + where
                    + "order by orden";
            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0] = rs.getString("id");
                Dato[1] = rs.getString("titulo");
                Dato[2] = rs.getString("url");
                Dato[3] = rs.getString("imagen");
                Dato[4] = rs.getString("ultima_vez_compartida");
                Dato[5] = rs.getString("numero_veces_compartido");
                Dato[6] = rs.getString("orden");
                Dato[7] = rs.getString("activo");
                modelo.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla.setModel(modelo);
            //OCULTAMOS EL ID EN LA TABLA
            //tabla.getColumn("id").setMaxWidth(0);
            url.setText("");
            imagen.setText("");
            titulo.setText("");
            ultima_vez_compartida.setText("");
            numero_veces_compartido.setText("");
            orden.setText("");
            activo.setSelected(true);
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

    //CARGA CATEGORIAS DE LA PUBLICACION
    public void carga_tabla_cat_pub(int id_pub) {
        String Dato[] = new String[4];
        modelo_cat_pub.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS PUBLICACIONES
            String query = "SELECT \n"
                    + "\"pub-cat\".id,\n"
                    + "\"pub-cat\".idCat,\n"
                    + "\"pub-cat\".idPub,\n"
                    + "categoria.nombre,\n"
                    + "publicaciones.id \n"
                    + "FROM \n"
                    + "categoria \n"
                    + "INNER JOIN \"pub-cat\" ON \"pub-cat\".idCat = categoria.id \n"
                    + "INNER JOIN publicaciones ON \"pub-cat\".idPub = publicaciones.id \n"
                    + "where \n"
                    + "publicaciones.id='" + id_pub + "';";
            System.out.println(query);

            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0] = rs.getString("id");
                Dato[1] = rs.getString("idCat");
                Dato[2] = rs.getString("idPub");
                Dato[3] = rs.getString("nombre");
                modelo_cat_pub.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla_cat_pub.setModel(modelo_cat_pub);
            //OCULTAMOS EL ID EN LA TABLA
            /*
            tabla_cat_pub.getColumn("id").setMaxWidth(0);
            tabla_cat_pub.getColumn("idCat").setMaxWidth(0);
            tabla_cat_pub.getColumn("idPub").setMaxWidth(0);
             */
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

    //CARGA LA TABLA DE CATEGORIAS QUE NO TENGA ESTA PUBLICACION
    public void carga_tabla_cat(int id_pub) {
        String Dato[] = new String[3];
        modelo_cat.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS PUBLICACIONES
            String query = "SELECT \n"
                    + "id,\n"
                    + "nombre\n"
                    + " FROM categoria \n"
                    + "WHERE id not in (SELECT \"pub-cat\".idCat from \"pub-cat\" where \"pub-cat\".idPub='" + id_pub + "');";
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0] = rs.getString("id");
                Dato[1] = rs.getString("nombre");
                modelo_cat.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla_cat_exist.setModel(modelo_cat);
            //OCULTAMOS EL ID EN LA TABLA
            /*
            tabla_cat_exist.getColumn("idCat").setMaxWidth(0);
            tabla_cat_exist.getColumn("idPub").setMaxWidth(0);
             */
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

    //COLOCAMOS EL TEXTO DE LA TABLA EN CADA INPUT TEXT
    public void asigna_tabla_publicaciones_input_text() {
        try {
            //idPub=tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            titulo.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
            url.setText(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
            imagen.setText(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
            ultima_vez_compartida.setText(tabla.getValueAt(tabla.getSelectedRow(), 4).toString());
            numero_veces_compartido.setText(tabla.getValueAt(tabla.getSelectedRow(), 5).toString());
            orden.setText(tabla.getValueAt(tabla.getSelectedRow(), 6).toString());
            if("1".equals(tabla.getValueAt(tabla.getSelectedRow(), 7).toString())){
                activo.setSelected(true);
            }else{
                activo.setSelected(false);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JCheckBox activo;
    private javax.swing.JButton agrega_cat;
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField busca_titulo;
    private javax.swing.JTextField busca_url;
    private javax.swing.JButton editar;
    private javax.swing.JTextField imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JButton nuevo;
    private javax.swing.JTextField numero_veces_compartido;
    private javax.swing.JTextField orden;
    private javax.swing.JButton quita_cat;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_cat_exist;
    private javax.swing.JTable tabla_cat_pub;
    private javax.swing.JTextField titulo;
    private javax.swing.JTextField ultima_vez_compartida;
    private javax.swing.JTextField url;
    // End of variables declaration//GEN-END:variables
}
