package layout;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GLayout extends JFrame{

	public GLayout() {
		setVentanaGL();
		initGL();
		
		
	}
	JPanel panelGL = new JPanel();
	
	private void initGL() {
		// TODO Auto-generated method stub
		panelGL.setLayout(new GridLayout(2,5));
		panelGL.add(new JButton("01"));
		panelGL.add(new JButton("02"));
		panelGL.add(new JButton("03"));
		panelGL.add(new JButton("04"));
		panelGL.add(new JButton("05"));
		panelGL.add( new JButton("10"));
		panelGL.add(new JButton("11"));
		panelGL.add(new JButton("12"));
		panelGL.add(new JButton("13"));
		panelGL.add(new JButton ("14"));
		
	}
	private void setVentanaGL() {
		// TODO Auto-generated method stub
		this.setTitle("Border Layout");
		this.setBounds(600,300,600,350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panelGL);
	
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GLayout gl = new GLayout();
		gl.setVisible(true);
		
		
		
		
	}

}
