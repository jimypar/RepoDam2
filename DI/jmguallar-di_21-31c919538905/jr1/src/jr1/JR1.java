package jr1;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class JR1 {

		    public static void main(String[] args) throws JRException{
		        JasperPrint informeLleno = RGenerator.generarInformeActores();
		    //    JasperExportManager.exportReportToPdfFile(informeLleno,"InformeActores.pdf");
		        JasperViewer viewer = new JasperViewer(informeLleno);
		        viewer.setVisible(true);
		    }
		    
		    
		}

