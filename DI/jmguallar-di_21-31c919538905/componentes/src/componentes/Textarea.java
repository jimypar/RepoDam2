package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Textarea extends JFrame {
	
	public Textarea() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		textArea();
			
	}
	private void textArea() {
	JTextArea jta = new JTextArea("Esto es un JTextArea");
	jta.setEditable(true);
	jta.setBounds(10, 10, 620, 360);
	this.add(jta);
	
	JScrollPane jsp = new JScrollPane(jta,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
	jsp.setBounds(10, 10, 620, 360);
	this.add(jsp);
	JButton boton = new JButton("Coger el contenido");
	boton.setBounds(10, 400, 620, 50);
	this.add(boton);
	
	boton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String s = "";
			s += jta.getText();
			System.out.println(s);
			
		}
		
		
		
	});
	
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Textarea ta = new Textarea();
		ta.setVisible(true);
		
	}

}
