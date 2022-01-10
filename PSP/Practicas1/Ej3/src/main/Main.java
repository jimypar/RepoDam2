package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Persona> personas = new ArrayList<Persona>();
		
		Persona pepe = new Persona("a", "a", "a", 1, "a");
		pepe.rellenarCoche("a", "a", "a", 1,2);
		pepe.rellenarDomicilio("a","a","a",0,"a","a");
		personas.add(pepe);
		
		Persona jose = new Persona("b", "b", "b", 2, "b");
		jose.rellenarCoche("a", "a", "a", 1,2);
		jose.rellenarDomicilio("b","b","b",0,"b","b");
		personas.add(jose);
		
		Persona dani = new Persona("c", "c", "c", 3, "c");
		dani.rellenarCoche("a", "a", "a", 1,2);
		dani.rellenarDomicilio("c","c","c",0,"c","c");
		personas.add(dani);
		
		Persona manolo = new Persona("d", "d", "d", 4, "d");
		manolo.rellenarCoche("a", "a", "a", 1,2);
		manolo.rellenarDomicilio("c","c","c",0,"c","c");
		personas.add(manolo);
		
		Persona antonio = new Persona("e", "e", "e",5, "e");
		antonio.rellenarCoche("a", "a", "a", 1,2);
		antonio.rellenarDomicilio("e","e","e",0,"e","e");
		personas.add(antonio);
		
	}

}
