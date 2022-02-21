package jr1;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Actor;
import model.ActorDAO;
import model.ActorDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RGenerator {
	
	   public static final String I_A = "InformeSimpleActores.jasper";
  
	    
	    public static JasperPrint generarInformeActores(){
	        try {
	            ActorDAO dao = new ActorDAO();
	            List<Actor> actores = dao.leerTodo();
	            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(actores);
	            JasperPrint reporteLleno = JasperFillManager.fillReport
	            		(I_A, new HashMap(), dataSource);
	            return reporteLleno;
	        } catch (JRException ex) {
	            Logger.getLogger(RGenerator.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }
	    
	}

