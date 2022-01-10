package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejercicio2 extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Ejercicio2() {
		
		setVentana();
		iniciarComponentes();		
		
	}
	
	private void setVentana() {
		
		this.setTitle("Disposiciones");
		this.setBounds(500,400, 500, 400);
		this.setDefaultCloseOperation(3);
		
	}

	private void iniciarComponentes() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,4));

		JButton boton1 = new JButton();
		boton1.setText("00");
		panel.add(boton1);
		
		JButton boton2 = new JButton();
		boton2.setText("01");
		panel.add(boton2);
		
		JButton boton3 = new JButton();
		boton3.setText("02");
		panel.add(boton3);
		
		JButton boton4 = new JButton();
		boton4.setText("03");
		panel.add(boton4);
		
		JButton boton5 = new JButton();
		boton5.setText("10");
		panel.add(boton5);
		
		JButton boton6 = new JButton();
		boton6.setText("11");
		panel.add(boton6);
		
		JButton boton7 = new JButton();
		boton7.setText("12");
		panel.add(boton7);
		
		JButton boton8 = new JButton();
		boton8.setText("13");
		panel.add(boton8);

		this.getContentPane().add(panel);
		
	}

	

	public static void main(String[] args) {
		
		Ejercicio2 e1 = new Ejercicio2();
		e1.setVisible(true);

	}

}
