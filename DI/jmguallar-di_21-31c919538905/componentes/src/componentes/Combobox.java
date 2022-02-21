package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Combobox extends JFrame {
	
	public Combobox() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		comboBox();
			
	}
	private void comboBox() {
	String elementos[] = {"CheckBox", "JTextArea","JTextField","JRadioButton","ComboBox","PasswordField"};
	JComboBox jcb = new JComboBox(elementos);
	jcb.setBounds(10, 10, 200, 50);
	this.add(jcb);
	
	JButton boton = new JButton ("Ver");
	boton.setBounds(10, 100, 600, 50);
	this.add(boton);
	
	//accion
	
	boton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(jcb.getSelectedItem());
		}
		
		
		
		
	});
	
	
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Combobox cb = new Combobox();
		cb.setVisible(true);
		
	}

}
