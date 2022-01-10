package clases;

public class Animal {

	private String nombreAnimal;
	private String especie;
	private double peso;
	private String zoo;

	public Animal() {
		super();
		this.nombreAnimal = "";
		this.especie = "";
		this.peso = 0;
		this.zoo = "";
	}

	public Animal(String nombreAnimal, String especie, double peso, String zoo) {
		super();
		this.nombreAnimal = nombreAnimal;
		this.especie = especie;
		this.peso = peso;
		this.zoo = zoo;
	}

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

	@Override
	public String toString() {
		return "Datos del Animal [nombreAnimal=" + nombreAnimal + ", especie=" + especie + ", peso=" + peso + ", zoo=" + zoo
				+ "]";
	}

	
	
}
