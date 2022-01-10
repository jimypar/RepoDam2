package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class InicioSesion extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> usuarios = new ArrayList<>();

	public InicioSesion() {

		introducirProductos();
		setVentana();
		setResizable(false);
		iniciarComponentes();

	}

	private void introducirProductos() {

		usuarios.add(new Usuario("user","1234"));

	}

	private void setVentana() {

		this.setTitle("Productos");
		this.setBounds(400, 200, 400, 500);

	}
	
	

	private void iniciarComponentes() {

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel imagen = new JLabel();
		ImageIcon image = new ImageIcon("images/Logo.png");
		imagen.setBounds(200, 200, 200, 200);
		imagen.setIcon(new ImageIcon(
				image.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(imagen);
		
		
		JPanel panellogin = new JPanel(new GridBagLayout());
		panellogin.setBackground(Color.WHITE);
		GridBagConstraints config = new GridBagConstraints();
		
		config.insets = new Insets(3,3,3,3);
		
		config.gridx = 0;
		config.gridy = 0;
		config.fill = 1;
		
		JLabel txtUsuario = new JLabel("Usuario: ");
		panellogin.add(txtUsuario,config);
		
		config.gridx = 1;
		config.gridy = 0;
		config.fill = 1;
	
		
		JTextField usuario = new JTextField("",10);
		panellogin.add(usuario,config);
		
		config.gridx = 0;
		config.gridy = 1;
		config.fill = 1;
		
		JLabel txtPassword = new JLabel("Contraseña: ");
		panellogin.add(txtPassword,config);
		
		config.gridx = 1;
		config.gridy = 1;
		config.fill = 1;
		
		JPasswordField password = new JPasswordField("",10);
		panellogin.add(password,config);
		
		config.gridx = 0;
		config.gridy = 2;
		config.gridwidth = 0;
		
		JButton entrar = new JButton();
		entrar.setText("ENTRAR");
		entrar.setBackground(new Color(128,0,128));
		entrar.setForeground(Color.WHITE);
		entrar.setBorderPainted(false);
		panellogin.add(entrar,config);
		
		config.gridx = 0;
		config.gridy = 3;
		config.gridwidth = 0;		
		
		JButton registrar = new JButton();
		registrar.setText("REGISTRARSE");
		registrar.setBackground(new Color(128,0,128));
		registrar.setForeground(Color.WHITE);
		registrar.setBorderPainted(false);
		
		
		
		panellogin.add(registrar,config);
		
		add(panellogin);
		
		add(panel,BorderLayout.NORTH);
		
		
		entrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
									
					if (!usuario.getText().equals("") || !password.getText().equals("")) {
						if (comprobar(usuario.getText(),password.getText())) {
							PantallaPrincipal e1 = new PantallaPrincipal();
							e1.setVisible(true);
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Hay campos sin rellenar");
					}	
			}
		});
		
		
		registrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!usuario.getText().equals("") && !password.getText().equals("")) {
					if (comprobar(usuario.getText(),password.getText())) {
						JOptionPane.showMessageDialog(null, "Este usuario ya esta registrado");
					}else {
						usuarios.add(new Usuario(usuario.getText(),password.getText()));
						JOptionPane.showMessageDialog(null, "Usuario registrado");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Hay campos sin rellenar");
				}
				
			}
		});

	}
	
	
	private boolean comprobar(String user, String pass) {
		
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getUsername().equals(user) && usuarios.get(i).getPassword().equals(pass)) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) {

		InicioSesion e1 = new InicioSesion();
		e1.setVisible(true);

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



