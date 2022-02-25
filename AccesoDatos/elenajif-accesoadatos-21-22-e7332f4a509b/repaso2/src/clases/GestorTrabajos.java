package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class GestorTrabajos {
	// atributos
	private ArrayList<Trabajo> listaTrabajos;
	private ArrayList<Responsable> listaResponsables;

	// constructor
	public GestorTrabajos() {
		listaTrabajos = new ArrayList<Trabajo>();
		listaResponsables = new ArrayList<Responsable>();
	}

	// metodos
	public void altaResponsable(String dni, String nombre) {
		if (!existeResponsable(dni)) {
			Responsable nuevoResponsable = new Responsable(dni, nombre);
			nuevoResponsable.setFechaContratacion(LocalDate.now());
			listaResponsables.add(nuevoResponsable);
		} else {
			System.out.println("El responsable ya existe");
		}
	}

	public boolean existeResponsable(String dni) {
		for (Responsable responsable : listaResponsables) {
			if (responsable != null && responsable.getDni().equals(dni)) {
				return true;
			}
		}
		return false;
	}

	public void listarResponsables() {
		for (Responsable responsable : listaResponsables) {
			if (responsable != null) {
				System.out.println(responsable);
			}
		}
	}

	public void altaTrabajo(String nombre, String cliente, Double presupuesto, String fechaConcesion) {
		Trabajo nuevoTrabajo = new Trabajo(nombre, cliente, presupuesto);
		nuevoTrabajo.setFechaConcesion(LocalDate.parse(fechaConcesion));
		listaTrabajos.add(nuevoTrabajo);
	}

	public void eliminarTrabajo(String nombre) {
		Iterator<Trabajo> iteradorTrabajos = listaTrabajos.iterator();

		while (iteradorTrabajos.hasNext()) {
			Trabajo trabajo = iteradorTrabajos.next();
			if (trabajo.getNombre().equals(nombre)) {
				iteradorTrabajos.remove();
			}
		}
	}

	public void asignarResponsable(String dni, String nombreTrabajo) {
		if (buscarResponsable(dni) != null && buscarTrabajo(nombreTrabajo) != null) {
			Responsable jefe = buscarResponsable(dni);
			Trabajo trabajo = buscarTrabajo(nombreTrabajo);
			trabajo.setResponsableTrabajo(jefe);
		}
	}

	public Responsable buscarResponsable(String dni) {
		for (Responsable jefe : listaResponsables) {
			if (jefe != null && jefe.getDni().equals(dni)) {
				return jefe;
			}
		}
		return null;
	}

	public Trabajo buscarTrabajo(String nombreTrabajo) {
		for (Trabajo trabajo : listaTrabajos) {
			if (trabajo != null && trabajo.getNombre().equals(nombreTrabajo)) {
				return trabajo;
			}
		}
		return null;
	}

	public void asignarResponsableExperto(String nombreTrabajo) {
		Trabajo trabajo = buscarTrabajo(nombreTrabajo);
		if (trabajo != null) {
			trabajo.setResponsableTrabajo(buscarResponsableExperto());
		}
	}

	public Responsable buscarResponsableExperto() {
		LocalDate fechaAntigua = null;
		for (int i = 0; i < listaResponsables.size(); i++) {
			Responsable responsableActual = listaResponsables.get(i);
			if (responsableActual != null && i == 0) {
				fechaAntigua = responsableActual.getFechaContratacion();
			} else {
				if (responsableActual != null && responsableActual.getFechaContratacion().isAfter(fechaAntigua)) {
					fechaAntigua = responsableActual.getFechaContratacion();
				}
			}
		}
		for (Responsable jefe : listaResponsables) {
			if (jefe != null && jefe.getFechaContratacion().equals(fechaAntigua)) {
				return jefe;
			}
		}
		return null;
	}

	public void listarTrabajosAnno(int anno) {
		for (Trabajo trabajo : listaTrabajos) {
			if (trabajo.getFechaConcesion().getYear() == anno) {
				System.out.println(trabajo);
			}
		}
	}

	public void listarTrabajosDeResponsable(String dni) {
		for (Trabajo trabajo : listaTrabajos) {
			if (trabajo.getResponsableTrabajo() != null && trabajo.getResponsableTrabajo().getDni().equals(dni)) {
				System.out.println(trabajo);
			}
		}
	}

	public void ere() {
		Iterator<Responsable> iteradorResponsables = listaResponsables.iterator();

		while (iteradorResponsables.hasNext()) {
			Responsable responsableActual = iteradorResponsables.next();
			boolean estaEnTrabajo = false;
			for (Trabajo trabajo : listaTrabajos) {
				if (trabajo.getResponsableTrabajo()!=null 
						&& trabajo.getResponsableTrabajo().getDni().equals(responsableActual.getDni())) {
				estaEnTrabajo=true;	
				}
			}
			if (!estaEnTrabajo) {
				iteradorResponsables.remove();
			}
		}
	}
}
