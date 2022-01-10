package figuras;

public class Triangulo extends Figura {

	private int altura;
	private int base;

	public Triangulo(int altura, int base) {
		super(0, 0);
		this.altura = altura;
		this.base = base;
	}

	public Triangulo(int x, int y, int altura, int base) {
		super(x, y);
		this.altura = altura;
		this.base = base;
	}

	@Override
	public int calcularArea() {

		return (base * altura) / 2;

	}

	@Override
	public
	void dibujar() {

		System.out.println("    * ");
		System.out.println("   * * ");
		System.out.println("  *   * ");
		System.out.println(" *     * ");
		System.out.println("********* ");

	}

}
