package componentes;

import javax.swing.*;

public class Jtextfield extends JFrame {
	
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
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jtextfield tf = new Jtextfield();
		tf.setVisible(true);
		
	}

}
