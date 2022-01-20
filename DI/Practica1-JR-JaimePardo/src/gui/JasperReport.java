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

    public static void start(){
        // TODO Auto-generated method stub
        JasperPrint informeLleno = ReporGenerator.generarInformeClientes();

        JasperViewer viewer = new JasperViewer(informeLleno,false);
        viewer.setVisible(true);
        try {
            JasperExportManager.exportReportToPdfFile(informeLleno, "Clientes_Pardo.pdf");
        } catch (JRException e) {
            // TODO Auto-generated catch block
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
        public static final String I_P = "Clientes_Pardo.jasper";

        public static JasperPrint generarInformeClientes() {

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

