package gridbl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class Ejercicio01 extends JFrame {
	JPanel panel = new JPanel();
	public Ejercicio01() {
		
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 300, 300);
		JButton boton = new JButton("BOTON");
		panel.setLayout(new GridBagLayout());
		GridBagConstraints config = new GridBagConstraints();
		config.weightx= 1.0;
		config.weighty= 1.0;
		//ejercicio 02 boton aparece a la derecha
		//config.anchor=GridBagConstraints.EAST;
		//separa 5 pixeles del lateral
		//config.insets= new Insets(5,5,5,5);
		//ejercicio 03 boton horizontal arriba
		//config.fill = GridBagConstraints.HORIZONTAL;
		//config.anchor= GridBagConstraints.NORTH;
		//ejercicio 04 boton en el centro de la ventana y vertical
		config.fill = GridBagConstraints.VERTICAL;
		
		panel.add(boton,config);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ejercicio01 e01 = new Ejercicio01();
		e01.setVisible(true);
	}

}
