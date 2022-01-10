package main;

public class Persona {

	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String dni;
	private Domicilio domicilio;
	private Coche coche;

	public Persona(String nombre, String apellido1, String apellido2, int telefono, String dni) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void rellenarDomicilio(String direccion, String puerta, String cv, int piso, String localidad, String provincia) {
		
		Domicilio domicilio = new Domicilio(direccion, puerta, cv, piso, localidad, provincia);
		this.domicilio = domicilio;
	}
	
	public void rellenarCoche(String marca, String modelo, String matricula, int cilindrada, int cv) {
		
		Coche coche = new Coche(marca, modelo, matricula, cilindrada, cv);
		this.coche = coche;
		
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", telefono="
				+ telefono + ", dni=" + dni + ", domicilio=" + domicilio + ", coche=" + coche + "]";
	}

}
