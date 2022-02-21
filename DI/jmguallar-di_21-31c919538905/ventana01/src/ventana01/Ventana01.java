package ventana01;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Ventana01 extends JFrame{
	public int c;
	public JPanel panel;
	public JLabel etiqueta;
	public JTextField jtf;
	public JButton boton,btnvolver,btnsalir;
	public JFrame ventana02;
	public JPanel panel2;
	static Ventana01 e01 ;
	
	
	public Ventana01() {
		setVentana();
		initC();
		
		
		
	}
private void setVentana() {
	setSize(400,400);
	setTitle("abrir otra ventana");
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
	panel2 = new JPanel();
	panel2.setLayout(null);
	ventana02 = new JFrame();
	
	this.getContentPane().add(panel);
}

private void colocarEtiquetas() {
	etiqueta = new JLabel("ventana 02 " ,SwingConstants.CENTER);
	etiqueta.setBounds(10, 55, 100, 20);
	etiqueta.setBackground(Color.RED);
	etiqueta.setForeground(Color.BLACK);
	etiqueta.setOpaque(true);
	ventana02.add(etiqueta);
	
}

private void colocarBotones() {
	boton = new JButton("Botón");
	jtf = new JTextField("");
	boton.setBounds(100, 160, 200, 80);
	boton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	boton.setBackground(Color.GRAY);
	jtf.setBounds(100, 80, 220, 40);
	
	jtf.setText("has pulsado " + c + " veces el boton de volver");
	panel.add(boton);
	panel.add(jtf);
	
	
	interaccionBoton();
	
}
private void colocarPanel2() {
	
	btnsalir = new JButton("salir");
	btnvolver = new JButton("volver");
	ventana02.setBounds(0, 0, 200, 200);
	ventana02.setDefaultCloseOperation(EXIT_ON_CLOSE);
	ventana02.getContentPane().setBackground(Color.GRAY);
	ventana02.setLayout(null);
	btnsalir.setBounds(10, 10, 100, 40);
	btnvolver.setBounds(10, 80, 100, 40);
	
	ventana02.add(btnsalir);
	ventana02.add(btnvolver);
	
	iteraccionBoton2();
	
	
		
}
private void iteraccionBoton2() {
	btnvolver.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c++;
			ventana02.dispose();
			
			e01.setVisible(true);
		}
		
		
		
		
	});
	btnsalir.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	} );
	}
	
private void interaccionBoton(){
    boton.addMouseListener(new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			pulsarsalir();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    	
        }
    		
    		);
}
    
private void pulsarsalir() {
	e01.dispose();
	//e01.setVisible(false);
	colocarPanel2();
	ventana02.setVisible(true);
	
}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	c= 0;
		e01 =new Ventana01();
		e01.setVisible(true);
		
		
	}

}
