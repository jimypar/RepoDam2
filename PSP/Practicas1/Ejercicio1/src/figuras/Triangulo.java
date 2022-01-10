package figuras;

public class Triangulo extends Figura {

	private int altura;
	private int base;

	public Triangulo(int altura, int base) {
		super();
		this.altura = altura;
		this.base = base;
	}

	@Override
	public
	int calcularArea() {

		return (base * altura) / 2;

	}

}
