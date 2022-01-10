package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productos = new ArrayList<>();
	
	public Main() {
		
		
		introducirProductos();
		
		setVentana();
		setResizable(false);
		iniciarComponentes();		
		
	}
	
	

	private void introducirProductos() {
		
		productos.add(new Producto("Movil",230,"images/movil.jpg"));
		productos.add(new Producto("Portatil",600,"images/portatil.jpg"));
		productos.add(new Producto("PlayStation 5 ",500,"images/ps5.jpg"));
		productos.add(new Producto("Monitor",200,"images/monitor.jpg"));
		productos.add(new Producto("Airpods",300,"images/airpods.jpg"));
		
		
	}



	private void setVentana() {
		
		this.setTitle("Productos");
		this.setBounds(400, 200, 400, 500);
		this.setDefaultCloseOperation(3);
		
		
	}

	private void iniciarComponentes() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		
		GridBagConstraints config1 = new GridBagConstraints();	

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setAutoscrolls(true);
		this.add(scrollPane);
		
		
		
		JPanel panelProductos = new JPanel();
		panelProductos.setLayout(new GridBagLayout());
		panelProductos.setBackground(Color.WHITE);
		
		
		GridBagConstraints config2 = new GridBagConstraints();
	    
		
		for (int i = 0; i < productos.size(); i++) {
			
			config2.gridy=i;
			config2.gridx=0;
					
			JLabel imagen = new JLabel();
	        ImageIcon image = new ImageIcon(productos.get(i).getImagen());
	        imagen.setBounds(100,100,100,100);
	        imagen.setIcon(new ImageIcon(image.getImage().getScaledInstance(imagen.getWidth(),imagen.getHeight(), Image.SCALE_SMOOTH)));
	        panelProductos.add(imagen,config2);
			
	        config2.gridy=i;
			config2.gridx=1;
	        
	        JLabel nombre = new JLabel();
	        nombre.setText(productos.get(i).getNombre());
			panelProductos.add(nombre,config2);
			
			config2.gridy=i;
			config2.gridx=2;
			
			JLabel precio = new JLabel();
			precio.setText(productos.get(i).getPrecio()+"€");
			panelProductos.add(precio,config2);
			
			config2.gridy=i;
			config2.gridx=3;
			JPanel panelCantidad = new JPanel();
			panelCantidad.setBackground(Color.WHITE);
			
			JButton res = new JButton();
			res.setText("-");
			panelCantidad.add(res);
			productos.get(i).setMenos(res);
			
			JLabel cantidad = new JLabel();
			cantidad.setText("0");
			panelCantidad.add(cantidad);
			productos.get(i).setCantidad(cantidad);
			
			JButton add = new JButton();
			add.setText("+");
			panelCantidad.add(add);
			productos.get(i).setMas(add);
			
			
			panelProductos.add(panelCantidad,config2);
			
		}
		
		config1.gridy = 0;
		config1.gridx = 0;
				
		panel.add(panelProductos,config1);
		
		config1.gridy = 1;
		config1.gridx = 0;
		
		JLabel titulo = new JLabel();
		titulo.setText("BeBuy");
		titulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		
		JPanel panelNorte = new JPanel();
		
		panelNorte.add(titulo,config1);
		
		add(panelNorte,  BorderLayout.NORTH);
		
		config1.gridy = 1;
		config1.gridx = 0;
		
		JButton comprar = new JButton();
		comprar.setText("Comprar");
		
		JPanel panelsur = new JPanel();
		
		panelsur.add(comprar,config1);
		
		add(panelsur,  BorderLayout.SOUTH);
		
		
	}
	


	public static void main(String[] args) {
		
		Main e1 = new Main();
		e1.setVisible(true);

	}

}

