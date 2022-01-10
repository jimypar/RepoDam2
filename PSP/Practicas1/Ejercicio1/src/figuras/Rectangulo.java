package figuras;

public class Rectangulo extends Figura {

	private int lado1;
	private int lado2;

	public Rectangulo(int lado1, int lado2) {
		super();
		this.lado1 = lado1;
		this.lado2 = lado2;
	}

	@Override
	public
	int calcularArea() {
		
		return lado1*lado2;
		
	}

}
