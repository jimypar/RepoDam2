package componentes;

import javax.swing.*;

public class Textarea extends JFrame {
	
	public Textarea() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		textArea();
			
	}
	private void textArea() {
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Textarea ta = new Textarea();
		ta.setVisible(true);
		
	}

}
