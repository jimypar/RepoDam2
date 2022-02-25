package clases;

public class Zoo {

	// creo array de clase Animal
	private Animal[] animales;

	// constructor que inicializa el vector de animales
	// con el tamaño recibido como parametro
	public Zoo(int maxAnimales) {
		this.animales = new Animal[maxAnimales];
	}

	// metodo alta animal
	public void altaAnimal(String nombreAnimal, double peso, String especie, String zoo) {
		for (int i = 0; i < animales.length; i++) {
			if (animales[i] == null) {
				animales[i] = new Animal(nombreAnimal);
				animales[i].setPeso(peso);
				animales[i].setEspecie(especie);
				animales[i].setZoo(zoo);
				break;

			}
		}
	}

	// metodo buscar animal
	public Animal buscarAnimal(String nombreAnimal) {
		for (int i = 0; i < animales.length; i++) {
			if (animales[i] != null) {
				if (animales[i].getNombreAnimal().equals(nombreAnimal)) {
					return animales[i];
				}
			}
		}
		return null;
	}

	// metodo eliminar animal
	public void eliminarAnimal(String nombreAnimal) {
		for (int i = 0; i < animales.length; i++) {
			if (animales[i] != null) {
				if (animales[i].getNombreAnimal().equals(nombreAnimal)) {
					animales[i] = null;
				}
			}
		}
	}
	
	//metodo listar animales
	public void listaAnimales() {
		for (int i=0;i<animales.length;i++) {
			if (animales[i]!=null) {
				System.out.println(animales[i]);
			}
		}
	}
	
	//metodo cambiar nombre animal
	public void cambiarNombreAnimal(String nombreAnimal, String nombreAnimal2) {
		for (int i=0;i<animales.length;i++) {
			if (animales[i]!=null) {
				if (animales[i].getNombreAnimal().equals(nombreAnimal)) {
					animales[i].setNombreAnimal(nombreAnimal2);
				}
			}
		}
	}
	
	//metodo listar por zoo
	public void listarAnimalesPorZoo(String zoo) {
		for (int i=0;i<animales.length;i++) {
			if (animales[i]!=null) {
				if (animales[i].getZoo().equals(zoo)) {
					System.out.println(animales[i]);
				}
			}
		}
	}
	

}
