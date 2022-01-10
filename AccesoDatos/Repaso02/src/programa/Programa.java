package programa;

import clases.GestorTrabajos;

public class Programa {

	public static void main(String[] args) {
		
		GestorTrabajos gestor = new GestorTrabajos();
		
		gestor.altaResponsable("1a", "a");
		gestor.altaResponsable("2b", "b");
		gestor.altaResponsable("2b", "c");

		
		gestor.listarResponsables();
		
		System.out.println(gestor.buscarResponsable("1a"));
		System.out.println(gestor.buscarResponsable("1b"));
		
		gestor.altaTrabajo("t1", "c1", 1.0, "2019-04-02");
		gestor.altaTrabajo("t2", "c2", 1.0, "2019-06-01");
		gestor.altaTrabajo("t3", "c3", 1.0, "2020-08-03");
		
		gestor.asignarResponsable("1a", "t1");
		gestor.asignarResponsable("1a", "t2");
		
		gestor.listarTrabajosResponsables("1a");
		
		gestor.listarTrabajosAnno(2019);
		
		gestor.eliminarTrabajo("t2");
		
		gestor.listarTrabajosResponsables("1a");
		
		gestor.ere();
		
		gestor.listarResponsables();
		
		
	}

}
