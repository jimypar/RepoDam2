package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejercicio1 extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Ejercicio1() {
		
		setVentana();
		iniciarComponentes();		
		
	}
	
	private void setVentana() {
		
		this.setTitle("Disposiciones");
		this.setDefaultCloseOperation(3);
		
	}

	private void iniciarComponentes() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton boton = new JButton();
		boton.setText("69x25");
		boton.setSize(69, 25);
		boton.setPreferredSize(boton.getSize());
		boton.setBackground(Color.YELLOW);
		panel.add(boton);

		JButton boton1 = new JButton();
		boton1.setText("70x15");
		boton1.setSize(70, 15);
		boton1.setPreferredSize(boton1.getSize());
		panel.add(boton1);
		
		JButton boton2 = new JButton();
		boton2.setText("300x25");
		boton2.setSize(300, 25);
		boton2.setPreferredSize(boton2.getSize());
		panel.add(boton2);
		
		JButton boton3 = new JButton();
		boton3.setText("70x70");
		boton3.setSize(70,70);
		boton3.setPreferredSize(boton3.getSize());
		panel.add(boton3);
		
		JButton boton4 = new JButton();
		boton4.setText("70x25");
		boton4.setSize(70, 25);
		boton4.setPreferredSize(boton4.getSize());
		panel.add(boton4);

		this.getContentPane().add(panel);
		
	}

	

	public static void main(String[] args) {
		
		Ejercicio1 e1 = new Ejercicio1();
		e1.pack();
		e1.setVisible(true);

	}

}
