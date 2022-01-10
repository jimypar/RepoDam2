package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PantallaPrincipal extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productos = new ArrayList<>();
	private ArrayList<Producto> productosComprados = new ArrayList<>();
	public PantallaPrincipal() {

		introducirProductos();
		setVentana();
		setResizable(false);
		iniciarComponentes();

	}

	private void introducirProductos() {

		productos.add(new Producto("Movil", 230, "images/movil.jpg"));
		productos.add(new Producto("Portatil", 600, "images/portatil.jpg"));
		productos.add(new Producto("PlayStation 5 ", 500, "images/ps5.jpg"));
		productos.add(new Producto("Monitor", 200, "images/monitor.jpg"));
		productos.add(new Producto("Airpods", 300, "images/airpods.jpg"));

	}

	private void setVentana() {

		this.setTitle("Productos");
		this.setBounds(400, 200, 400, 500);

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

			config2.gridy = i;
			config2.gridx = 0;

			JLabel imagen = new JLabel();
			ImageIcon image = new ImageIcon(productos.get(i).getImagen());
			imagen.setBounds(100, 100, 100, 100);
			imagen.setIcon(new ImageIcon(
					image.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH)));
			panelProductos.add(imagen, config2);

			config2.gridy = i;
			config2.gridx = 1;

			JLabel nombre = new JLabel();
			nombre.setText(productos.get(i).getNombre());
			panelProductos.add(nombre, config2);

			config2.gridy = i;
			config2.gridx = 2;

			JLabel precio = new JLabel();
			precio.setText(productos.get(i).getPrecio() + "€");
			panelProductos.add(precio, config2);

			config2.gridy = i;
			config2.gridx = 3;
			JPanel panelCantidad = new JPanel();
			panelCantidad.setBackground(Color.WHITE);

			JButton res = new JButton();
			res.setText("-");
			res.setBackground(new Color(128,0,128));
			res.setForeground(Color.WHITE);
			res.setBorderPainted(false);
			panelCantidad.add(res);
			productos.get(i).setMenos(res);
			res.addActionListener(this);

			JLabel cantidad = new JLabel();
			cantidad.setText("0");
			panelCantidad.add(cantidad);
			productos.get(i).setCantidad(cantidad);

			JButton add = new JButton();
			add.setText("+");
			add.setBackground(new Color(128,0,128));
			add.setForeground(Color.WHITE);
			add.setBorderPainted(false);
			panelCantidad.add(add);
			productos.get(i).setMas(add);
			add.addActionListener(this);

			panelProductos.add(panelCantidad, config2);

		}

		config1.gridy = 0;
		config1.gridx = 0;

		panel.add(panelProductos, config1);

		config1.gridy = 1;
		config1.gridx = 0;

		JLabel titulo = new JLabel();
		titulo.setText("BeBuy");
		titulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));

		JPanel panelNorte = new JPanel();

		panelNorte.add(titulo, config1);

		
		add(panelNorte, BorderLayout.NORTH);

		config1.gridy = 1;
		config1.gridx = 0;

		JButton comprar = new JButton();
		comprar.setText("Comprar");
		comprar.setBackground(new Color(128,0,128));
		comprar.setForeground(Color.WHITE);
		comprar.setBorderPainted(false);

		JPanel panelsur = new JPanel();

		panelsur.add(comprar, config1);

		add(panelsur, BorderLayout.SOUTH);

		comprar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean comprado = false;

				for (int i = 0; i < productos.size(); i++) {
					if (Integer.parseInt(productos.get(i).getCantidad().getText()) > 0) {
						productosComprados.add(productos.get(i));
						comprado = true;
					}
				}

				if (!comprado) {
					JOptionPane.showMessageDialog(null, "No hay nada en el carrito");
				} else {
					Compra compra = new Compra(productosComprados);
					compra.setVisible(true);
					dispose();
				}
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < productos.size(); i++) {
			if (e.getSource().equals(productos.get(i).getMenos())) {
				if (Integer.parseInt(productos.get(i).getCantidad().getText()) > 0) {
					productos.get(i).getCantidad()
							.setText(String.valueOf(Integer.parseInt(productos.get(i).getCantidad().getText()) - 1));
				}
			}
			if (e.getSource().equals(productos.get(i).getMas())) {
				productos.get(i).getCantidad()
						.setText(String.valueOf(Integer.parseInt(productos.get(i).getCantidad().getText()) + 1));
			}
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		System.out.println("adios");

		int n = JOptionPane.showConfirmDialog(e.getWindow(), "¿DESEA CERRAR EL PROGRAMA?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "GRACIAS POR UTILIZAR EL PROGRAMA");
			System.exit(0);
		}

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
