package cartelera;

public class Pelicula {

	//atributos
	private String titulo;
	private String genero;//accion-aventura...
	private String imagen;//ruta de la pelicula
	
	public Pelicula(String titulo, String genero, String imagen) {
		this.titulo= titulo;
		this.genero= genero;
		this.imagen=imagen;
		
		
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
