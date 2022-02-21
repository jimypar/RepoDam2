package pruebajavahelp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;

public class PruebaJavaHelp {


	/** Ventana manual */
	private JDialog manual;

	/** Ventana principal */
	private JFrame instalar;

	/** Item de menú para la ayuda */
	private JMenuItem itemAyuda;

	/** Boton para que muestre la ventana manual */
	private JButton botonManual;

	/**
	 * Crea una instanacia 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new PruebaJavaHelp();
	}

	/**
	 * Crea las ventanas, pone la ayuda y visualiza la ventana principal.
	 */
	public PruebaJavaHelp() {
		creaVentanaInstalar();
		creaVentanaManual();

		botonManual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manual.setVisible(true);
			}

			
		});

		ponLaAyuda();
		ventanaPrincipal();
	}

	/**
	 * Mostrara la pantalla principal.
	 */
	private void ventanaPrincipal() {
		instalar.pack();
		instalar.setVisible(true);
		instalar.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Hace que el item del menu y la pulsacion de F1 visualicen la ayuda.
	 */
	private void ponLaAyuda() {
		try {
			// Carga el fichero de ayuda
			File fichero = new File("src\\pruebajavahelp\\help\\help_set.hs");
			//File fichero = new File("src"+File.separator+"pruebajavahelp\\help\\help_set.hs");
			URL hsURL = fichero.toURI().toURL();

			// Crea el HelpSet y el HelpBroker
			HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
			HelpBroker hb = helpset.createHelpBroker();

			// Pone ayuda a item de menu al pulsarlo y a F1 en ventana
			// manual e instalar.
			hb.enableHelpOnButton(itemAyuda, "aplicacion", helpset);
						//si pulsamos F1
			hb.enableHelpKey(manual.getContentPane(), "manual",
					helpset);
			hb.enableHelpKey(instalar.getContentPane(), "instalar",
					helpset);
	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  ventana instalr
	 */
	private void creaVentanaInstalar() {
		instalar = new JFrame("Ventana principal");
		JMenuBar menuBar = new JMenuBar();
		itemAyuda = new JMenuItem("Ayuda");
		menuBar.add(itemAyuda);
		botonManual = new JButton("instalar");
		instalar.setJMenuBar(menuBar);
		instalar.getContentPane().setLayout(new FlowLayout());
		instalar.getContentPane().add(botonManual);
		
	}

	/**
	 * ventana manual
	 */
	private void creaVentanaManual() {
		manual = new JDialog(instalar, "Ventana del Manual");
		manual.getContentPane().setLayout(new FlowLayout());
		manual.getContentPane().add(new JLabel("ventana del Manual"));
		manual.getContentPane().add(new JButton("No hago nada"));
		manual.pack();
	}
}
