package joption;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Jopton extends JFrame {
	//componentes para JOption
	JTextField jtfn, jtfa,jtfe;
	JButton jbtaceptar,jbtsalir;
	JLabel lbn, lba,lbe;
	String texto;
	JPanel blbotones = new JPanel();
	
	
	
	public Jopton() {
		this.setTitle("Opciones");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(4,2));
		blbotones.setLayout(new BoxLayout(blbotones, BoxLayout.Y_AXIS));
		initC();
		
	}

	private void initC() {
		insertarCampos();
			
	}
	private void insertarCampos() {
	//colocar los componenetes en la ventana
		lbn = new JLabel("Nombre");
		jtfn = new JTextField();
		lba = new JLabel("Apellido");
		jtfa = new JTextField();
		lbe = new JLabel("Edad:");
		jtfe = new JTextField();
		jbtaceptar = new JButton("Aceptar");
		jbtsalir = new JButton("Salir");
		this.add(lbn);
		this.add(jtfn);
		this.add(lba);
		this.add(jtfa);
		this.add(lbe);
		this.add(jtfe);
		blbotones.add(jbtaceptar);
		blbotones.add(jbtsalir);
		
		this.add(blbotones);
		
		jbtaceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nombre;
				int seleccion;
				
				//mensajes informativos 
				
				JOptionPane.showMessageDialog(null, "error desconocido", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Error Fatal", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "Aviso", "Aviso", JOptionPane.WARNING_MESSAGE);
				JOptionPane.showMessageDialog(null, "Tienes un mensaje", "Mensaje", JOptionPane.PLAIN_MESSAGE);
				
				//cuadro de dialogo con 2 opciones 
				seleccion=JOptionPane.showConfirmDialog(null, "Datos correctos?", "Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if (seleccion == JOptionPane.YES_OPTION)
					JOptionPane.showMessageDialog(null,	"Has dicho si", "aviso",JOptionPane.INFORMATION_MESSAGE);
				if (seleccion == JOptionPane.NO_OPTION)
					JOptionPane.showMessageDialog(null,	"NO?", "aviso", JOptionPane.INFORMATION_MESSAGE);
				if (seleccion == JOptionPane.CLOSED_OPTION)
					JOptionPane.showMessageDialog(null,	"Porqué no respondes?", "aviso", JOptionPane.INFORMATION_MESSAGE);
				
				//cuadro de dialogo con 3 opciones
				seleccion=JOptionPane.showConfirmDialog(null, "Datos correctos?", "Aviso",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
				if (seleccion == JOptionPane.YES_OPTION)
					JOptionPane.showMessageDialog(null,	"Has dicho si", "aviso",JOptionPane.INFORMATION_MESSAGE);
				if (seleccion == JOptionPane.NO_OPTION)
					JOptionPane.showMessageDialog(null,	"NO?", "aviso", JOptionPane.INFORMATION_MESSAGE);
				if (seleccion == JOptionPane.CLOSED_OPTION)
					JOptionPane.showMessageDialog(null,	"Porqué no respondes?", "aviso", JOptionPane.INFORMATION_MESSAGE);
				if (seleccion == JOptionPane.CANCEL_OPTION)
					JOptionPane.showMessageDialog(null, "¿Porué has cancelado?","aviso" , JOptionPane.INFORMATION_MESSAGE);
				
				//introducir un valor en un cuadro de dialogo
				
				nombre = JOptionPane.showInputDialog(null, "¿Cual es tu nombre?", "Introducir nombre", JOptionPane.QUESTION_MESSAGE);
				JOptionPane.showMessageDialog(null, "seguro que te llamas "+ nombre,
						"Nombre", JOptionPane.INFORMATION_MESSAGE);
				
				//cuadros de texto personalizados
				Object[] opciones = {"Si","No","No lo se"};
				
				seleccion = JOptionPane.showOptionDialog(null, "¿Deseas hacer una operacion?",
						"Mensaje de confirmacion", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0]);
		}
		});
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jopton tf = new Jopton();
		tf.setVisible(true);
		
	}

}
