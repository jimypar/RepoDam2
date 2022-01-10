package main;

import clases.Zoo;

public class Main {

	public static void main(String[] args) {
		
		int maxAnimales=4;
		Zoo miZoo = new Zoo(maxAnimales);
		miZoo.altaAnimal("Pepe", 20, "mamifero", "zoo1");
		miZoo.altaAnimal("Manuel", 30, "Tiranosaurio Rex", "zoo1");
		miZoo.altaAnimal("Jose", 50, "helicoptero", "zoo2");
		miZoo.altaAnimal("Daniel", 73, "homosapiens", "zoo1");
		
		System.out.println("Lista animales:");
		miZoo.listarAnimales();
		
		System.out.println("Buscar manuel");
		System.out.println(miZoo.buscarAnimal("Manuel"));
		System.out.println("Lista animales:");
		miZoo.listarAnimales();
		
		System.out.println("Eliminar daniel");
		miZoo.eliminarAnimal("Daniel");
		System.out.println("Lista animales:");
		miZoo.listarAnimales();
		
		System.out.println("Incorporar mono");
		miZoo.altaAnimal("Mono", 20, "Simio", "zoo 2");
		System.out.println("Lista animales:");
		miZoo.listarAnimales();
		
		System.out.println("Modificar animal");
		miZoo.cambiarNombreAnimal("Manuel", "Antonio");
		System.out.println("Lista animales:");
		miZoo.listarAnimales();
		
		System.out.println("Animales zoo1");
		miZoo.ListarAnimalesPorZoo("zoo1");

	}

}
