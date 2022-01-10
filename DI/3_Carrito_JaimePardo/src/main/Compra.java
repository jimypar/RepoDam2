package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

		for (int i = 0; i < productosComprados.size(); i++) {
			this.precio += (productosComprados.get(i).getPrecio()
					* (Integer.parseInt(productosComprados.get(i).getCantidad().getText())));
		}

	}

	private void iniciarComponentes() {

		this.setTitle("Carrito compra");
		this.setBounds(400, 200, 400, 500);
		this.setDefaultCloseOperation(3);

	}

	private void setVentana() {

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
			nombre.setText(productosComprados.get(i).getNombre() + "  ");
			panelProductos.add(nombre, config2);

			config2.gridy = i;
			config2.gridx = 2;

			JLabel precio = new JLabel();
			precio.setText(productosComprados.get(i).getPrecio() + " €  ");
			panelProductos.add(precio, config2);

			config2.gridy = i;
			config2.gridx = 3;
			JLabel cantidad = productosComprados.get(i).getCantidad();

			panelProductos.add(cantidad, config2);

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

		JButton volver = new JButton();
		volver.setText("Volver");
		volver.setBackground(new Color(128,0,128));
		volver.setForeground(Color.WHITE);
		volver.setBorderPainted(false);

		JPanel panelsur = new JPanel();

		panelsur.add(comprar, config1);
		panelsur.add(volver, config1);

		add(panelsur, BorderLayout.SOUTH);

		comprar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int ventanaYesNotCancel = JOptionPane.showConfirmDialog(null, "¿Quieres realizar la compra?", "Comprar",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (ventanaYesNotCancel == 0) {
					JOptionPane.showMessageDialog(null, "Compra realizada");
				}

			}
		});

		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PantallaPrincipal p1 = new PantallaPrincipal();
				p1.setVisible(true);
				dispose();

			}
		});

	}

}
