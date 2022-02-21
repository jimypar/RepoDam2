package gridbl;


import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Ejercicio05 extends JFrame {

	public JPanel panels,paneli,panelglobal;
	public JButton boton1, boton2,boton3,boton4,boton5;
	
	
	public Ejercicio05() {
		setVentana05();
		initC05();
				
	}
	
	private void setVentana05() {
		this.setSize(300, 300);
		this.setDefaultCloseOperation(3);
		this.setTitle("Layouts");
		this.setResizable(false);
	}
	private void initC05() {
		
		definirPaneles05();

		colocarBotones05();
		
	}
	
	private void definirPaneles05() {
		//panel inferior
		paneli = new JPanel();
		paneli.setLayout(new BorderLayout(10,10));
		
		//panel superior
		panels = new JPanel();
		panels.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//panel general
		panelglobal = new JPanel();
		panelglobal.setLayout(new BorderLayout());
		this.getContentPane().add(panelglobal);
		
	}
	
	private void colocarBotones05() {
		//botones en el panel inferior
		boton5 = new JButton("Botón 05");
		boton4 = new JButton("Botón 04");
		boton3 = new JButton("Botón 03");
		paneli.add(boton5,BorderLayout.EAST);
		paneli.add(boton4,BorderLayout.CENTER);
		paneli.add(boton3,BorderLayout.WEST);
		panelglobal.add(paneli,BorderLayout.SOUTH);
		
		//botones en el panel superior
		boton1 = new JButton("Botón 01");
		boton2 = new JButton("Botón 02");
		panels.add(boton1);
		panels.add(boton2);
		panelglobal.add(panels,BorderLayout.NORTH);
		
			}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ejercicio05 e05 = new Ejercicio05();
		e05.setVisible(true);
		
		
	}

}
