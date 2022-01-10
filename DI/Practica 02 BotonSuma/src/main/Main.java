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



	public Main() {
		
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
		texto.setText("0");
		config.gridx=1;
		panel.add(texto,config);
		
		config.gridx = 0;
		config.gridy = 1;

		JButton restar = new JButton();
		restar.setText("Restar");
		panel.add(restar,config);
		
		config.gridx = 1;
		config.gridy = 1;
		
		
		JButton sumar = new JButton();
		sumar.setText("Sumar");
		panel.add(sumar,config);
		
		config.gridx = 2;
		config.gridy = 1;
		
		JButton cerrar = new JButton();
		cerrar.setText("Cerrar");
		panel.add(cerrar,config);
		
		this.getContentPane().add(panel);
		
		sumar.addMouseListener(new MouseListener() {
			
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
				
				int num = Integer.parseInt(texto.getText());
				num++;
				texto.setText(""+num);
				
			}
		});
		
		restar.addMouseListener(new MouseListener() {
			
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
				
				int num = Integer.parseInt(texto.getText());
				num--;
				texto.setText(""+num);
				
			}
		});
		
		cerrar.addMouseListener(new  MouseListener() {
			
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
		
		
	}

	

	public static void main(String[] args) {
		
		Main e1 = new Main();
		e1.setVisible(true);

	}

}

