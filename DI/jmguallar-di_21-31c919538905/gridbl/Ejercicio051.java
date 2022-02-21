package gridbl;


import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Ejercicio051 extends JFrame {

	public JPanel panelc,panelglobal;
	public JButton boton1, boton2,boton3,boton4,boton5;
	
	
	public Ejercicio051() {
		setVentana051();
		initC051();
				
	}
	
	private void setVentana051() {
		this.setSize(300, 300);
		this.setDefaultCloseOperation(3);
		this.setTitle("Layouts");
		this.setResizable(false);
	}
	private void initC051() {
		
		definirPaneles051();

		colocarBotones051();
		
	}
	
	private void definirPaneles051() {
	
		
		//panel general
		panelglobal = new JPanel();
		panelglobal.setLayout(new GridBagLayout());
		
		
		this.getContentPane().add(panelglobal);
		
	}
	
	private void colocarBotones051() {
	
		GridBagConstraints c = new GridBagConstraints();
		//definicion de los botones y colocacion de cada uno
boton1 = new JButton("Botón 01");
		
		c.gridx = 0;
		c.gridy = 0;
		
		c.weightx= 1.0;
		c.weighty= 1.0;
		c.anchor =GridBagConstraints.NORTH;
		panelglobal.add(boton1,c);
		
boton2 = new JButton("Botón 02");
		
		c.gridx = 1;
		c.gridy = 0;
		
		c.weightx= 1.0;
		c.weighty= 1.0;
		
		panelglobal.add(boton2,c);

		
boton3 = new JButton("Botón 03");
		c.anchor =GridBagConstraints.SOUTH;
		c.gridx = 0;
		c.gridy = 2;
		
		c.weightx= 1.0;
		c.weighty= 1.0;
		panelglobal.add(boton3,c);
		
boton4 = new JButton("Botón 04");
		
		c.gridx = 1;
		c.gridy = 2;
		
		c.weightx= 1.0;
		c.weighty= 1.0;
		panelglobal.add(boton4,c);
		
boton5 = new JButton("Botón 05");
		
		c.gridx = 2;
		c.gridy = 2;
		
		c.weightx= 1.0;
		c.weighty= 1.0;
		panelglobal.add(boton5,c);
			}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ejercicio051 e051 = new Ejercicio051();
		e051.setVisible(true);
		
		
	}

}
