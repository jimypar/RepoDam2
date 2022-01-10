package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jaime
 *
 */
/**
 * @author Jaime
 *
 */
public class GestorTrabajos {

	private ArrayList<Trabajo> listaTrabajos;
	private ArrayList<Responsable> listaResponsables;

	public GestorTrabajos() {
		super();
		this.listaTrabajos = new ArrayList<Trabajo>();
		this.listaResponsables = new ArrayList<Responsable>();
	}

	public void altaResponsable(String dni, String nombre) {

		if (!existeResponsable(dni)) {

			Responsable responsable = new Responsable(dni, nombre);
			responsable.setFechaContratacion(LocalDate.now());

			listaResponsables.add(responsable);

		} else {

			System.out.println("El responsable ya existe");

		}

	}

	public void listarResponsables() {

		for (Responsable responsable : listaResponsables) {

			if (responsable != null) {

				System.out.println(responsable);

			}
		}

	}

	public void altaTrabajo(String nombre, String cliente, Double presupuesto, String fechaConcesion) {

		if (!existeTrabajo(nombre)) {

			Trabajo trabajo = new Trabajo(nombre, cliente, presupuesto);
			trabajo.setFechaConcesion(LocalDate.parse(fechaConcesion));
			listaTrabajos.add(trabajo);

		} else {

			System.out.println("El trabajo ya existe");

		}

	}

	/**
	 * 
	 * REPASAR
	 * 
	 * @param nombre
	 */
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

	public void asignarResponsableExperto(String nombreTrabajo) {

		Trabajo trabajo = buscarTrabajo(nombreTrabajo);
		if (trabajo != null) {
			trabajo.setResponsableTrabajo(buscarResponsableExperto());
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

	public Trabajo buscarTrabajo(String nombre) {

		for (Trabajo trabajo : listaTrabajos) {
			if (trabajo != null && trabajo.getNombre().equals(nombre)) {

				return trabajo;

			}
		}
		return null;

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

	public void listarTrabajosResponsables(String dni) {

		for (Trabajo trabajo : listaTrabajos) {
			if (trabajo.getResponsableTrabajo() != null && trabajo.getResponsableTrabajo().getDni().equals(dni)) {
				System.out.println(trabajo);
			}
		}

	}

	public void ere() {

		Iterator<Responsable> iteradorResponsables = listaResponsables.iterator();

		while (iteradorResponsables.hasNext()) {
			Responsable responsable = iteradorResponsables.next();
			boolean estaEnTrabajo = false;
			for (Trabajo trabajo : listaTrabajos) {
				if (trabajo.getResponsableTrabajo() != null && trabajo.getResponsableTrabajo().getDni().equals(responsable.getDni())) {
					estaEnTrabajo = true;
				}
			}
			if (!estaEnTrabajo) {
				iteradorResponsables.remove();
			}

		}

	}

	private boolean existeResponsable(String dni) {

		for (Responsable responsable : listaResponsables) {
			if (responsable != null && responsable.getDni().equals(dni)) {

				return true;

			}
		}

		return false;
	}

	private boolean existeTrabajo(String nombre) {

		for (Trabajo trabajo : listaTrabajos) {
			if (trabajo != null && trabajo.getNombre().equals(nombre)) {

				return true;

			}
		}

		return false;

	}

}
