package clases;

import java.time.LocalDate;

public class Responsable {

	private String dni;
	private String nombre;
	private LocalDate fechaContratacion;

	public Responsable(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}

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

	@Override
	public String toString() {
		return "Responsable [dni=" + dni + ", nombre=" + nombre + ", fechaContratacion=" + fechaContratacion + "]";
	}

}
