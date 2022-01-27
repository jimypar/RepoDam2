package componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Radiobutton extends JFrame {
	//componentes de JRationButton
	JRadioButton jrb1,jrb2;
	
	
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
	//colocamos en la pantalla
		jrb1 = new JRadioButton("Menor de 30");
		jrb1.setBounds(10, 10, 100, 50);
		jrb1.setSelected(true);
		this.add(jrb1);
		
		jrb2 = new JRadioButton("Mayor de 30");
		jrb2.setBounds(10, 70, 100, 50);
		jrb2.setSelected(false);
		this.add(jrb2);
		
		//escuchadores
		//escuchador jrb1
		
		jrb1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(jrb1.isSelected()) {//pasar de desactivado a activado
					jrb2.setSelected(false);
				}else {
					jrb1.setSelected(true);
					jrb2.setSelected(false);
				}
					
			}
						
		});
		//accion sobre jrb2
		
		jrb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jrb2.isSelected()) {//pasar de desactivado a activado
					jrb1.setSelected(false);
					
				}else {
					jrb1.setSelected(false);
					jrb2.setSelected(true);
				
			}
			
			}		
			
		});
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Radiobutton rb = new Radiobutton();
		rb.setVisible(true);
		
	}

}
