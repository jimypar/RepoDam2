package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Practica1 extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Practica1() {
		
		setVentana();
		iniciarComponentes();		
		
	}
	
	private void setVentana() {
		
		this.setResizable(true);
		this.setBounds(400,150,500,500);
		this.setTitle("Ventana con GridBagLayout");
		this.setDefaultCloseOperation(3);
		
		
	}

	private void iniciarComponentes() {
		JPanel panelGrid = new JPanel();
		GridBagLayout g = new GridBagLayout();
		panelGrid.setLayout(g);
		GridBagConstraints config = new GridBagConstraints();
		
	
		
		
		config.gridx = 0;
		config.gridy = 0;
		config.weightx = 0.0;
		config.weighty = 0.0;
		JButton boton1 = new JButton();
		boton1.setText("Abrir");
		panelGrid.add(boton1,config);
		
		config.gridx = 1;
		config.gridy = 0;
		config.weightx = 0.0;
		config.weighty = 0.0;
		JButton boton2 = new JButton();
		boton2.setText("Guardar");
		panelGrid.add(boton2,config);
		
		config.gridx = 2;
		config.gridy = 0;
		config.weightx = 0.0;
		config.weighty = 0.0;
		JButton boton3 = new JButton();
		boton3.setText("Guardar como");
		panelGrid.add(boton3,config);
		
		config.gridx = 3;
		config.gridy = 0;
		config.weightx = 0.0;
		config.weighty = 0.0;
		config.anchor = GridBagConstraints.WEST;
		JButton boton4 = new JButton();
		boton4.setText("Cerrar");
		panelGrid.add(boton4,config);
		
		config.fill = GridBagConstraints.BOTH;
		
		config.gridx = 0;
		config.gridy = 1;
		config.weightx = 0.0;
		config.weighty = 0.0;
		config.gridwidth = 2;
		JButton boton5 = new JButton();
		boton5.setText("Buscar");
		panelGrid.add(boton5,config);
		
		config.gridx = 2;
		config.gridy = 1;
		config.weightx = 0.0;
		config.weighty = 0.0;
		JTextField jtf = new JTextField("texto");
		panelGrid.add(jtf,config);
		
		config.gridx = 0;
		config.gridy = 3;
		config.gridwidth = 2;
		config.weighty = 0.1;
		config.weighty = 0.1;
		JButton boton6 = new JButton();
		boton6.setText("Ayuda");
		panelGrid.add(boton6,config);
		
		config.gridx = 2;
		config.gridy = 3;
		config.weightx = 0.1;
		config.weighty = 0.1;
		JTextArea jta = new JTextArea("Area de texto");
		panelGrid.add(jta,config);
		
		
		this.getContentPane().add(panelGrid);
		
	}

	

	public static void main(String[] args) {
		
		Practica1 e1 = new Practica1();
		e1.setVisible(true);

	}

}

