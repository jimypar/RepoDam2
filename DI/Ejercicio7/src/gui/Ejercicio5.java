package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.sql.rowset.WebRowSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejercicio5 extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Ejercicio5() {
		
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
		GridBagConstraints config = new GridBagConstraints();
		
		
		JButton boton1 = new JButton();
		boton1.setText("Boton_01");
		panel.add(boton1,config);
		JButton boton2 = new JButton();
		boton2.setText("Boton_02");
		panel.add(boton2,config);
		config.gridy = 2;
		JButton boton3 = new JButton();
		boton3.setText("Boton_03");
		panel.add(boton3,config);
		JButton boton4 = new JButton();
		boton4.setText("Boton_04");
		panel.add(boton4,config);
		
		
		
		this.getContentPane().add(panel);
		
	}

	

	public static void main(String[] args) {
		
		Ejercicio5 e1 = new Ejercicio5();
		e1.setVisible(true);

	}

}
