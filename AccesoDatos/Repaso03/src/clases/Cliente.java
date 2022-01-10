package clases;

import java.time.LocalDate;

public class Cliente {

	private String dni;
	private String nombre;
	private LocalDate fechaNacimiento;

	public Cliente() {
		this.dni = "";
		this.nombre = "";
	}

	public Cliente(String dni, String nombre) {
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

}
