package mvc;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Modelo {


private void ponerInformacion(int i) {
	//imagen de la pelicula
		ImageIcon imageicon = new ImageIcon(peliculas[i].getImagen());
		Icon icon = new ImageIcon(imageicon.getImage().getScaledInstance(imagen_x, imagen_y, Image.SCALE_DEFAULT));
		
		imagen = new JLabel();
		imagen.setIcon(icon);
		pnlPelicula.add(imagen);
	//etiqueta del titulo
		
		titulo = new JLabel(peliculas[i].getTitulo());
		pnlTxt.add(titulo);
	//etiqueta del genero
		genero = new JLabel(peliculas[i].getGenero());
		pnlTxt.add(genero);
		
	}
}