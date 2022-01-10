package componentes;

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
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Combobox cb = new Combobox();
		cb.setVisible(true);
		
	}

}
