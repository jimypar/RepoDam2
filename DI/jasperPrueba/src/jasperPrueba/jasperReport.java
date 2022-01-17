package jasperPrueba;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class jasperReport {
	
	public static void main(String[] args) {
		
		JasperPrint informeLleno = ReportGenerator.generarInformePeliculas();
		
		JasperViewer viewer = new JasperViewer(informeLleno);
		
		viewer.setVisible(true);
		
		try {
			JasperExportManager.exportReportToPdfFile(informeLleno, "shakira.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}

}
