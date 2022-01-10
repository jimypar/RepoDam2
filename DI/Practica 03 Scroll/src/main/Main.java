package main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main extends JFrame{
	

	private static final long serialVersionUID = 1L;
	JButton boton1;
	JButton boton2;
	JButton boton3;
	int num1 = 0;
	int num2 = 0;
	int num3 = 0;


	public Main() {
		
		setVentana();
		iniciarComponentes();		
		
	}
	
	private void setVentana() {
		
		this.setTitle("Cambio color RGB");
		this.setBounds(320, 200, 320, 200);
		this.setDefaultCloseOperation(3);
		
		
	}

	private void iniciarComponentes() {
		
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints config = new GridBagConstraints();
		
		config.gridx = 1;
		config.gridy = 0;
		
		JTextArea texto = new JTextArea("R,G,B");
		panel.add(texto,config);
		
		config.gridx = 0;
		config.gridy = 1;

		boton1 = new JButton("R:");
		panel.add(boton1,config);
		
		config.gridx = 1;
		config.gridy = 1;
				
		boton2 = new JButton("G:");
		panel.add(boton2,config);
		
		config.gridx = 2;
		config.gridy = 1;
		
		boton3 = new JButton("B:");
		panel.add(boton3,config);
		
		boton1.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				if (e.getWheelRotation()==1) {
					if (num1<255) {
						num1 = num1+(e.getWheelRotation());
						boton1.setText("R: "+num1);
						panel.setBackground(new Color(num1,num2,num3));
						texto.setText(num1+","+num2+","+num3);
					}
				}else {
					if (num1>0) {
						num1 = num1+(e.getWheelRotation());
						boton1.setText("R: "+num1);
						panel.setBackground(new Color(num1,num2,num3));
						texto.setText(num1+","+num2+","+num3);
					}
				}
				
			
			}
		});;
		
		
		boton2.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				if (e.getWheelRotation()==1) {
					if (num2<255) {
						num2 = num2+(e.getWheelRotation());
						boton2.setText("G: "+num2);
						panel.setBackground(new Color(num1,num2,num3));
						texto.setText(num1+","+num2+","+num3);
					}
				}else {
					if (num2>0) {
						num2 = num2+(e.getWheelRotation());
						boton2.setText("G: "+num2);
						panel.setBackground(new Color(num1,num2,num3));
						texto.setText(num1+","+num2+","+num3);
					}
				}
				
			}
		});
		
		
		boton3.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				if (e.getWheelRotation()==1) {
					if (num3<255) {
						num3 = num3+(e.getWheelRotation());
						boton3.setText("B: "+num3);
						panel.setBackground(new Color(num1,num2,num3));
						texto.setText(num1+","+num2+","+num3);
					}
				}else {
					if (num3>0) {
						num3 = num3+(e.getWheelRotation());
						boton3.setText("B: "+num3);
						panel.setBackground(new Color(num1,num2,num3));
						texto.setText(num1+","+num2+","+num3);
					}
				}
			}
		});
		
		this.getContentPane().add(panel);
		
		
		
	}

	

	public static void main(String[] args) {
		
		Main e1 = new Main();
		e1.setVisible(true);

	}

}

