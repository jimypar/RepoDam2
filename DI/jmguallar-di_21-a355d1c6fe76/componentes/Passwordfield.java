package componentes;

import javax.swing.*;

public class Passwordfield extends JFrame {
	
	public Passwordfield() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		passwordField();
			
	}
	private void passwordField() {
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Passwordfield pf = new Passwordfield();
		pf.setVisible(true);
		
	}

}
