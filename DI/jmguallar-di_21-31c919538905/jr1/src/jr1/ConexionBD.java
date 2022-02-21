package jr1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
	
	    private static final String HOST = "localhost";
	    private static final String DB = "sakila";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";
	    
	    public static Connection getMySQLConnection()  {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String connectionURL = "jdbc:mysql://" + HOST + ":3308/" + DB;
	            Connection conn = DriverManager.getConnection(connectionURL, USER, PASSWORD);
	            return conn;
	        } catch (ClassNotFoundException | SQLException ex) {
	            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }
	    
	}

