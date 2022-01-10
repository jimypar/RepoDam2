package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Compra extends JFrame {

	private static final long serialVersionUID = 1L;

	private ArrayList<Producto> productosComprados;

	public static int precio = 0;

	public Compra(ArrayList<Producto> productosComprados) {

		this.productosComprados = productosComprados;
		calcularPrecio();
		setVentana();
		iniciarComponentes();		

	}

	private void calcularPrecio() {

		for (int i = 0;i<productosComprados.size();i++) {
			this.precio += (productosComprados.get(i).getPrecio()*(Integer.parseInt(productosComprados.get(i).getCantidad().getText())));
		}

	}

	private void iniciarComponentes() {

		this.setTitle("Carrito compra");
		this.setBounds(400, 200, 400, 500);
		this.setDefaultCloseOperation(3);

	}

	private void setVentana() {

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints config1 = new GridBagConstraints();

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setAutoscrolls(true);
		this.add(scrollPane);

		JPanel panelProductos = new JPanel();
		panelProductos.setLayout(new GridBagLayout());

		GridBagConstraints config2 = new GridBagConstraints();

		for (int i = 0; i < productosComprados.size(); i++) {

			config2.gridy = i;
			config2.gridx = 0;

			JLabel imagen = new JLabel();
			ImageIcon image = new ImageIcon(productosComprados.get(i).getImagen());
			imagen.setBounds(100, 100, 100, 100);
			imagen.setIcon(new ImageIcon(
					image.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH)));
			panelProductos.add(imagen, config2);

			config2.gridy = i;
			config2.gridx = 1;

			JLabel nombre = new JLabel();
			nombre.setText(productosComprados.get(i).getNombre());
			panelProductos.add(nombre, config2);

			config2.gridy = i;
			config2.gridx = 2;

			JLabel precio = new JLabel();
			precio.setText(productosComprados.get(i).getPrecio() + "€");
			panelProductos.add(precio, config2);

			config2.gridy = i;
			config2.gridx = 3;
			JPanel panelCantidad = new JPanel();

			JLabel cantidad = productosComprados.get(i).getCantidad();
			panelCantidad.add(cantidad);

			panelProductos.add(panelCantidad, config2);

		}

		config1.gridy = 0;
		config1.gridx = 0;

		panel.add(panelProductos, config1);

		config1.gridy = 1;
		config1.gridx = 0;

		JLabel precio = new JLabel();
		precio.setText("El precio total es: " + this.precio + "€");
		panel.add(precio, config1);

		config1.gridy = 2;
		config1.gridx = 0;

		JButton comprar = new JButton();
		comprar.setText("Comprar");
		panel.add(comprar, config1);

		comprar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

}
