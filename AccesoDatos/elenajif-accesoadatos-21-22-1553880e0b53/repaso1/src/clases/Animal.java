package clases;

public class Animal {
	//atributos
	private String nombreAnimal;
	private String especie;
	private double peso;
	private String zoo;		
	
	//contructor
	public Animal(String nombreAnimal) {
		this.nombreAnimal=nombreAnimal;
	}
	
	//setter y getter
	public String getNombreAnimal() {
		return nombreAnimal;
	}
	public void setNombreAnimal(String nombreAnimal) {
		this.nombreAnimal = nombreAnimal;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getZoo() {
		return zoo;
	}
	public void setZoo(String zoo) {
		this.zoo = zoo;
	}
	
	//toString
	@Override
	public String toString() {
		return "Datos del Animal [nombreAnimal=" + nombreAnimal + ", especie=" + especie + ", peso=" + peso + ", zoo=" + zoo
				+ "]";
	}
	
}
