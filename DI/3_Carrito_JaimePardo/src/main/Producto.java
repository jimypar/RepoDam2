package main;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Producto {

	String imagen;
	String nombre;
	double precio;
	JLabel cantidad;
	JButton mas,menos;

	public Producto(String nombre, double precio, String imagen) {
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public JLabel getCantidad() {
		return cantidad;
	}

	public void setCantidad(JLabel cantidad) {
		this.cantidad = cantidad;
	}

	public JButton getMas() {
		return mas;
	}

	public void setMas(JButton mas) {
		this.mas = mas;
	}

	public JButton getMenos() {
		return menos;
	}

	public void setMenos(JButton menos) {
		this.menos = menos;
	}

	
	
	
	

}
