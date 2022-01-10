package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.sql.rowset.WebRowSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejercicio4 extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Ejercicio4() {
		
		setVentana();
		iniciarComponentes();		
		
	}
	
	private void setVentana() {
		
		this.setTitle("Ventana con GridBagLayout");
		this.setBounds(320, 200, 320, 200);
		this.setDefaultCloseOperation(3);
		
		
	}

	private void iniciarComponentes() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		JButton boton1 = new JButton();
		boton1.setText("Boton");
		
		
		GridBagConstraints config = new GridBagConstraints();
		config.weightx = 1;
		config.weighty = 1;
		config.anchor=GridBagConstraints.NORTH;
		config.fill = GridBagConstraints.HORIZONTAL;
		
		panel.add(boton1,config);
		
		this.getContentPane().add(panel);
		
	}

	

	public static void main(String[] args) {
		
		Ejercicio4 e1 = new Ejercicio4();
		e1.setVisible(true);

	}

}
