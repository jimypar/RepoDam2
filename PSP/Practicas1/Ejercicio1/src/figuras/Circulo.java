package figuras;

public class Circulo extends Figura {

	private int radio;

	public Circulo(int radio) {
		super();
		this.radio = radio;
	}

	@Override
	public int calcularArea() {

		return (int) (Math.PI * (Math.sqrt(radio)));

	}

}
