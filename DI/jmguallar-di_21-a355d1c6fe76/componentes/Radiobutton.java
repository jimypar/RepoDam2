package componentes;

import javax.swing.*;

public class Radiobutton extends JFrame {
	
	public Radiobutton() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		radioButton();
			
	}
	private void radioButton() {
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Radiobutton rb = new Radiobutton();
		rb.setVisible(true);
		
	}

}
