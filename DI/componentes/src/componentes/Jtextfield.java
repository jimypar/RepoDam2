package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Jtextfield extends JFrame {
	//componentes para JTextField
	JTextField jtf;
	JButton buttonjtf;
	JLabel labeljtf;
	String texto;
	
	
	
	public Jtextfield() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		textField();
			
	}
	private void textField() {
	//colocar los componenetes en la ventana
		jtf = new JTextField();
		jtf.setBounds(10, 10, 600, 50);
		this.add(jtf);
		
		
		buttonjtf = new JButton("Contenido del JTextField");
		buttonjtf.setBounds(10, 130,200,50);
		this.add(buttonjtf);
		
		labeljtf = new JLabel("");
		labeljtf.setBounds(10, 70, 600, 50);
		this.add(labeljtf);
		
		// escuchador
		buttonjtf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				texto = jtf.getText();//coge el contenido de jtextfield
				labeljtf.setText(texto);//pone el contenido en la etiqueta
			
			}
			
			
			
			
		});
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jtextfield tf = new Jtextfield();
		tf.setVisible(true);
		
	}

}
