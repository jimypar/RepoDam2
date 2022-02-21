package eventos;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

public class Evento02 extends JFrame{
	
	public JPanel panel;
	public JLabel etiqueta;
	public JButton boton;
	
	public Evento02() {
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
	
	interaccionBoton2();
	
}
private void interaccionBoton2(){
    boton.addMouseWheelListener(new MouseWheelListener(){

		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			// TODO Auto-generated method stub
			etiqueta.setText("valor:" + arg0.getWheelRotation() );
		}
        
    });
    
}


	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Evento02 e01 =new Evento02();
		e01.setVisible(true);
		
		
	}

}
