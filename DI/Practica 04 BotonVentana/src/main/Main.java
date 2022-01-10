package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int contador;


	public Main(int contador) {
		
		this.contador = contador;
		setVentana();
		iniciarComponentes();		
		
	}
	
	

	private void setVentana() {
		
		this.setTitle("Ventana con GridBagLayout");
		this.setBounds(320, 200, 320, 200);
		this.setDefaultCloseOperation(3);
		
		
	}

	private void iniciarComponentes() {
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints config = new GridBagConstraints();
		
		TextField texto = new TextField();
		texto.setText("Has presionado "+contador+" veces volver");
		config.gridx=0;
		panel.add(texto,config);
		
		config.gridx = 0;
		config.gridy = 1;
		
		
		
		JButton nuevo = new JButton();
		nuevo.setText("Nueva Ventana");
		panel.add(nuevo,config);
		
		this.getContentPane().add(panel);
	
		
		nuevo.addMouseListener(new  MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				NuevaVentana e2 = new NuevaVentana(contador);
				e2.setVisible(true);
				dispose();

			}
		});
		
		
	}

	

	public static void main(String[] args) {
		
		Main e1 = new Main(0);
		e1.setVisible(true);

	}

}

