package clases;

import java.time.LocalDate;

public class Responsable {
	//atributos
	private String dni;
	private String nombre;
	private LocalDate fechaContratacion;
	
	//constructor
	public Responsable(String dni, String nombre) {
		this.dni=dni;
		this.nombre=nombre;
	}
	
	//setter y getter
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	//toString
	@Override
	public String toString() {
		return "Responsable [dni=" + dni + ", nombre=" + nombre + ", fechaContratacion=" + fechaContratacion + "]";
	}
	
	
}
