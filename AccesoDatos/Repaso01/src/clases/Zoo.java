package clases;

public class Zoo {

	private Animal[] animales;

	public Zoo(int maxAnimales) {

		this.animales = new Animal[maxAnimales];

	}

	public void altaAnimal(String nombreAnimal, double peso, String especie, String zoo) {

		for (int i = 0; i < animales.length; i++) {
			if (animales[i] == null) {
				animales[i] = new Animal(nombreAnimal, especie, peso, zoo);
				break;
			}
		}

	}

	public Animal buscarAnimal(String nombreAnimal) {

		for (int i = 0; i < animales.length; i++) {
			if (animales[i] == null) {
				if (animales[i].getNombreAnimal().equals(nombreAnimal)) {
					return animales[i];
				}
			}
		}

		return null;

	}
	
	public void eliminarAnimal(String nombreAnimal) {
		
		for (int i = 0; i < animales.length; i++) {
			if (animales[i] == null) {
				if (animales[i].getNombreAnimal().equals(nombreAnimal)) {
					animales[i]=null;
				}
			}
		}
		
	}
	
	public void listarAnimales() {
		
		for (int i = 0; i < animales.length; i++) {
			if (animales[i] != null) {
				System.out.println(animales[i].getNombreAnimal());
			}
		}
			
	}
	
	public void cambiarNombreAnimal(String nombreAnimal, String nombreAnimal2) {
		
		for (int i = 0; i < animales.length; i++) {
			if (animales[i] != null) {
				if (animales[i].getNombreAnimal().equals(nombreAnimal)) {
					animales[i].setNombreAnimal(nombreAnimal2);
				}
			}
		}
		
	}

	public void ListarAnimalesPorZoo(String zoo) {
		
		for (int i = 0; i < animales.length; i++) {
			if (animales[i] != null) {
				if (animales[i].getZoo().equals(zoo)) {
					System.out.println(animales[i]);
				}
			}
		}
		
	}
	
}
