package main;

import java.util.ArrayList;

import figuras.Circulo;
import figuras.Cuadrado;
import figuras.Figura;
import figuras.Rectangulo;
import figuras.Triangulo;

public class Main {

	public static void main(String[] args) {

		/**
		 * 
		 * -Punto de origen 
		 * -Mover() 
		 * -Dibujar()
		 * 
		 */

		ArrayList<Figura> listaFiguras = new ArrayList<Figura>();

		Cuadrado unCuadrado = new Cuadrado(2);

		Rectangulo unRectangulo = new Rectangulo(2, 4);

		Triangulo unTriangulo = new Triangulo(2, 4);

		Circulo unCirculo = new Circulo(2);

		listaFiguras.add(unCuadrado);
		listaFiguras.add(unRectangulo);
		listaFiguras.add(unTriangulo);
		listaFiguras.add(unCirculo);

		for (int i = 0; i < listaFiguras.size(); i++) {
			
			Figura fig = listaFiguras.get(i);
			
			fig.dibujar();

		}

		
		for (Figura figura : listaFiguras) {
			Figura fig = figura;
			
			fig.calcularArea();
		}

	}

}
