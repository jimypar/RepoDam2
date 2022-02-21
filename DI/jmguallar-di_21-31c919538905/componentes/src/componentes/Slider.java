package componentes;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider extends JFrame {
	//componentes para JTextField
	JSlider jsl;
	JLabel labeljtf = new JLabel();;
	int texto;
	
	
	
	public Slider() {
		this.setTitle("JSlider");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		initC();
		
	}

	private void initC() {
		Jslider();
			
	}
	private void Jslider() {
	//colocar los componenetes en la ventana
		jsl = new JSlider(100,200,120);
		//marcas pequeñas
		jsl.setMinorTickSpacing(5);
		//marcas grandes
		jsl.setMajorTickSpacing(25);
		// pintamos las marcas
		jsl.setPaintTicks(true);
		// cambiamos el tipo de letra de las etiquetas
		jsl.setFont(new Font("Serif", Font.ITALIC,12));
		// visualizamos valor numerico
		jsl.setPaintLabels(true);
		// cambio de orientacion
		jsl.setOrientation(SwingConstants.HORIZONTAL);
		// imantar a las marcas
		//jsl.setSnapToTicks(true);
		jsl.setBounds(10, 10, 700, 300);
		this.add(jsl,BorderLayout.CENTER);
		labeljtf.setBounds(10, 10, 600, 10);
		texto=jsl.getValue();
		labeljtf.setText(String.valueOf(texto));
		
		this.add(labeljtf,BorderLayout.SOUTH);
		jsl.addChangeListener(new EventoSlider());
		//this.add(labeljtf,BorderLayout.SOUTH);
	}
	
	private class EventoSlider implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			texto=jsl.getValue();
			labeljtf.setText(String.valueOf(texto));
			
			add(labeljtf,BorderLayout.SOUTH);
			
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Slider tf = new Slider();
		tf.setVisible(true);
		
	}

}
