package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Slider extends JFrame {
	//componentes para JTextField
	JSlider jsl;
	JButton buttonjtf;
	JLabel labeljtf;
	String texto;
	
	
	
	public Slider() {
		this.setTitle("JSlider");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		Jslider();
			
	}
	private void Jslider() {
	//colocar los componenetes en la ventana
		jsl = new JSlider();
		jsl.setBounds(10, 10, 600, 50);
		this.add(jsl);
		
					
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Slider tf = new Slider();
		tf.setVisible(true);
		
	}

}
