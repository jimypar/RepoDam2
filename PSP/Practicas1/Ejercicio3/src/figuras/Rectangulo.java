package figuras;

public class Rectangulo extends Figura {

	private int lado1;
	private int lado2;

	public Rectangulo(int lado1, int lado2) {
		super(0, 0);
		this.lado1 = lado1;
		this.lado2 = lado2;
	}

	@Override
	public int calcularArea() {

		return lado1 * lado2;

	}

	@Override
	public
	void dibujar() {
		
		System.out.println("*****************");
		System.out.println("*               *");
		System.out.println("*               *");
		System.out.println("*****************");

	}

}
