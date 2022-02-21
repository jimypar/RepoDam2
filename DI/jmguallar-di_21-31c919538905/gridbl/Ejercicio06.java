package gridbl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class Ejercicio06 extends JFrame {
	JPanel panel = new JPanel();
	public Ejercicio06() {
		
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 300, 300);
		JButton boton = new JButton("BOTON01");
		JButton boton2 = new JButton("BOTON02");
		JButton boton3 = new JButton("BOTON03");
		JButton boton4 = new JButton("BOTON04");
		panel.setLayout(new GridBagLayout());
		GridBagConstraints config = new GridBagConstraints();
		config.gridx = 0;
		config.gridy = 0;
		config.weightx= 1.0;
		config.weighty= 1.0;
		config.anchor = GridBagConstraints.SOUTHEAST;
		
		panel.add(boton,config);
		config.gridx = 1;
		config.gridy = 0;
		config.weightx= 1.0;
		config.weighty= 1.0;
		config.anchor = GridBagConstraints.SOUTHWEST;
		
		panel.add(boton2,config);
		config.gridx = 0;
		config.gridy = 1;
		config.weightx= 1.0;
		config.weighty= 1.0;
		config.anchor = GridBagConstraints.NORTHEAST;
		
		panel.add(boton3,config);
		config.gridx = 1;
		config.gridy = 1;
		config.weightx= 1.0;
		config.weighty= 1.0;
		config.anchor = GridBagConstraints.NORTHWEST;
		
		panel.add(boton4,config);
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ejercicio06 e06 = new Ejercicio06();
		e06.setVisible(true);
	}

}
