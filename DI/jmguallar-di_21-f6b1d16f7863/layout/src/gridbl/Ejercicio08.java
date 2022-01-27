package gridbl;


import java.awt.*;
import javax.swing.*;


public class Ejercicio08  extends JFrame{
	
	JButton j1;
	JButton j2;
	JButton j3;
	JButton j4;
	JButton j5;
	JTextField j6;
	JButton j7;
	JButton j8;
	JTextArea j9;

	public Ejercicio08(){
	init08();
	this.setSize(600,600);
	}
	
	private void init08() {
	j1 = new JButton ("Abrir");
	j2 = new JButton ("Guardar");
	j3 = new JButton ("Guardar como");
	j4 = new JButton("Cerrar");
	j5 = new JButton("Buscar");
	j6 = new JTextField();
	j7 = new JButton ("Vacío");
	j8 = new JButton("Ayuda");
	j9 = new JTextArea("Texto de area");
	j9.setBackground (Color.GRAY); 

	GridBagLayout layout08 = new GridBagLayout();
	this.add (j1); // Agrega el componente a jframe
	        this.add(j2);
	        this.add(j3);
	        this.add(j4);
	        this.add(j5);
	        this.add(j6);
	        this.add(j7);
	        this.add(j8);
	        this.add(j9);
  	        GridBagConstraints s = new GridBagConstraints();
	        s.fill = GridBagConstraints.BOTH;
	        s.gridx =0;
	        s.gridy = 0;
	        s.gridwidth = 1; 
	            
	        s.weightx =0; 
	        s.weighty =0;
	        layout08.setConstraints(j1, s);
	        s.gridx =1;
	        s.gridy = 0;
	        
	        s.gridwidth =1;
	       s.weightx =0;
	        s.weighty =0;
	        layout08.setConstraints(j2, s);
	        s.gridx =2;
	        s.gridy = 0;
	        
	        s.gridwidth =1;
	        s.weightx =0;
	        s.weighty =0;
	        layout08.setConstraints(j3, s);
	        s.gridx =3;
	        s.gridy = 0;
	        
	        s.gridwidth=1;
	        s.weightx=0;
	       s.weighty=0;
	        layout08.setConstraints(j4, s);
	        s.gridx =0;
	        s.gridy = 1;
	        
	        s.gridwidth =2;
	        s.weightx =0;
	        s.weighty =0;
	        layout08.setConstraints(j5, s);
	        s.gridx =2;
	        s.gridy = 1;
	        	        
	        s.gridwidth=4;
	        s.weightx=0.2;
	       s.weighty=0;
	        layout08.setConstraints(j6, s);
	        s.gridx =2;
	        s.gridy = 1;
	        
	        s.gridwidth=1;
	        s.weightx=0;
	        s.weighty=0;
	        layout08.setConstraints(j7, s);
	        s.gridx =0;
	        s.gridy = 2;
	        
	        s.gridwidth=2;
	        s.weightx = 0;
	        s.weighty=1;
	        layout08.setConstraints(j8, s);
	        s.gridx =2;
	        s.gridy = 2;
	        
	        s.gridwidth=5;
	        s.weightx = 0;
	        s.weighty=1;
	        layout08.setConstraints(j9, s);
	    this.setLayout(layout08);
	        
	}
	public static void main(String[] args) {
		Ejercicio08 g = new Ejercicio08();
	
	g.setVisible(true);


	}

}
