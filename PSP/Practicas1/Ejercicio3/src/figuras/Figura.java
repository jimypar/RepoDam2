package figuras;

public abstract class Figura {

	private int x;
	private int y;

	public Figura(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void mover(int x, int y) {

		this.x = this.x + x;
		this.y = this.y + y;

	}

	public abstract int calcularArea();
	public abstract void dibujar();

}
