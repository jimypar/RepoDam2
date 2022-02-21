package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class FLayout extends JFrame{
	
	public FLayout () {
		setVentana();
		init();
	}
	private void setVentana() {
		setTitle("Botones");
		setBounds(600,350,600,300);
		this.setDefaultCloseOperation(3);
		this.add(panel);	
	}
	private JButton boton1,boton2,boton3,boton4, boton5;
	Dimension dim = new Dimension(69,25);
	String h="";
	String w = "";
	JPanel panel = new JPanel();
	private void init() {
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		boton1 = new JButton();
		boton2 = new JButton();
		boton3 = new JButton();
		boton4 = new JButton();
		boton5 = new JButton();
		h = String.valueOf(dim.height);
		w = String.valueOf(dim.width);
		boton1.setText(w + "x"+ h);
		boton1.setBackground(Color.YELLOW);
		boton1.setPreferredSize(dim);
		panel.add(boton1);
		boton2.setText("300 x 25");
		boton2.setPreferredSize(new Dimension(300,25));
		panel.add(boton2);
		boton3.setText("75 x 75");
		boton3.setPreferredSize(new Dimension(75,75));
		panel.add(boton3);
	
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FLayout fl = new FLayout();
		fl.pack();
		fl.setVisible(true);
	}

}
