package main;

import figuras.Circulo;
import figuras.Cuadrado;
import figuras.Rectangulo;
import figuras.Triangulo;

public class Main {

	public static void main(String[] args) {
		
		Cuadrado unCuadrado = new Cuadrado(2);
		System.out.println(unCuadrado.calcularArea());
		
		Rectangulo unRectangulo = new Rectangulo(2, 4);
		System.out.println(unRectangulo.calcularArea());
		
		Triangulo unTriangulo = new Triangulo(2, 4);
		System.out.println(unTriangulo.calcularArea());
		
		Circulo unCirculo = new Circulo(2);
		System.out.println(unCirculo.calcularArea());

	}

}
