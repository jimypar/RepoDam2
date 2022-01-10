package main;

public class Coche {

	private String marca;
	private String modelo;
	private String matricula;
	private int cilindrada;
	private int cv;

	public Coche(String marca, String modelo, String matricula, int cilindrada, int cv) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.cilindrada = cilindrada;
		this.cv = cv;
	}

	@Override
	public String toString() {
		return "Domicilio [marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + ", cilindrada="
				+ cilindrada + ", cv=" + cv + "]";
	}

}
