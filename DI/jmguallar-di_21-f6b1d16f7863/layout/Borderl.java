package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Borderl  extends JFrame{

	public Borderl() {
		setVentana();
		iniciarComponentes();
		
	}
	private void setVentana() {
		this.setSize(400,400);
		this.setTitle("BorderLayout");
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		
	}
	
	private void colocarPaneles() {
		JPanel pcentro = new JPanel();
		JLabel eti = new JLabel("zona centro");
		JButton bot = new JButton("zona centro");
		JLabel eti5 = new JLabel("etiqueta 05");
		JButton bot5 = new JButton("Botón 5");
		JLabel eti1 = new JLabel("zona norte");
		JButton bot1 = new JButton("zona norte");
		JLabel eti2 = new JLabel("zona este");
		JButton bot2 = new JButton ("zona este");
		JLabel eti3 = new JLabel("zona sur");
		JButton bot3 = new JButton ("zona sur");
		JLabel eti4 = new JLabel("zona oeste");
		JButton bot4 = new JButton ("zona oeste");
		
		pcentro.setBackground(Color.WHITE);
		//pcentro.add(eti);
		
		
		pcentro.setLayout(new GridLayout (2,2));
		
	
		
		pcentro.add(bot);
		pcentro.add(eti);
		pcentro.add(eti5);
		pcentro.add(bot5);
		this.getContentPane().add(pcentro,BorderLayout.CENTER);
		
		JPanel pnorte = new JPanel();
		pnorte.setBackground(Color.GREEN);
		//pnorte.add(bot1);
		pnorte.add(eti1);
		this.getContentPane().add(pnorte,BorderLayout.NORTH);
		
		JPanel psur = new JPanel();
		psur.setBackground(Color.BLUE);
		//psur.add(bot3);
		psur.add(eti3);
		this.getContentPane().add(psur,BorderLayout.SOUTH);
		
		
		JPanel peste = new JPanel();
		peste.setBackground(Color.YELLOW);
		peste.setLayout(new BoxLayout(peste,BoxLayout.X_AXIS));
				bot2.setAlignmentY(RIGHT_ALIGNMENT);
				eti2.setAlignmentY(TOP_ALIGNMENT);
		peste.add(bot2);
		peste.add(eti2);
		
		this.getContentPane().add(peste,BorderLayout.EAST);
		
		
		JPanel poeste = new JPanel();
		poeste.setBackground(Color.RED);
		poeste.setLayout(new BoxLayout(poeste,BoxLayout.Y_AXIS));
		poeste.add(bot4);
		poeste.add(eti4);
		//poeste.add(eti4);
		this.getContentPane().add(poeste,BorderLayout.WEST);
		
	}
	private void iniciarComponentes() {
	colocarPaneles();	
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Borderl bl= new Borderl();
		bl.setVisible(true);
	}

}
