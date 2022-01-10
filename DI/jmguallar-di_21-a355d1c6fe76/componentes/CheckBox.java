package componentes;

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
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckBox cb = new CheckBox();
		cb.setVisible(true);
		
	}

}
