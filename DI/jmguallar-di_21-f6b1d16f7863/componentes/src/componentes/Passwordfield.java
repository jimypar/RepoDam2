package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Passwordfield extends JFrame {
	
	public Passwordfield() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		passwordField();
		
			
	}
	//creamos el passwordfield
	private void passwordField() {
	JPasswordField jpf = new JPasswordField();
	jpf.setBounds(10, 10, 620, 50);
	this.add(jpf);
	//creacion de boton
	JButton boton = new JButton("ver contenido password");
	boton.setBounds(10, 100, 200, 50);
	this.add(boton);
	
	//añadimos la accion al boton
	boton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String s = "";
			for (int i = 0 ; i < jpf.getPassword().length; i ++) {
				
				s += jpf.getPassword()[i];
			}
			System.out.println(s);
			
			
		}
		
		
	});
	
	;
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Passwordfield pf = new Passwordfield();
		pf.setVisible(true);
		
	}

}
