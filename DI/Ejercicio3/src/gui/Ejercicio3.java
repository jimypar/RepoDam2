package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejercicio3 extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Ejercicio3() {
		
		setVentana();
		iniciarComponentes();		
		
	}
	
	private void setVentana() {
		
		this.setTitle("Ventana con GridBagLayout");
		this.setBounds(320, 200, 320, 200);
		this.setDefaultCloseOperation(3);
		
		
	}

	private void iniciarComponentes() {
		
		GridBagLayout layout = new GridBagLayout();
		JPanel panel = new JPanel();
		panel.setLayout(layout);

		JButton boton1 = new JButton();
		boton1.setText("Boton");
		panel.add(boton1);
		
		GridBagConstraints config = new GridBagConstraints();
		
		

		this.getContentPane().add(panel);
		
	}

	

	public static void main(String[] args) {
		
		Ejercicio3 e1 = new Ejercicio3();
		e1.setVisible(true);

	}

}
