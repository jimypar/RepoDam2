package eventos;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Evento01 extends JFrame{
	
	public JPanel panel;
	public JLabel etiqueta;
	public JButton boton;
	
	public Evento01() {
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
	
}

private void colocarBotones() {
	boton = new JButton("Botón");
	boton.setBounds(100, 160, 200, 80);
	boton.setBorder(BorderFactory.createLineBorder(Color.YELLOW,10));
	boton.setBackground(Color.GREEN);
	panel.add(boton);
	
	iteracionBoton();
	
}
private void iteracionBoton() {
	boton.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//al pulsar y soltar
			boton.setBackground(Color.BLUE);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//al acceder al boton
			etiqueta.setText("acabas de entrar en el boton");
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//al salir del boton
			boton.setBackground(Color.GREEN);
			System.exit(0);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//al pulsar el boton
			etiqueta.setBackground(Color.YELLOW);
			etiqueta.setText("estas pulsando el boton de roedor");
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//al soltar el boton
			etiqueta.setBackground(Color.RED);
			etiqueta.setText("Esto es una etiqueta ");
			
		}
		
		
		
		
	});
	
	
}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Evento01 e01 =new Evento01();
		e01.setVisible(true);
		
		
	}

}
