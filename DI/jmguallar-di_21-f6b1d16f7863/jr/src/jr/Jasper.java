package jr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Jasper {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JasperPrint informeLleno = ReporGenerator.generarInformePeliculas();
		//JasperViewer viewer = new JasperViewer(informeLleno,false);
		//viewer.setVisible(true);
	  JasperPrint informeLleno = ReporGenerator.generarInformePorCalificacion("NC-17");
	  JasperViewer viewer = new JasperViewer(informeLleno, false);
	  viewer.setVisible(true);
		try {
			//JasperExportManager.exportReportToPdfFile(informeLleno, "tururu.pdf");
			JasperExportManager.exportReportToPdfFile(informeLleno, "calificacion.pdf");
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/sakila",USER, PASSWORD);	
			return conn;
			
		} catch (ClassNotFoundException | SQLException ex ) {
			// TODO Auto-generated catch block
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
		}
			
		return null;
		
		
	}
	
	
}
public static class ReporGenerator {
public static final String I_P = "tururu.jasper";
//indicamos el nombre del fichero jasper
public static final String I_PARAMETRO = "informe_parametro.jasper";
//primer metodo informe global de peliculas
public static JasperPrint generarInformePeliculas() {
	
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
//creamos el metodo pasar por parametros la calificacion
public static JasperPrint generarInformePorCalificacion(String rating) {
	// pasamos el HasMap que contendra el parametro de tipo string
	// y el objeto como valor 
	
	HashMap<String,	Object> parametros = new HashMap<>();
	//en los parametros pongo los valores como se llama 
	//en el informe y el valor que paso
	parametros.put("calificacion", rating);
	
	try {
		JasperPrint informeLleno = JasperFillManager.fillReport
				(I_PARAMETRO, parametros, Conexion.getMySQLConexion());
		return informeLleno;
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
		
}
}

}
