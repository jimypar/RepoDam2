package figuras;

public class Cuadrado extends Figura {

	private int lado;

	public Cuadrado(int lado) {
		super(0, 0);
		this.lado = lado;
	}

	
	public Cuadrado(int x, int y, int lado) {
		super(x, y);
		this.lado = lado;
	}
	
	

	@Override
	public int calcularArea() {

		return lado * lado;

	}

	@Override
	public
	void dibujar() {
		
		System.out.println("*******");
		System.out.println("*     *");
		System.out.println("*     *");
		System.out.println("*******");

	}

}
