package figuras;

public class Cuadrado extends Figura{
	
	private int lado;

	public Cuadrado(int lado) {
		super();
		this.lado = lado;
	}

	@Override
	public
	int calcularArea() {
		
		return lado*lado;
		
	}
	
	

}
