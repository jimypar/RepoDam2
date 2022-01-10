package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NuevaVentana extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int contador;


	public NuevaVentana(int contador) {
		
		this.contador = contador+1;
		setVentana();
		iniciarComponentes();		
		
	}




	private void setVentana() {
		
		this.setTitle("Ventana con GridBagLayout");
		this.setBounds(320, 200, 320, 200);
		this.setDefaultCloseOperation(3);
		
		
	}

	public void iniciarComponentes() {
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());

		GridBagConstraints config = new GridBagConstraints();
		
		TextField texto = new TextField();
		texto.setText("Ventana 2");
		config.gridx=0;
		panel2.add(texto,config);
		

		config.gridx = 0;
		config.gridy = 1;
		
		JButton cerrar = new JButton();
		cerrar.setText("Cerrar");
		panel2.add(cerrar,config);
		

		config.gridx = 1;
		config.gridy = 1;
		
		JButton volver = new JButton();
		volver.setText("Volver");
		panel2.add(volver,config);
		
		this.getContentPane().add(panel2);
		
		cerrar.addMouseListener(new MouseListener() {
			
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
				
				System.exit(0);
				
			}
		});
	
		
		volver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Main e1 = new Main(contador);
				e1.setVisible(true);
				dispose();
				
				
			}
		});
		
	}



}

