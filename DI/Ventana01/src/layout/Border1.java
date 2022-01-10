package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class Border1 extends JFrame{

	public Border1() {
		setVentana();
		initComponent();
	}
	
	

	private void setVentana() {
		
		this.setSize(400,400);
		this.setTitle("BorderLayout");
		//this.setResizable(false);
		this.setDefaultCloseOperation(3);
		
	}

	private void colocarPaneles() {
		
		//PANEL
		JPanel pcentro = new JPanel();
		pcentro.setBackground(Color.GRAY);
		//ETIQUETAS
		JLabel etiqueta = new JLabel("Centro");
		JButton button = new JButton("Centro");
		JLabel etiquetaN = new JLabel("Norte");
		JButton buttonN = new JButton("Norte");
		JLabel etiquetaS = new JLabel("Sur");
		JButton buttonS = new JButton("Sur");
		JLabel etiquetaE = new JLabel("Este");
		JButton buttonE = new JButton("Este");
		JLabel etiquetaO = new JLabel("Oeste");
		JButton buttonO = new JButton("Oeste");
		pcentro.add(button);
		pcentro.add(etiqueta);
		//pcentro.setLayout(new GridLayout(2,2));
		pcentro.setLayout(new BoxLayout(pcentro,BoxLayout.Y_AXIS));

		
		this.getContentPane().add(pcentro,BorderLayout.CENTER);
		
		
		JPanel pnorte = new JPanel();
		pnorte.setBackground(Color.BLACK);
		pnorte.add(buttonN);
		pnorte.add(etiquetaN);
		
		this.getContentPane().add(pnorte,BorderLayout.NORTH);
		
		JPanel psur = new JPanel();
		psur.setBackground(Color.GREEN);
		this.getContentPane().add(psur,BorderLayout.SOUTH);
		psur.add(buttonS);
		psur.add(etiquetaS);
		
		JPanel peste = new JPanel();
		peste.setBackground(Color.BLUE);
		this.getContentPane().add(peste,BorderLayout.EAST);
		peste.add(buttonE);
		peste.add(etiquetaE);
		
		JPanel poeste = new JPanel();
		poeste.setBackground(Color.RED);
		this.getContentPane().add(poeste,BorderLayout.WEST);
		poeste.add(buttonO);
		poeste.add(etiquetaO);
	}
	
	private void initComponent() {
		
		colocarPaneles();
		
	}

	

	public static void main(String[] args) {
		Border1 b1= new Border1();
		b1.setVisible(true);

	}

}
