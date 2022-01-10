package main;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame implements ChangeListener{
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JSlider jsl;
	JButton buttonjtf;
	JLabel labeljtf;
	String texto;
	
	
	
	public Main() {
		this.setTitle("JSlider");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		
		panel = new JPanel(new GridLayout(2,1));
			
			
		jsl = new JSlider(5,60,12);
		jsl.setMinorTickSpacing(1);
		jsl.setMajorTickSpacing(5);
		jsl.setPaintTicks(true);
		jsl.setPaintLabels(true);
		jsl.setFont(new Font("Serif", Font.ITALIC,20));
		jsl.addChangeListener(this);
		
		panel.add(jsl);
		
		labeljtf = new JLabel();
		labeljtf.setText("Hola Mundo");
		panel.add(labeljtf);
		
		panel.setBounds(10, 10, 600, 200);
		this.add(panel);
		
		
	}
	
	
	public static void main(String[] args) {

		Main tf = new Main();
		tf.setVisible(true);
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		
		labeljtf.setFont(new Font("Serif", Font.BOLD, jsl.getValue()));
		
	}

}
