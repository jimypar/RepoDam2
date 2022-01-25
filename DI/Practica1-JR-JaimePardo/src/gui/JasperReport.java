package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;

public class JasperReport {

    public static void generar(String nombre){
        JasperPrint informeLleno = ReporGenerator.generarInformeClientes(nombre);

        JasperViewer viewer = new JasperViewer(informeLleno,false);
        viewer.setVisible(true);
        try {
            JasperExportManager.exportReportToPdfFile(informeLleno, nombre+".pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }


    public static class Conexion {

        private static final String USER ="root";
        private static final String PASSWORD ="";

        public static Connection getMySQLConexion() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/jrJaimePardo",USER, PASSWORD);
                return conn;

            } catch (ClassNotFoundException | SQLException ex ) {
                // TODO Auto-generated catch block
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
            }

            return null;


        }


    }
    public static class ReporGenerator {
        public static String I_P;

        public static JasperPrint generarInformeClientes(String nombre) {

            I_P = nombre+".jasper";

            try {
                JasperPrint informeLleno = JasperFillManager.fillReport
                        (I_P ,new HashMap(), Conexion.getMySQLConexion());
                return informeLleno;
            } catch (JRException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

    }

}

