package com.mycompany.mavenproject1;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class cControl {

    long rapido = 500;
    long medio = 1000;
    long lento = 2000;
    long mlento = 3000;
    //DEFINE SI SE ESTA RPBANDO DESDE UN TELEFONO O DESDE ESCRITORIO
    boolean esTelefono = false;
    WebDriver driver;

    Connection connection = null;
    Statement statement = null;
    String path_drive = "";
    Object[] options = {"Continuar", "Detener"};
    String escribirCon = "";

    public void cControl(String escribirCon1) {
        escribirCon = escribirCon1;
    }

    public void cControl() {
        escribirCon = "sendkeys";
    }

    public void inicializarWebdriver(String path_drive) {
        try {
            //CREA UNA BD (SI NO EXISTE) EN DONDE SE ENCUENTRE LA APLICACION
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            System.setProperty("webdriver.chrome.driver", path_drive);
            //EVITAMOS QUE APARESCAN NOTIFICACIONES (PERMITIR GEOLOCALIZACION)
            //Map<String, Object> prefs = new HashMap<String, Object>();
            //prefs.put("profile.default_content_setting_values.notifications", 2);
            ChromeOptions optionsGoo = new ChromeOptions();
            //optionsGoo.setExperimentalOption("args", "no-sandbox");
            optionsGoo.addArguments("--no-sandbox");
            optionsGoo.addArguments("--disable-notifications");
            //FIN EVITAMOS QUE APARESCAN NOTIFICACIONES (PERMITIR GEOLOCALIZACION)
            driver = new ChromeDriver(optionsGoo);
        } catch (NoSuchElementException e) {
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
    }

    public void inicializarRemoteWebdriver(String USERNAME, String AUTOMATE_KEY, String hub, String remote_config1, String remote_config2, String remote_config3, String remote_config4, String remote_config5) {
        String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + hub;
        String[] config1 = remote_config1.split(",");
        String[] config2 = remote_config2.split(",");
        String[] config3 = remote_config3.split(",");
        String[] config4 = remote_config4.split(",");
        String[] config5 = remote_config5.split(",");
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            if (config1[0].isEmpty() == false) {
                caps.setCapability(config1[0], config1[1]);
            }
            if (config2[0].isEmpty() == false) {
                caps.setCapability(config2[0], config2[1]);
            }
            if (config3[0].isEmpty() == false) {
                caps.setCapability(config3[0], config3[1]);
            }
            if (config4[0].isEmpty() == false) {
                caps.setCapability(config4[0], config4[1]);
            }
            if (config5[0].isEmpty() == false) {
                caps.setCapability(config5[0], config5[1]);
            }
            try {
                String browserName = caps.getCapability("browserName").toString();
                //SI EL Capability browserName ESTA VACIO SIGNIFICA QUE SI ES UN TELEFONO
                if (browserName.isEmpty() == true) {
                    esTelefono = false;
                } else {
                    esTelefono = true;
                }
            } catch (Exception e) {
                esTelefono = false;
            }
            driver = new RemoteWebDriver(new URL(URL), caps);
        } catch (NoSuchElementException e) {
            int res = JOptionPane.showOptionDialog(null, "Desea continuar o detener? \n error= " + e, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (res == 1) {
                driver.quit();
            }
        } catch (Exception e) {
            int res = JOptionPane.showOptionDialog(null, "Desea continuar o detener? \n error= " + e, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (res == 1) {
                driver.quit();
            }
        }
    }

    /*
    *
    *
    *          FB
    *
    *
    *
     */
    public void accedeFB(String user, String password) {
        driver.get("https://www.facebook.com/");
        if (user.length() > 0 && password.length() > 0) {
            escribeTexto(driver.findElement(By.xpath("//*[@id=\"email\"]")), user);
            escribeTexto(driver.findElement(By.xpath("//*[@id=\"pass\"]")), password);
            //driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(user);
            //driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
            pausa(rapido);
            driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(Keys.ENTER);
            //JOptionPane.showMessageDialog(null, "Elimine todos los popups de facebook y haga click en aceptar en este mensaje para continuar");
        } else {
            //PEDIMOS AL USUARIO QUE PONGA SU USUARIO Y CONTRASEÑA PARA PODER CONTINUAR
            JOptionPane.showMessageDialog(null, "Favor de loguearse en su cuenta y despues hacer click en aceptar");
        }

    }

    public void ir(String url) {
        driver.get(url);
    }

    public void probarFB(String urlPrueba) {
        driver.get(urlPrueba);
        try {
            driver.findElement(By.cssSelector("//br[@data-text='true']")).click();
        } catch (NoSuchElementException e) {
            //String grupoError = grupoError + rs.getString("nombre") + "\n" + rs.getString("url") + "\n";
        }

    }

    /**
     * Si ya esta publicado en todos los grupos se desactiva esta publicacion
     *
     * @param idPub id de la publicacion a revisar
     * @param idsYaCompartidos lista dividida por comas de los ids de los grupos
     * en donde ya se compartio esta publicacion
     * @return boolean True si se desactivo, False si no se desactivo
     */
    public boolean publicacionYacompartida(String idPub, String idsYaCompartidos) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //SACAMOS LA LISTA DE TODOS LOS GRUPOS DE FACEBOOK MENOS LOS GRUPOS EN LOS QUE YA SE COMPARTIO
            String query = "  SELECT DISTINCT\n"
                    + "grupos.id,\n"
                    + "grupos.nombre,\n"
                    + "grupos.url,\n"
                    + "grupos.activo\n"
                    + "FROM\n"
                    + "grupos\n"
                    + "INNER JOIN \"grup-cat\" ON \"grup-cat\".idGrup = grupos.id\n"
                    + "INNER JOIN categoria ON \"grup-cat\".idCat = categoria.id\n"
                    + "INNER JOIN \"pub-cat\" ON \"pub-cat\".idCat = categoria.id\n"
                    + "INNER JOIN publicaciones ON \"pub-cat\".idPub = publicaciones.id\n"
                    + "WHERE\n"
                    + "grupos.activo = '1' \n"
                    + "and publicaciones.id='" + idPub + "' "
                    + "AND grupos.id not in (" + idsYaCompartidos + ")   "
                    + "and grupos.tipo='FB';  ";
            ResultSet rs = statement.executeQuery(query);
            // Esta publicacion ya se compartio en todos los grupos
            if (rs.isBeforeFirst() == false) {
                // Desactivamos la publicacion
                desactivaPublicacion(Integer.parseInt(idPub));
                // Retornamos un true diciento que esta publicacion se desactivo
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
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

        return false;
    }

    public String[] compartirFB(String urlVideo, String pathImagen, String titulo, String idPub, int numeroAcompartir, String idsYaCompartidos) {
        String grupoError = "";
        String grupoBien = "";
        int vecesCompartido = 0;
        String listaIdsDeGrupos = "";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //SACAMOS LA LISTA DE TODOS LOS GRUPOS DE FACEBOOK MENOS LOS GRUPOS EN LOS QUE YA SE COMPARTIO
            String query = "  SELECT DISTINCT\n"
                    + "grupos.id,\n"
                    + "grupos.nombre,\n"
                    + "grupos.url,\n"
                    + "grupos.activo\n"
                    + "FROM\n"
                    + "grupos\n"
                    + "INNER JOIN \"grup-cat\" ON \"grup-cat\".idGrup = grupos.id\n"
                    + "INNER JOIN categoria ON \"grup-cat\".idCat = categoria.id\n"
                    + "INNER JOIN \"pub-cat\" ON \"pub-cat\".idCat = categoria.id\n"
                    + "INNER JOIN publicaciones ON \"pub-cat\".idPub = publicaciones.id\n"
                    + "WHERE\n"
                    + "grupos.activo = '1' \n"
                    + "and publicaciones.id='" + idPub + "' "
                    + "AND grupos.id not in (" + idsYaCompartidos + ")   "
                    + "and grupos.tipo='FB'  "
                    + "limit " + numeroAcompartir + " ;  ";
            ResultSet rs = statement.executeQuery(query);
            // Esta publicacion ya se compartio en todos los grupos
            if (rs.isBeforeFirst() == false) {
                desactivaPublicacion(Integer.parseInt(idPub));
                // Cerramos navegador y nos salimos
                cerrarNavegador();
                return null;
            }
            //HACEMOS UN BLUCLE CON TODOS LOS GRUPOS DE FACEBOOK
            while (rs.next()) {
                try {
                    //PONEMOS LA URL DEL GRUPO QUE SACAMOS DE LA BD
                    driver.get(rs.getString("url"));
                    //DETECTAMOS SI HAY UN ALERT Y SI APARECE LA ALERTA DAMOS CLICK EN ACEPTAR(A VECES FACEBOOK PONE ALERTAS QUE NO DEJAN CONTINUAR)
                    try {
                        Alert alert = driver.switchTo().alert();
                        alert.accept();
                    } //SI NO HAY ALERT PROCEDEMOS A ECRIBIR EL ARTICULO
                    catch (NoAlertPresentException e) {
                        //AGREGAMOS LOS GRUPOS QUE SE VAN A GUARDAR EN GRUPOS YA PUBLICADOS
                        listaIdsDeGrupos = listaIdsDeGrupos + "," + rs.getString("id");
                        pausa(mlento);
                        String espacio = "";
                        String borrar = "";
                        //CICLO QUE ESCRIBE Y BORRA LETRAS PARA ESPERAR QUE SE CARGUE LA IMAGEN AUTOMATICAMENTE
                        for (int letras = 0; letras < 380; letras++) {
                            espacio = espacio + "1";
                            borrar = borrar + Keys.BACK_SPACE;
                        }
                        //ESCRIBIMOS EL TITULO Y EL VIDEO DE LA PUBLICACION
                        escribeTexto(driver.findElement(By.name("xhpc_message_text")),
                                titulo
                                + "\\r"
                                + urlVideo
                                + "\\r"
                        );

                        grupoBien = grupoBien + " </br> " + rs.getString("nombre") + " </br>\n " + rs.getString("url") + " </br>\n ";
                        pausa(mlento);
                        driver.findElement(By.name("xhpc_message_text")).sendKeys(Keys.chord(espacio + borrar + Keys.CONTROL, Keys.ENTER));
                        pausa(mlento);
                        pausa(mlento);
                        pausa(mlento);
                        pausa(mlento);
                        vecesCompartido++;
                    }
                } catch (NoSuchElementException e) {
                    grupoError = grupoError + rs.getString("nombre") + "\n" + rs.getString("url") + "\n";
                } catch (Exception e) {
                    grupoError = grupoError + rs.getString("nombre") + "\n" + rs.getString("url") + "\n";
                }

            }
        } catch (NoSuchElementException e) {
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
        return new String[]{grupoError, listaIdsDeGrupos, grupoBien};
    }

    public int numeroCompartidasFB(String idPub) {
        int vecesCompartido = 0;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //SACAMOS LA LISTA DE TODOS LOS GRUPOS DE FACEBOOK
            String query = "  SELECT DISTINCT\n"
                    + "count(grupos.id) as numeroCompartidasFB "
                    + "FROM\n"
                    + "grupos\n"
                    + "INNER JOIN \"grup-cat\" ON \"grup-cat\".idGrup = grupos.id\n"
                    + "INNER JOIN categoria ON \"grup-cat\".idCat = categoria.id\n"
                    + "INNER JOIN \"pub-cat\" ON \"pub-cat\".idCat = categoria.id\n"
                    + "INNER JOIN publicaciones ON \"pub-cat\".idPub = publicaciones.id\n"
                    + "WHERE\n"
                    + "grupos.activo = '1' \n"
                    + "and publicaciones.id='" + idPub + "' "
                    + "and grupos.tipo='FB';  ";
            ResultSet rs = statement.executeQuery(query);
            return Integer.parseInt(rs.getString("numeroCompartidasFB"));

        } catch (Exception e) {
            return 0;
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

    /*
    *
    *
    *          G+
    *
    *
    *
     */
    public void accedeGP(String user, String password, String forma) {
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fplus.google.com");
        if (user.length() > 0 && password.length() > 0) {
            escribeTexto(driver.findElement(By.id("identifierId")), user);
            //driver.findElement(By.id("identifierId")).sendKeys(user);
            pausa(rapido);
            driver.findElement(By.id("identifierNext")).click();
            pausa(rapido);
            escribeTexto(driver.findElement(By.name("password")), password);
            //driver.findElement(By.name("password")).sendKeys(password);
            pausa(rapido);
            if (forma.equals("automatico")) {
                driver.findElement(By.id("passwordNext")).click();
                pausa(medio);
                pausa(rapido);
            } else if (forma.equals("manual")) {
                JOptionPane.showMessageDialog(null, "Favor de loguearse en su cuenta y despues hacer click en aceptar");
            }
        } else {
            //PEDIMOS AL USUARIO QUE PONGA SU USUARIO Y CONTRASEÑA PARA PODER CONTINUAR
            JOptionPane.showMessageDialog(null, "Favor de loguearse en su cuenta y despues hacer click en aceptar");
        }
    }

    public void accedeGPManual(String user, String password) {
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fplus.google.com");
        pausa(rapido);
        driver.findElement(By.id("identifierId")).sendKeys(user);
        pausa(rapido);
        driver.findElement(By.id("identifierNext")).click();
        pausa(rapido);
        driver.findElement(By.name("password")).sendKeys(password);
        pausa(rapido);
        JOptionPane.showMessageDialog(null, "Favor de loguearse en su cuenta y despues hacer click en aceptar");
    }

    public String[] compartirGP(String urlVideo, String pathImagen, String titulo, String idPub, int numeroAcompartir, String idsYaCompartidos) {
        String grupoError = "";
        String grupoBien = "";
        int vecesCompartido = 0;
        String listaIdsDeGrupos = "";
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //BANDERA QUE SE ACTIVA SI SE ENCONTRO UNA PALABRA PLUS SI NO SE ENCUNTRA LA PALABRA MANDA UNA NOTIFICACION
            int flagSeEncontroUnaPalabraPlus = 0;
            //SACAMOS LA LISTA DE TODOS LOS GRUPOS DE FACEBOOK
            String query = "  SELECT DISTINCT\n"
                    + "grupos.id,\n"
                    + "grupos.nombre,\n"
                    + "grupos.url,\n"
                    + "grupos.activo,\n"
                    + "grupos.palabras_plus\n"
                    + "FROM\n"
                    + "grupos\n"
                    + "INNER JOIN \"grup-cat\" ON \"grup-cat\".idGrup = grupos.id\n"
                    + "INNER JOIN categoria ON \"grup-cat\".idCat = categoria.id\n"
                    + "INNER JOIN \"pub-cat\" ON \"pub-cat\".idCat = categoria.id\n"
                    + "INNER JOIN publicaciones ON \"pub-cat\".idPub = publicaciones.id\n"
                    + "WHERE\n"
                    + "grupos.activo = '1' \n"
                    + "and publicaciones.id='" + idPub + "' "
                    + "AND grupos.id not in (" + idsYaCompartidos + ")   "
                    + "and grupos.tipo='GP'  "
                    + "limit " + numeroAcompartir + "; ";
            ResultSet rs = statement.executeQuery(query);
            if (rs.isBeforeFirst() == false) {
                return null;
            }
            //HACEMOS UN BLUCLE CON TODOS LOS GRUPOS DE GOOGLE
            while (rs.next()) {
                flagSeEncontroUnaPalabraPlus = 0;
                try {
                    //PONEMOS LA URL DEL GRUPO QUE SACAMOS DE LA BD
                    driver.get(rs.getString("url"));
                    pausa(medio);
                    driver.findElement(By.cssSelector("span[role='button']")).click();
                    pausa(medio);
                    String espacio = "";
                    String borrar = "";
                    //CICLO QUE ESCRIBE Y BORRA LETRAS PARA ESPERAR QUE SE CARGUE LA IMAGEN AUTOMATICAMENTE
                    for (int letras = 0; letras < 380; letras++) {
                        espacio = espacio + "1";
                        borrar = borrar + Keys.BACK_SPACE;
                    }
                    //ESCRIBIMOS EL TEXTO DEL VIDEO
                    //driver.findElement(By.cssSelector("textarea[role='textbox']")).sendKeys(titulo + Keys.RETURN + urlVideo + Keys.RETURN + "                             " + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE  + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.BACK_SPACE + Keys.chord(Keys.CONTROL, Keys.ENTER));

                    escribeTexto(driver.findElement(By.cssSelector("textarea[role='textbox']")),
                            titulo
                            + "\\r"
                            + urlVideo
                    );
                    pausa(rapido);
                    driver.findElement(By.cssSelector("textarea[role='textbox']")).sendKeys(Keys.ENTER);
                    driver.findElement(By.cssSelector("textarea[role='textbox']")).sendKeys(Keys.chord(espacio + borrar + Keys.CONTROL, Keys.ENTER));
                    //SACAMOS LA LSTA DE LOS NOMBRES DE LOS GRUPOS QUE SI SE ESCRIBIERON
                    grupoBien = grupoBien + " </br> " + rs.getString("nombre") + " </br>\n " + rs.getString("url") + " </br>\n ";
                    //AGREGAMOS LOS GRUPOS QUE SE VAN A GUARDAR EN GRUPOS YA PUBLICADOS
                    listaIdsDeGrupos = listaIdsDeGrupos + "," + rs.getString("id");
                    pausa(mlento);
                    pausa(mlento);
                    vecesCompartido++;
                    //SI NO HAY PALABRAS PLUS ES PORQUE ESE GRUPO NO TIENE Y NOS SEGUIMOS AL SIGUIENTE GRUPO
                    if (rs.getString("palabras_plus").length() > 1) {
                        pausa(rapido);
                        //DIVIDIMOS EL STRING DIVIDIDO POR COMAS EN UN ARRAY
                        String[] palabra_plus = rs.getString("palabras_plus").split(",");
                        for (int i = 0; i < palabra_plus.length; i++) {
                            //SI HAY 0 PALABRAS PLUS ENCONTRADAS ENTRAMOS A HACER CLICK A UNA PALABRA PLUS
                            if (flagSeEncontroUnaPalabraPlus == 0) {
                                //SI HAY UN ELEMENTO CON EL NOMBRE DE LA PALABRA PLUS LE HACEMOS CLCICK
                                if (driver.findElements(By.cssSelector("div[data-name='" + palabra_plus[i] + "']")).size() > 0) {
                                    driver.findElement(By.cssSelector("div[data-name='" + palabra_plus[i] + "']")).click();
                                    flagSeEncontroUnaPalabraPlus++;
                                }
                            }
                        }
                        //SI NO SE ENCONTRO NINGUNA PALABRA PONEMOS ESE ERROR EN EL LOG
                        if (flagSeEncontroUnaPalabraPlus == 0) {
                            grupoError = grupoError + rs.getString("nombre") + "\n" + rs.getString("url") + "  \n PALABRA NO ENCONTRADA \n";
                        }
                    }
                    pausa(mlento);
                    pausa(mlento);

                } catch (NoSuchElementException e) {
                    grupoError = grupoError + rs.getString("nombre") + "\n" + rs.getString("url") + "\n";
                } catch (Exception e) {
                    grupoError = grupoError + rs.getString("nombre") + "\n" + rs.getString("url") + "\n";
                }
            }
        } catch (NoSuchElementException e) {
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
        return new String[]{grupoError, listaIdsDeGrupos, grupoBien};
    }

    public int numeroCompartidasGP(String idPub) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tmt.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            //SACAMOS LA LISTA DE TODOS LOS GRUPOS DE FACEBOOK
            String query = "  SELECT DISTINCT\n"
                    + "count(grupos.id) as numeroCompartidasGP "
                    + "FROM\n"
                    + "grupos\n"
                    + "INNER JOIN \"grup-cat\" ON \"grup-cat\".idGrup = grupos.id\n"
                    + "INNER JOIN categoria ON \"grup-cat\".idCat = categoria.id\n"
                    + "INNER JOIN \"pub-cat\" ON \"pub-cat\".idCat = categoria.id\n"
                    + "INNER JOIN publicaciones ON \"pub-cat\".idPub = publicaciones.id\n"
                    + "WHERE\n"
                    + "grupos.activo = '1' \n"
                    + "and publicaciones.id='" + idPub + "' "
                    + "and grupos.tipo='GP';  ";
            ResultSet rs = statement.executeQuery(query);
            return Integer.parseInt(rs.getString("numeroCompartidasGP"));

        } catch (Exception e) {
            return 0;
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

    /*
    *
    *
    *          KINGDOMLIKES
    *
    *
    *
     */
    String userKingdom = "";

    public void accedeKL(String user, String password) {
        //Asignamos el nombre del usuario a esta variable global para que la pnga en el navegador
        userKingdom = user;
        try {
            driver.get("https://kingdomlikes.com");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>" + userKingdom + "</div>\");");
            js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentos + "</div>\");");
            pausa(medio);
            if (user.length() > 0 && password.length() > 0) {
                //driver.findElement(By.name("email")).sendKeys(user);
                escribeTextoJS(driver.findElement(By.name("email")), user);
                //copy(user);
                //driver.findElement(By.name("email")).click();
                //paste();
                driver.findElement(By.name("password")).sendKeys(password);
                //driver.findElement(By.name("password")).click();
                pausa(rapido);
                driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
            } else {
                //PEDIMOS AL USUARIO QUE PONGA SU USUARIO Y CONTRASEÑA PARA PODER CONTINUAR
                JOptionPane.showMessageDialog(null, "Favor de loguearse en su cuenta y despues hacer click en aceptar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    int intentos = 0;

    public void clickVideos() {
        //DESPUES DE 3 INTENTOS Y NO FUNCIONA ES QUE HAY ALGO MAL Y SE SALE
        if (intentos > 3) {
            int res = JOptionPane.showOptionDialog(null, "Se han realizado 3 intentos fallidos desea intentar de nuevo o cerrar", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (res == 1) {
                driver.quit();
            } //SI DESEA CONTINUA RESETEAMOS LOS INTENTO A 0 PARA PROBAR DE NUEVO
            else {
                intentos = 0;
            }
        }
        //ESTA BANDERA MARCA SI ENCONTRO UN BOTON PARA ABRIR LA VENTANA SECUNDARIA
        boolean banderaEncontro = false;
        //ESTE ES EL CODIGO DE LA VENTANA A ABRIR
        String codigoOnClick = "";
        //SEGUNDOS A ESPERAR EL VIDEO ABIERTO
        int totSeg = 0;
        pausa(mlento);
        pausa(mlento);
        pausa(mlento);
        pausa(mlento);
        pausa(mlento);
        pausa(mlento);

        //VAMOS A LA PAGINA DE LOS VIDEOS
        driver.get("https://kingdomlikes.com/free_points/youtube-views");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>" + userKingdom + "</div>\");");
        js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentos + "</div>\");");
        //ESPERAMOS UN RATO A QUE CARGUEN TODOS LOS VIDEOS
        pausa(lento);
        pausa(lento);
        pausa(lento);
        pausa(lento);
        pausa(lento);
        //HACEMOS UNA LISTA CON TODOS LOS BOTONES
        List<WebElement> todosLosBotones = driver.findElements(By.className("button"));
        Iterator<WebElement> boton = todosLosBotones.iterator();
        //CICLO QUE BUSCA TODOS LOS BOTONES
        while (boton.hasNext()) {
            try {
                //JALAMOS EL CODIGO DEL CLICK
                codigoOnClick = boton.next().getAttribute("onclick");
                //SI EL TEXTO NO ESTA VACIO LO EJECUTAMOS
                if (codigoOnClick.length() > 0) {
                    if (driver instanceof JavascriptExecutor) {
                        ((JavascriptExecutor) driver).executeScript(codigoOnClick);
                    } else {
                        throw new IllegalStateException("This driver does not support JavaScript!");
                    }
                } //SI EL TEXTO DEL CODIGO ESTA VACIO SUMAMOS UN INTENTO Y REINICIAMOS ESTA FUNCION
                else {
                    intentos++;
                    js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentos + "</div>\");");
                    clickVideos();
                }
                //ACTIVAMOS LA BANDERA DICIENDO QUE SI DIMOS CLICK EN EL BOTON
                banderaEncontro = true;
            } catch (Exception e) {
                //LA BANDERA ES FALSA PORQUE REALMENTE NO ENCONTRO NINGUN BOTON
                banderaEncontro = false;
                //SUMA IN INTENTO MAS
                intentos++;
                js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentos + "</div>\");");
                //REINICIA LA FUNCION
                clickVideos();
            }
            //CERRAMOS EL CICLO
            break;
        }
        pausa(rapido);
        //SI SE ENCONTRO UN BOTON Y SE DIO CLICK EN EL SE HABRE UNA NUEVA VENTANA Y HAY QUE ESPERAR UN TIEMPO Y CERRARLA
        if (banderaEncontro) {

            /*   SEGUNDOS A CERRAR SUBVENTANA   */
            //HACEMOS UNA LISTA CON TODOS LOS BOTONES
            List<WebElement> todosCronometros = driver.findElements(By.className("containerbtn"));
            Iterator<WebElement> cronometro = todosCronometros.iterator();
            while (cronometro.hasNext()) {
                try {
                    //ES EL TEXTO DEL TIEMPO QUE SE TIENE QUE VER EL VIDEO
                    String textoCronometro = cronometro.next().getText();
                    //DIVIDIMOS EN TEXTO EN 2 PARTES ANTES Y DESPUES DE /
                    String[] parts = textoCronometro.split("/");
                    //SACAMOS EL TEXTO DE MINUTOS Y SEGUNDOS A PAGAR
                    String minutosSegundos = parts[1].replaceAll("minutes", "").trim();
                    //DIVIDIMOS EL TEXTO EN MINUTOS Y SEGUNDOS
                    String[] partesMin = minutosSegundos.split(":");
                    //SACAMOS LOS MINUTOS
                    int partMin = Integer.parseInt(partesMin[0]);
                    //SACAMOS LOS SEGUNDOS
                    int partSeg = Integer.parseInt(partesMin[1]);
                    //SACAMOS LA CANTIDAD TOTAL DE SEGUNDOS
                    totSeg = (partMin * 60) + partSeg + 6;
                    break;
                } catch (Exception e) {
                    //SUMA IN INTENTO MAS
                    intentos++;
                    js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentos + "</div>\");");
                    //REINICIA LA FUNCION
                    clickVideos();
                }
            }
            //OBTENEMOS UN NUMERO ENTRE 4 Y 11
            int numeroAleatorio = (int) (Math.random() * 4) + 7;
            //CONVERTIMOS LOS SEGUNDOS EN MILISEGUNDOS
            int miliSegundos = (totSeg + numeroAleatorio + 5) * 1000;
            //ESPERAMOS LOS MINUTOS SE PIDIO PARA CERRAR LA SUBVENTANA
            pausa(miliSegundos);

            /*  CERRAR VENTANA SECUNDARIA  */
            //JALAMOS EL NOMBRE DE LA VENTANA PRINCIPAL
            String nombreVentanaPrincipal = driver.getWindowHandle();
            //JALAMOS LA LISTA DE VENTANAS
            Set<String> listaVentanas = driver.getWindowHandles();
            //DE LA LISTA DE VENTANA BORRAMOS LA VENTANA PRINCIPAL
            listaVentanas.remove(nombreVentanaPrincipal);
            //EL ARREGLO DE VENTANAS LO REDOMENCIONAMOS A 1
            assert listaVentanas.size() == 1;
            //SI LA VENTANA SECUNDARIA YA FUE CERRADA YA NO LA CERRAMOS
            try {
                //NOS MOVEMOS A LA VENTANA SECUNDARIA
                driver.switchTo().window(listaVentanas.toArray()[0].toString());
                //CERRAMOS ESA VENTANA
                driver.close();
                //REGRESAMOS A LA VENTANA PRINCIPAL
                driver.switchTo().window(nombreVentanaPrincipal);
            } catch (ArrayIndexOutOfBoundsException excepcion) {
                System.out.println(" Error de índice en un array");
            }
            //COMO EL PROCESO FUE EXISTOSO REINICIAMOS LOS INTENTOS
            intentos = 0;
            //ESPERAMOS 3 SEGUNDOS EN LO SE CARGAN NUESTROS PUNTOS
            pausa(lento);
            pausa(lento);
            //REINICIAMOS LA FUNCION
            clickVideos();
        } //SI NO ENCONTRO NINGUN VIDEO EN LA LISTA
        else {
            //ESPERAMOS UN MINUTO
            pausa(60000);
            clickVideos();

        }
    }

    public void clickVideosLimite(int limite, int procesosExitoso, int intentosFallidos) {
        //ESTA BANDERA MARCA SI ENCONTRO UN BOTON PARA ABRIR LA VENTANA SECUNDARIA
        boolean banderaEncontro = false;
        //ESTE ES EL CODIGO DE LA VENTANA A ABRIR
        String codigoOnClick = "";
        //SEGUNDOS A ESPERAR EL VIDEO ABIERTO
        int totSeg = 0;
        pausa(mlento);
        pausa(mlento);

        //VAMOS A LA PAGINA DE LOS VIDEOS
        driver.get("https://kingdomlikes.com/free_points/youtube-views");
        //Colocamos el nombre del usuario en la parte superior del navegador
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>" + userKingdom + "</div>\");");
        js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + procesosExitoso + " Limite=" + limite + "</div>\");");
        js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos fallidos=" + intentosFallidos + " Limite=" + limite + "</div>\");");
        //ESPERAMOS UN RATO A QUE CARGUEN TODOS LOS VIDEOS
        pausa(lento);
        pausa(lento);
        //HACEMOS UNA LISTA CON TODOS LOS BOTONES
        List<WebElement> todosLosBotones = driver.findElements(By.className("button"));
        Iterator<WebElement> boton = todosLosBotones.iterator();
        //CICLO QUE BUSCA TODOS LOS BOTONES
        while (boton.hasNext()) {
            try {
                //JALAMOS EL CODIGO DEL CLICK
                codigoOnClick = boton.next().getAttribute("onclick");
                //SI EL TEXTO NO ESTA VACIO LO EJECUTAMOS
                if (codigoOnClick.length() > 0) {
                    if (driver instanceof JavascriptExecutor) {
                        ((JavascriptExecutor) driver).executeScript(codigoOnClick);
                    } else {
                        throw new IllegalStateException("This driver does not support JavaScript!");
                    }
                } 
                //SI EL TEXTO DEL CODIGO ESTA VACIO SUMAMOS UN INTENTO Y REINICIAMOS ESTA FUNCION
                else {
                    intentosFallidos++;
                    js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos fallidos=" + intentosFallidos + " Limite=" + limite + "</div>\");");
                    clickVideosLimite(limite, procesosExitoso,intentosFallidos);
                }
                //ACTIVAMOS LA BANDERA DICIENDO QUE SI DIMOS CLICK EN EL BOTON
                banderaEncontro = true;
            } catch (Exception e) {
                //LA BANDERA ES FALSA PORQUE REALMENTE NO ENCONTRO NINGUN BOTON
                banderaEncontro = false;
                //SUMA IN INTENTO MAS
                intentosFallidos++;
                js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos fallidos=" + intentosFallidos + " Limite=" + limite + "</div>\");");
                //REINICIA LA FUNCION
                clickVideosLimite(limite, procesosExitoso,intentosFallidos);
            }
            //CERRAMOS EL CICLO
            break;
        }
        pausa(rapido);
        //SI SE ENCONTRO UN BOTON Y SE DIO CLICK EN EL SE HABRE UNA NUEVA VENTANA Y HAY QUE ESPERAR UN TIEMPO Y CERRARLA
        if (banderaEncontro) {
            /*   SEGUNDOS A CERRAR SUBVENTANA   */
            //HACEMOS UNA LISTA CON TODOS LOS BOTONES
            List<WebElement> todosCronometros = driver.findElements(By.className("containerbtn"));
            Iterator<WebElement> cronometro = todosCronometros.iterator();
            while (cronometro.hasNext()) {
                try {
                    //ES EL TEXTO DEL TIEMPO QUE SE TIENE QUE VER EL VIDEO
                    String textoCronometro = cronometro.next().getText();
                    //DIVIDIMOS EN TEXTO EN 2 PARTES ANTES Y DESPUES DE /
                    String[] parts = textoCronometro.split("/");
                    //SACAMOS EL TEXTO DE MINUTOS Y SEGUNDOS A PAGAR
                    String minutosSegundos = parts[1].replaceAll("minutes", "").trim();
                    //DIVIDIMOS EL TEXTO EN MINUTOS Y SEGUNDOS
                    String[] partesMin = minutosSegundos.split(":");
                    //SACAMOS LOS MINUTOS
                    int partMin = Integer.parseInt(partesMin[0]);
                    //SACAMOS LOS SEGUNDOS
                    int partSeg = Integer.parseInt(partesMin[1]);
                    //SACAMOS LA CANTIDAD TOTAL DE SEGUNDOS
                    totSeg = (partMin * 60) + partSeg + 6;
                    break;
                } catch (Exception e) {
                    //SUMA IN INTENTO MAS
                    intentosFallidos++;
                    js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + procesosExitoso + " Limite=" + limite + "</div>\");");
                    js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos fallidos=" + intentosFallidos + " Limite=" + limite + "</div>\");");
                    //REINICIA LA FUNCION
                    clickVideosLimite(limite, procesosExitoso,intentosFallidos);
                }
            }
            //Si llegamos a limite de videos cerramos el navegador y nos salimos de la funcion
            if (procesosExitoso == limite) {
                cerrarNavegador();
                return;
            }
            //Si el proceso fue exitoso contamos 1 mas
            procesosExitoso++;
            js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + procesosExitoso + " Limite=" + limite + "</div>\");");
            js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentosFallidos + " Limite=" + limite + "</div>\");");

            //OBTENEMOS UN NUMERO ENTRE 4 Y 11
            int numeroAleatorio = (int) (Math.random() * 4) + 7;
            //CONVERTIMOS LOS SEGUNDOS EN MILISEGUNDOS
            int miliSegundos = (totSeg + numeroAleatorio + 5) * 1000;
            //ESPERAMOS LOS MINUTOS SE PIDIO PARA CERRAR LA SUBVENTANA
            pausa(miliSegundos);

            /*  CERRAR VENTANA SECUNDARIA  */
            //JALAMOS EL NOMBRE DE LA VENTANA PRINCIPAL
            String nombreVentanaPrincipal = driver.getWindowHandle();
            //JALAMOS LA LISTA DE VENTANAS
            Set<String> listaVentanas = driver.getWindowHandles();
            //DE LA LISTA DE VENTANA BORRAMOS LA VENTANA PRINCIPAL
            listaVentanas.remove(nombreVentanaPrincipal);
            //EL ARREGLO DE VENTANAS LO REDOMENCIONAMOS A 1
            assert listaVentanas.size() == 1;
            //SI LA VENTANA SECUNDARIA YA FUE CERRADA YA NO LA CERRAMOS
            try {
                //NOS MOVEMOS A LA VENTANA SECUNDARIA
                driver.switchTo().window(listaVentanas.toArray()[0].toString());
                //CERRAMOS ESA VENTANA
                driver.close();
                //REGRESAMOS A LA VENTANA PRINCIPAL
                driver.switchTo().window(nombreVentanaPrincipal);
            } catch (ArrayIndexOutOfBoundsException excepcion) {
                System.out.println(" Error de índice en un array");
            }
            //COMO EL PROCESO FUE EXISTOSO REINICIAMOS LOS INTENTOS
            //intentos = 0;

            //ESPERAMOS 3 SEGUNDOS EN LO SE CARGAN NUESTROS PUNTOS
            pausa(lento);
            pausa(lento);
            //REINICIAMOS LA FUNCION
            clickVideosLimite(limite, procesosExitoso,intentosFallidos);
        } //SI NO ENCONTRO NINGUN VIDEO EN LA LISTA
        else {
            //Si llegamos a limite de videos cerramos el navegador y nos salimos de la funcion
            if (intentosFallidos == limite) {
                cerrarNavegador();
                return;
            }
            intentosFallidos++;
            //ESPERAMOS UN MINUTO
            pausa(60000);
            clickVideosLimite(limite, procesosExitoso,intentosFallidos);

        }
    }

    //Funcion que prepara el dar de alta lo video de youtube en kindomlikes
    public void creaVideos(String paises, int numeroTabs) {
        String listaPaises[] = paises.split("\\r?\\n");
        pausa(rapido);
        driver.get("https://kingdomlikes.com/sites/add");
        //Colocamos el nombre del usuario en la parte superior del navegador
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>" + userKingdom + "</div>\");");
        js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentos + "</div>\");");
        for (int tabs = 0; tabs < numeroTabs; tabs++) {
            Select drpCountry = new Select(driver.findElement(By.name("idtype")));
            drpCountry.selectByVisibleText("YouTube Views");
            driver.findElement(By.id("checkcountries")).click();
            driver.findElement(By.xpath("//*[@id=\"add\"]/div[4]/div[2]/button")).click();
            for (int i = 0; i < listaPaises.length; i++) {
                driver.findElement(By.xpath("//*[@title='" + listaPaises[i] + "']")).click();
                pausa(rapido);
            }
            ((JavascriptExecutor) driver).executeScript("window.open()");
            ArrayList<String> tabsEle = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabsEle.get(tabs + 1));
            driver.get("https://kingdomlikes.com/sites/add");
            //Colocamos el nombre del usuario en la parte superior del navegador
            js = (JavascriptExecutor) driver;
            js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>" + userKingdom + "</div>\");");
            js.executeScript("jQuery('body').prepend(\"<div style='font-size: 20pt;'>Intentos exitosos=" + intentos + "</div>\");");
        }
    }

    /*
    *
    *
    *          HITLEAP
    *
    *
    *
     */
    public void accedeHL(String user, String password) {
        try {
            driver.get("https://hitleap.com/authentication");
            pausa(lento);
            if (user.length() > 0 && password.length() > 0) {
                escribeTexto(driver.findElement(By.name("identifier")), user);
                escribeTexto(driver.findElement(By.name("password")), password);
                pausa(medio);
                driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
            } else {
                //PEDIMOS AL USUARIO QUE PONGA SU USUARIO Y CONTRASEÑA PARA PODER CONTINUAR
                JOptionPane.showMessageDialog(null, "Favor de loguearse en su cuenta y despues hacer click en aceptar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void escribeTexto(WebElement elementoWeb, String texto) {
        if (escribirCon.equals("sendkeys")) {
            elementoWeb.sendKeys(texto);
        } else if (escribirCon.equals("javascript")) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='" + texto + "';", elementoWeb);
        }
    }

    public void escribeTextoJS(WebElement elementoWeb, String texto) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + texto + "';", elementoWeb);
    }

    //PONEMOS LOS VIDEOS LE LOS SLOTS DE HITLEAP
    public boolean registraVideos(String listaPub) {
        pausa(lento);
        //ABRIMOS LA LISTA DE WEBSITES
        driver.get("https://hitleap.com/websites");
        pausa(lento);
        //SACAMOS LA LISTA DE TODAS LA TACHAS DE LAS DE LA LISTA DE PAGINAS PARA BORRARLAS UNA POR UNA
        List<WebElement> todasLasTachas = driver.findElements(By.cssSelector("a[data-original-title='Delete']"));
        //SI HAY TACHAS
        if (!todasLasTachas.isEmpty()) {
            for (WebElement tachas : todasLasTachas) {
                try {
                    //CLICK EN LA TACHA PARA BORRAR ESTA PAGINA DE HITLEAP
                    tachas.click();
                    pausa(lento);
                } catch (Exception er) {
                    return false;
                }
            }
        }
        try {
            //NOS MOVEMOS AL AREA DE CREAR PAGINAS
            driver.get("https://hitleap.com/websites/new");
            pausa(rapido);
            //CLICK EN BOTON PARA CREAR VARIA PAGINAS
            driver.findElement(By.className("add-list")).click();
            pausa(rapido);
            //ESCRIBIMOS LAS 3 URL'S
            escribeTexto(driver.findElement(By.name("address_list")), listaPub);
            //driver.findElement(By.id("address_list")).sendKeys(listaPub);
            pausa(rapido);
            //CERRAMOS LA LISTA DE VINCULOS
            driver.findElement(By.name("button")).click();
            pausa(rapido);
            //GUARDAMOS LOS LINKS QUE PUSIMOS
            driver.findElement(By.name("website[addresses][]")).sendKeys(Keys.ENTER);
            pausa(mlento);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void cerrarNavegador() {
        try {
            driver.quit();
        } catch (Exception e) {
        }

    }

    public void pausa(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {
        }
    }

    public void buscaGoogle(String texto, String escribirCon) {
        try {
            driver.get("https://www.google.com");
            pausa(medio);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement busGoogle = driver.findElement(By.id("lst-ib"));
            js.executeScript("arguments[0].value='" + texto + "';", busGoogle);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void mandaMail(String correoRecibe, String asunto, String deNombre, String mensaje) {
        String HOST_NAME = "smtp.gmail.com";
        int PORT = 465;
        String TEXT_PLAIN = "text/plain";

        String correoEnvia = "eucm2g@gmail.com";
        String password = "O2e20314#@64";

        HtmlEmail email = new HtmlEmail();
        email.setHostName(HOST_NAME);
        email.setSmtpPort(PORT);
        email.setSSLOnConnect(true);

        email.setAuthentication(correoEnvia, password);

        email.setSubject(asunto);
        try {
            email.setFrom(correoEnvia, deNombre, String.valueOf(StandardCharsets.UTF_8));
        } catch (EmailException ex) {
            Logger.getLogger(hitleap.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            email.addTo(correoRecibe);
        } catch (EmailException ex) {
            Logger.getLogger(hitleap.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            email.setHtmlMsg(mensaje);
        } catch (EmailException ex) {
            Logger.getLogger(hitleap.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(hitleap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void escribeLog(String texto) throws IOException{
        String ruta = "tmt.log";
        
        /*
        File archivo = new File(ruta);
        BufferedWriter bw;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("El fichero de texto ya estaba creado.");
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("Acabo de crear el fichero de texto.");
        }
        bw.close();
        */
        
        File archivo = new File(ruta);
        BufferedWriter bw;
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate= formatter.format(date);  
        bw = new BufferedWriter(new FileWriter(archivo,true));
        bw.write(strDate + "    " +  texto+"\r");
        bw.close();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    private void copy(String texto) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(texto);
        clipboard.setContents(selection, null);
    }

    private void paste() throws IOException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        DataFlavor flavor = DataFlavor.stringFlavor;
        if (clipboard.isDataFlavorAvailable(flavor)) {
            try {
                String text = (String) clipboard.getData(flavor);
                System.out.println(text);
            } catch (UnsupportedFlavorException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Decativamos la publicacion
    public void desactivaPublicacion(int id) {
        String sql = "UPDATE publicaciones SET activo = '0'  WHERE id = " + id;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //EJECUTAMOS EL COMANDO
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:tmt.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
