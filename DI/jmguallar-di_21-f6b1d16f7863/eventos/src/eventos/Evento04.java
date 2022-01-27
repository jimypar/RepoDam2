package eventos;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Evento04 extends JFrame{
	
	public JPanel panel;
	public JLabel etiqueta;
	public JButton boton;
	public JTextField texto;
	
	public Evento04() {
		setVentana();
		initC();
		
		
		
	}
private void setVentana() {
	setSize(400,400);
	setTitle("Eventos");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.getContentPane().setBackground(Color.WHITE);
//	this.setLayout(null);
}
private void initC() {
	colocarPaneles();
	colocarEtiquetas();
	colocarBotones();
}

private void colocarPaneles() {
	panel = new JPanel();
	panel.setLayout(null);
	this.getContentPane().add(panel);
}

private void colocarEtiquetas() {
	etiqueta = new JLabel("Esto es una etiqueta ",SwingConstants.CENTER);
	etiqueta.setBounds(10, 10, 350, 100);
	etiqueta.setBackground(Color.RED);
	etiqueta.setForeground(Color.BLACK);
	etiqueta.setOpaque(true);
	panel.add(etiqueta);
	texto = new JTextField();
	texto.setBounds(10, 100, 80, 50);
	panel.add(texto);
	
}

private void colocarBotones() {
	boton = new JButton("Botón");
	boton.setBounds(100, 160, 200, 80);
	boton.setBorder(BorderFactory.createLineBorder(Color.YELLOW,10));
	boton.setBackground(Color.GREEN);
	panel.add(boton);
	
	interaccionBoton4();
	
}
private void interaccionBoton4(){
   texto.addKeyListener(new KeyListener(){

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			boton.setBackground(Color.DARK_GRAY);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			boton.setBackground(Color.GRAY);;
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
   });
}


	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Evento04 e01 =new Evento04();
		e01.setVisible(true);
		
		
	}

}
