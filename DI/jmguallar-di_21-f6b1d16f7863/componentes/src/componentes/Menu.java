package componentes;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JFrame {
	
	public Menu() {
		this.setTitle("Componentes");
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		initC();
		
	}

	private void initC() {
		menu();
			
	}
	private void menu() {
	//barra de menu
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
	//menu colocado en la barra de menus
		JMenu menu = new JMenu("Menu");
		menubar.add(menu);
	//colocamos un JMenu en un Menu
		JMenuItem item = new JMenuItem("Item");//podemos poner texto, icono, texto + ico
		menu.add(item);
	//colocamos un serparador
		menu.addSeparator();
	//item con accion
		Image icono = new ImageIcon("fiesta.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JMenuItem salir = new JMenuItem("Salir", new ImageIcon(icono));
		menu.add(salir);
				
			}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu ta = new Menu();
		ta.setVisible(true);
		
	}

}
