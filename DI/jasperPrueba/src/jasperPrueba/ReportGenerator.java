package jasperPrueba;

import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ReportGenerator {
	
	public static String I_P = "shakira.jasper";

	public static JasperPrint generarInformePeliculas() {
		
		try {
			JasperPrint informeLleno = JasperFillManager.fillReport(I_P, new HashMap(), Conexion.getMySQLConexion());
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
