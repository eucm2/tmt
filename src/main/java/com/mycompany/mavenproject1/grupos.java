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
public class grupos extends javax.swing.JInternalFrame {
    Connection connection = null;
    Statement statement = null;
    DefaultTableModel modelo_grupo=new DefaultTableModel();
    DefaultTableModel modelo_cat_grup=new DefaultTableModel();
    DefaultTableModel modelo_cat=new DefaultTableModel();
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
    public grupos() {
        initComponents();
        //CARGAMOS EL MODELO DE LA TABLA GRUPOS
        modelo_grupo.addColumn("id");
        modelo_grupo.addColumn("nombre");
        modelo_grupo.addColumn("url");
        modelo_grupo.addColumn("palabras_plus");
        modelo_grupo.addColumn("tipo");
        modelo_grupo.addColumn("activo");
        //CARGAMOS EL MODELO DE LA TABLA CATEGORIAS DE LA PUBLICACION
        modelo_cat_grup.addColumn("id");
        modelo_cat_grup.addColumn("idCat");
        modelo_cat_grup.addColumn("idGrup");
        modelo_cat_grup.addColumn("nombre");
        //CARGAMOS EL MODELO DE LA TABLA CATEGORIAS DISPONIBLES PARA ESA PUBLICACION
        modelo_cat.addColumn("idCat");
        modelo_cat.addColumn("nombre");
        //CARGAMOS LA TABLA DE GRUPOS
        carga_tabla();
        editar.setEnabled(false);
        borrar.setEnabled(false);
        agregar.setEnabled(false);

        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        url = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_cat_grup = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        quita_cat = new javax.swing.JButton();
        agrega_cat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_cat_exist = new javax.swing.JTable();
        activo = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        palabras_plus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tipo = new javax.swing.JList<>();
        nuevo = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setTitle("Grupos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("URL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));
        getContentPane().add(url, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 640, -1));
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 640, -1));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel3.setText("Activo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        getContentPane().add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nombre", "URL", "palabras_plus", "tipo", "Activo"
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 740, 250));

        tabla_cat_grup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "idCat", "idGrup", "nombre"
            }
        ));
        jScrollPane3.setViewportView(tabla_cat_grup);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 300, 240));

        jLabel5.setText("Categorias existentes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, -1, -1));

        jLabel6.setText("Categorias existentes del la publicacion");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        quita_cat.setText(">>");
        quita_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quita_catActionPerformed(evt);
            }
        });
        getContentPane().add(quita_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 600, -1, -1));

        agrega_cat.setText("<<");
        agrega_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agrega_catActionPerformed(evt);
            }
        });
        getContentPane().add(agrega_cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, -1, -1));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 510, 270, 250));

        activo.setSelected(true);
        activo.setText("Activo");
        getContentPane().add(activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel4.setText("Palabras plus");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
        getContentPane().add(palabras_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 640, -1));

        jLabel7.setText("Tipo");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        tipo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "FB", "GP" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(tipo);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 200, 40));

        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        getContentPane().add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, -1, -1));

        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 770, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //BOTON AGREGAR
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        //VARIABLE QUE DETECTA SI EL GRUPO ESTA ACTIVO osea si se le van a enviar GRUPOS
        int Iactivo=0;
        //VARIABLE QUE GUARDA TODOS LOS ERRORES QUE SURJAN
        String mensajeError="";
        if(url.getText().length()==0){
            mensajeError=mensajeError+"Campo URL es obligatorio\n";
        }
        if(nombre.getText().length()==0){
            mensajeError=mensajeError+"Campo Nombre es obligatorio\n";
        }
        //SI EL CHECK ACTIVO ESTA CHECK LA VARIABLE Iactivo ES 1 DE LO CONTRA ES 0
        if (activo.isSelected()) {
            Iactivo = 1;
        } else {
            Iactivo = 0;
        }        
        //SI EL MENSAJE ERROR ESTA VACIO ES QUE NO HUBO NINGUN ERROR Y PROCEDE A INSERTAR LOS DATOS DE LA PUBLICACION
        int errorleng=mensajeError.length();
        if(mensajeError.length()==0){
            
            agregar(nombre.getText(),url.getText(),palabras_plus.getText(),tipo.getSelectedValue(),Iactivo);
        }
        //SI EL EMNSAJE DE ERROR TIENE CONTENIDO MANDAMOS UN MENSAJE DE ERROR
        else{
            Object[] options = {mensajeError};
            JOptionPane.showMessageDialog(rootPane, options,"Error",0);
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
        try{
            //SACAMOS EL ID A BORRAR
            int idBorrar= Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            //BORRAMOS EL REGISTRO DE LA BD
            borrar(idBorrar);
            //ELIMINAMOS DE LA JTABLE EL REGISTRO SELECCIONADO DE LA JTABLE
            modelo_grupo.removeRow(tabla.getSelectedRow());
        }catch(Exception e){
            Object[] options = {"No hay ningun registro seleccionado"};
            JOptionPane.showMessageDialog(rootPane, options,"Error",0);
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        
        //COLOCAMOS LOS VALORES DE LA TABLA EN LOS INPUT TEXT
        asigna_tabla_grupo_input_text();
        int idGrup= Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        carga_tabla_cat_grup(idGrup);
        carga_tabla_cat(idGrup);
        
        agregar.setEnabled(false);
        editar.setEnabled(true);
        nuevo.setEnabled(true);
        borrar.setEnabled(true);
        habilitaText(true);

        
    }//GEN-LAST:event_tablaMouseClicked

    private void agrega_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agrega_catActionPerformed
        try{
            //SACAMOS EL ID DE CATEGORIA Y EL ID DE PUBLICACION DE ESTA PUBLICACION
            int idCat=Integer.parseInt(tabla_cat_exist.getValueAt(tabla_cat_exist.getSelectedRow(), 0).toString());
            int idGrup= Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
            agrega_cat(idCat,idGrup);
            carga_tabla_cat_grup(idGrup);
            carga_tabla_cat(idGrup);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }//GEN-LAST:event_agrega_catActionPerformed

    private void quita_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quita_catActionPerformed
        try{
            //SACAMOS EL ID DE CATEGORIA Y EL ID DE PUBLICACION DE ESTA PUBLICACION
            int idCat=Integer.parseInt(tabla_cat_grup.getValueAt(tabla_cat_grup.getSelectedRow(), 1).toString());
            int idGrupCat=Integer.parseInt(tabla_cat_grup.getValueAt(tabla_cat_grup.getSelectedRow(), 2).toString());
            quita_cat(idCat,idGrupCat);
            //SACAMOS EL ID DE LA PUCLICACION DE LA PRIMER TABLA
            int idGrup= Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
            carga_tabla_cat_grup(idGrup);
            carga_tabla_cat(idGrup);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }//GEN-LAST:event_quita_catActionPerformed

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        //COLOCAMOS LOS VALORES DE LA TABLA EN LOS INPUT TEXT
        asigna_tabla_grupo_input_text();
        int idGrup= Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        carga_tabla_cat_grup(idGrup);
        carga_tabla_cat(idGrup);
        
        agregar.setEnabled(false);
        editar.setEnabled(true);
        nuevo.setEnabled(true);
        borrar.setEnabled(true);
        habilitaText(true);

    }//GEN-LAST:event_tablaKeyReleased

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        //COLOCAMOS TODOS LOS TEXTFIEL VACIOS
        habilitaText(true);
        borraText();
        agregar.setEnabled(true);
        borrar.setEnabled(false);
        nuevo.setEnabled(false);

    }//GEN-LAST:event_nuevoActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        
        //SACAMOS EL ID DE LA PUBLICACION DE LA TABLA
        int idPub = Integer.parseInt(tabla.getModel().getValueAt(tabla.getSelectedRow(), 0).toString());
        int selRow = tabla.getSelectedRow();
        int Iactivo=0;
        //PASAMOS LOS VALORES DE LOS INPUTSTEXT A LA FUNCION EDITAR
        if(activo.isSelected()){
            Iactivo=1;
        }
        else{
            Iactivo=0;
        }
        editar(idPub, nombre.getText(), url.getText(), palabras_plus.getText(), tipo.getSelectedValue(),  Iactivo);
        modelo_grupo.setValueAt(tabla, selRow, 1);
        tabla.setValueAt(nombre.getText(), selRow, 1);
        tabla.setValueAt(url.getText(), selRow, 2);
        tabla.setValueAt(palabras_plus.getText(), selRow, 3);
        tabla.setValueAt(tipo.getSelectedValue(), selRow, 4);
        tabla.setValueAt(""+Iactivo, selRow, 5);
        
    }//GEN-LAST:event_editarActionPerformed
    /*
    *
    *FUNCIONES AGREGAR BORRAR EDITAR
    *
    */
    //COLOCAMOS EL TEXTO DE LA TABLA EN CADA INPUT TEXT
    public void asigna_tabla_grupo_input_text() {
        try {
            nombre.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
            url.setText(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
            palabras_plus.setText(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
            tipo.setSelectedValue(tabla.getValueAt(tabla.getSelectedRow(), 4).toString(), false);
            if("1".equals(tabla.getValueAt(tabla.getSelectedRow(), 5).toString())){
                activo.setSelected(true);
            }else{
                activo.setSelected(false);
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    //HABILITA O DESHABILITA LOS TEXTOS
    public void habilitaText(boolean siNo){
        //COLOCAMOS TODOS LOS TEXTFIEL VACIOS
        nombre.setEnabled(siNo);
        url.setEnabled(siNo);
        palabras_plus.setEnabled(siNo);
        tipo.setEnabled(siNo);
        activo.setEnabled(siNo);
    }
    public void borraText(){
        //COLOCAMOS TODOS LOS TEXTFIEL VACIOS
        nombre.setText("");
        url.setText("");
        palabras_plus.setText("");
        tipo.setSelectedValue("FB", false);
        activo.setSelected(true);
    }

    //FUNCION QUE RESIBE EL ID A BORRAR
    public void borrar(int id) {
        String sql = "DELETE FROM grupos WHERE id='"+id+"';";
        //SI LA CONEXION ES EXITOSA
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL QUERY
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void quita_cat(int idCat,int idGrup) {
        String sql = "delete from \"grup-cat\" WHERE \"grup-cat\".idCat='"+idCat+"' and \"grup-cat\".idGrup='"+idGrup+"';";
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
    public void agrega_cat(int idCat,int idGrup) {
        String sql = "insert into \"grup-cat\" (idCat,idGrup) VALUES ('"+idCat+"','"+idGrup+"');";
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
    public void editar(int id, String nombre, String url,String palabras_plus,String tipo, int activo) {
        String sql = "UPDATE grupos SET "
                + "nombre = '"+ nombre +"',  "
                + "url = '"+ url +"',  "
                + "palabras_plus = '"+ palabras_plus +"',  "
                + "tipo = '"+ tipo +"',  "
                + "activo = '"+ activo +"'  "
                + "WHERE id = '"+id+"';";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //FUNCION AGREGAR
    public void agregar(String nombre, String url, String palabras_plus, String tipo, int activo) {
        String sql = "INSERT INTO grupos "
                + "(    nombre,        url,        palabras_plus,        tipo,         activo     ) values "
                + "('"+ nombre +"','"+ url +"','"+ palabras_plus +"','"+ tipo +"','" + activo + "')";
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
    //CARGA GRUPOS
    public void carga_tabla() {
        String Dato[]=new String[6];
        modelo_grupo.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS GRUPOS
            String query = "SELECT "
                    + "id, "
                    + "nombre, "
                    + "url, "
                    + "palabras_plus, "
                    + "tipo, "
                    + "activo "
                    + "FROM grupos;";
            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0]=rs.getString("id");
                Dato[1]=rs.getString("nombre");
                Dato[2]=rs.getString("url");
                Dato[3]=rs.getString("palabras_plus");
                Dato[4]=rs.getString("tipo");
                Dato[5]=rs.getString("activo");
                modelo_grupo.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla.setModel(modelo_grupo);
            //OCULTAMOS EL ID EN LA TABLA
            //tabla.getColumn("id").setMaxWidth(0);
            nombre.setText("");
            url.setText("");
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
    //CARGA CATEGORIAS DEL GRUPO
    public void carga_tabla_cat_grup(int id_grup) {
        String Dato[]=new String[4];
        modelo_cat_grup.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS GRUPOS
            String query = "SELECT \n" +
            "\"grup-cat\".id,\n" +
            "\"grup-cat\".idCat,\n" +
            "\"grup-cat\".idGrup,\n" +
            "categoria.nombre,\n" +
            "grupos.id \n" +
            "FROM \n" +
            "categoria \n" +
            "INNER JOIN \"grup-cat\" ON \"grup-cat\".idCat = categoria.id \n" +
            "INNER JOIN grupos ON \"grup-cat\".idGrup = grupos.id \n"+
            "where \n"+
            "grupos.id='"+id_grup+"';";
            System.out.println("---CATEGORIAS DEL GRUPO\n"+query);

            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0]=rs.getString("id");
                Dato[1]=rs.getString("idCat");
                Dato[2]=rs.getString("idGrup");
                Dato[3]=rs.getString("nombre");
                modelo_cat_grup.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla_cat_grup.setModel(modelo_cat_grup);
            //OCULTAMOS EL ID EN LA TABLA
            /*
            tabla_cat_pub.getColumn("id").setMaxWidth(0);
            tabla_cat_pub.getColumn("idCat").setMaxWidth(0);
            tabla_cat_pub.getColumn("idGrup").setMaxWidth(0);
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
    //CARGA LA TABLA DE CATEGORIAS QUE NO TENGA ESTE GRUPO
    public void carga_tabla_cat(int id_grup) {
        String Dato[]=new String[3];
        modelo_cat.setRowCount(0);
        try {
            //CONECTA A LA BD
            connection = this.connect();
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //QUERY QUE JALA TODAS LAS GRUPOS
            String query = "SELECT \n" +
            "id,\n" +
            "nombre\n" +
            " FROM categoria \n" +
            "WHERE id not in (SELECT \"grup-cat\".idCat from \"grup-cat\" where \"grup-cat\".idGrup='"+id_grup+"');" ;
            System.out.println("---CATEGORIAS QUE NO TENGA ESTE GRUPO\n"+query);
            ResultSet rs = statement.executeQuery(query);
            //CICLO QUE LLENA TODO EL MODELO
            while (rs.next()) {
                Dato[0]=rs.getString("id");
                Dato[1]=rs.getString("nombre");
                modelo_cat.addRow(Dato);
            }
            //LLENAMOS LA TABLA CON EL MODELO
            tabla_cat_exist.setModel(modelo_cat);
            //OCULTAMOS EL ID EN LA TABLA
            /*
            tabla_cat_exist.getColumn("idCat").setMaxWidth(0);
            tabla_cat_exist.getColumn("idGrup").setMaxWidth(0);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activo;
    private javax.swing.JButton agrega_cat;
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrar;
    private javax.swing.JButton editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JTextField palabras_plus;
    private javax.swing.JButton quita_cat;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_cat_exist;
    private javax.swing.JTable tabla_cat_grup;
    private javax.swing.JList<String> tipo;
    private javax.swing.JTextField url;
    // End of variables declaration//GEN-END:variables
}
