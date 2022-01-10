package programa;

import clases.Zoo;

public class Programa {

	public static void main(String[] args) {
		System.out.println("1.- Crear una instancia de Zoo llamada miZoo con 4 animales");
		int maxAnimales=4;
		Zoo miZoo = new Zoo(maxAnimales);
		System.out.println("instancia creada");
		
		System.out.println("2.- Doy de alta 4 animales");
		miZoo.altaAnimal("Timón", 10, "Suricato", "zoo 1");
		miZoo.altaAnimal("Pumba", 30, "Facóquero", "zoo 1");
		miZoo.altaAnimal("Simba", 60, "León", "zoo 1");
		miZoo.altaAnimal("Kowalski", 20, "Pingüino", "zoo 2");
		
		System.out.println("3.- Listar animales");
		miZoo.listaAnimales();
		
		System.out.println("4.- Buscar animal (Timón)");
		System.out.println(miZoo.buscarAnimal("Timón"));
		
		System.out.println("5.- Eliminar animal (Simba)");
		miZoo.eliminarAnimal("Simba");
		System.out.println("Listar animales");
		miZoo.listaAnimales();
		
		System.out.println("6.- Incorporar un nuevo animal (Dori)");
		miZoo.altaAnimal("Dori", 1, "pez cirujano", "zoo 2");
		System.out.println("Listar animales");
		miZoo.listaAnimales();
		
		System.out.println("7.- Modificar el nombre del animal (Dori)");
		miZoo.cambiarNombreAnimal("Dori", "Hola soy Dori");
		System.out.println("Listar animales");
		miZoo.listaAnimales();
		
		System.out.println("8.- Listar animales por zoo (zoo 1)");
		miZoo.listarAnimalesPorZoo("zoo 1");
		
	}

}
