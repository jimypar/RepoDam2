package figuras;

public class Circulo extends Figura {

	private int radio;

	public Circulo(int radio) {
		super(0, 0);
		this.radio = radio;
	}

	public Circulo(int x, int y, int radio) {
		super(x, y);
		this.radio = radio;
	}

	@Override
	public int calcularArea() {

		return (int) (Math.PI * (Math.sqrt(radio)));

	}

	@Override
	public
	void dibujar() {

		System.out.println("     ****    ");
		System.out.println("   **    **    ");
		System.out.println("  *        *    ");
		System.out.println("  *        *    ");
		System.out.println("   **    **    ");
		System.out.println("     ****    ");
	}

}
