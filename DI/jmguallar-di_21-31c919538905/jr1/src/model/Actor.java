package model;

public class Actor {
public Actor(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}
public Actor() {
	// TODO Auto-generated constructor stub
}
String nombre;
String apellido;
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
}
