package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CheckBox extends JFrame {
	
	public CheckBox() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		checkBox();
		
			
	}
	private void checkBox() {
	JCheckBox jcb = new JCheckBox("Patatas");
	jcb.setBounds(10, 10, 640, 50);
	this.add(jcb);
		
	JCheckBox jcb1 = new JCheckBox("Chocolate");
	jcb1.setBounds(10, 110, 640, 50);
	this.add(jcb1);
		
	JCheckBox jcb2 = new JCheckBox("Manzanas");
	jcb2.setBounds(10, 210, 640, 50);
	this.add(jcb2);
		
	JLabel etiqueta = new JLabel("Selecciona los elementos que desees.");
	etiqueta.setBounds(10, 310, 640, 50);
	this.add(etiqueta);
	
	JButton boton = new JButton("Ver los elementos seleccionados");
	boton.setBounds(200, 380, 320, 50);
	this.add(boton);

	boton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String s ="";
			//si hemos cogido patatas
			if(jcb.isSelected()) {
				s+= "Hemos cogido patatas. ";
			}else {
				s+= "No hemos codigo patatas";
			}
			
			//si hemos cogido chocolate
			if(jcb1.isSelected()) {
				s+= " Hemos cogido chocolate. ";
			}else {
				s+= " No hemos codigo chocolate";
			}
			
			//si hemos cogido manzanas
			if(jcb2.isSelected()) {
				s+= " Hemos cogido manzanas. ";
			}else {
				s+= " No hemos codigo manzanas";
			}
			
			etiqueta.setText(s);
			
			
		}
		
		
	});
	
	
	
	
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckBox cb = new CheckBox();
		cb.setVisible(true);
		
	}

}
