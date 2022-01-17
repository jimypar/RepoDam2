package jasperPrueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getMySQLConexion() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/sakila", USER, PASSWORD);
			
			return conn;
			
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
		} 
		
		return null;
	}
	
	
	
}
