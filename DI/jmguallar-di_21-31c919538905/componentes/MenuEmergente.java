package componentes;

	
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JFrame;
	import javax.swing.JMenuItem;
	import javax.swing.JPanel;
	import javax.swing.JPopupMenu;

	public class MenuEmergente {
	//creacion de un menu emergente
		//pulsando el boton derecho del roedor
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	MEmergente ME = new MEmergente();
	ME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ME.setVisible(true);
		}

	}
	class MEmergente extends JFrame{
		
		public MEmergente () {
			
		this.setBounds(600,300,600,400);
		add (new LaminaE());
			
		}
		
	}
	class LaminaE extends JPanel{
		
		public LaminaE () {
			//creamos el menu emergente
		JPopupMenu emergente = new JPopupMenu();
		//opciones del menu emergente
			JMenuItem azul = new JMenuItem("Azul");
			JMenuItem rojo = new JMenuItem("Rojo");
			JMenuItem verde = new JMenuItem("Verde");
			
			//añadimos el elemento al menu emergente
			
			//ponemos el boton a la escucha
			azul.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setBackground(Color.BLUE);
					}
				});
			rojo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setBackground(Color.RED);
					}
				});
			
			verde.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setBackground(Color.GREEN);
					}
				});
			//añadimos menu para cuando pulsados
			//el boton derecho del roedor
			emergente.add(azul);
			emergente.add(rojo);
			emergente.addSeparator();
			emergente.add(verde);
			setComponentPopupMenu(emergente);
			
		}
		
		
		
	}



