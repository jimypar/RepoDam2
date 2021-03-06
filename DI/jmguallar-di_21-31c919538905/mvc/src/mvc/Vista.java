package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class Vista extends JFrame {
	/*panel principal que ocupara toda la pantalla
	 * que es el panel de fondo
	 */
	private JPanel panel;
	//panel scroll
	private JScrollPane scroll;
	//panel que contendra la pelicula
	private JPanel pnlPelicula;
	//panel que contendra la imagen
	private JPanel pnlImagen;
	//panel que contendra el texto
	private JPanel pnlTxt;
	//array de peliculas
	private Pelicula peliculas[];
	//para poner la pelicula en la pantalla
	private JLabel imagen, titulo,genero;
	
	//tama?os ventana
	private int size_x = 480, size_y=640;
	//pelicula
	private int pelicula_x =460, pelicula_y =260;
	//imagen
	private int imagen_x =180, imagen_y = 260;
	
	
	

	public Vista() {
		this.setTitle("Cartelera de cine");
		this.setSize(480,640);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		iniciarComponentes();
		}
	private void iniciarComponentes() {
		//panel de fondo
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		this.add(panel);
		
		//panel de scroll
		scroll = new JScrollPane(panel);
		panel.setAutoscrolls(true);
		this.add(scroll);
		
		
		//creamos peliculas
		crearPeliculas();
		
		for (int i=0; i<peliculas.length; i++) {
			crearPaneles();
			ponerInformacion(i);
			
		}
		
		
		
	}
		private void crearPeliculas() {
			peliculas = new Pelicula[4];
			
			peliculas[0] = new Pelicula("Ron da error","Animacion","rondaerror.jpg");
			peliculas[1] = new Pelicula("Fast and Furius 9","Acci?n","fandf9.jpg");
			peliculas[2] = new Pelicula("Sin tiempo 007","Acci?n","sintiempo.jpg");
			peliculas[3] = new Pelicula("La vida de Brian","Comedia","vdebrian.jpg");
		}
		
		private void crearPaneles() {
		//panel de pelicula
			pnlPelicula = new JPanel();
			pnlPelicula.setBackground(Color.WHITE);
			pnlPelicula.setMinimumSize(new Dimension(480,640));
			pnlPelicula.setLayout(new BorderLayout());
			panel.add(pnlPelicula);
			
			
		//panel de imagen
			pnlImagen = new JPanel();
			pnlImagen.setBackground(Color.WHITE);
			pnlPelicula.add(pnlImagen,BorderLayout.WEST);
						
		//panel de texto
			pnlTxt = new JPanel();
			pnlTxt.setBackground(Color.WHITE);
			pnlTxt.setPreferredSize(new Dimension(pelicula_x -imagen_x, imagen_y));
			pnlTxt.setLayout(new BoxLayout(pnlTxt,BoxLayout.Y_AXIS));
			pnlPelicula.add(pnlTxt,BorderLayout.EAST);
			
			
			
			
		}
		
	