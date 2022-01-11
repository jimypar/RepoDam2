package programa;

import clases.GestorTrabajos;

public class Programa {

	public static void main(String[] args) {
		System.out.println("alta gestor trabajos");
		GestorTrabajos gestor = new GestorTrabajos();
		
		System.out.println();
		System.out.println("alta responsables");
		gestor.altaResponsable("54321", "responsable1");
		gestor.altaResponsable("12345", "responsable2");
		gestor.altaResponsable("12345", "responsable3");

		System.out.println();
		System.out.println("listar responsables");
		gestor.listarResponsables();
		
		System.out.println();
		System.out.println("buscar responsables");
		System.out.println(gestor.buscarResponsable("12345"));
		System.out.println(gestor.buscarResponsable("123456"));
		
		System.out.println();
		System.out.println("alta trabajos");
		gestor.altaTrabajo("trabajo1", "cliente1", 10.0, "2019-04-02");
		gestor.altaTrabajo("trabajo2", "cliente2", 10.0, "2019-05-02");
		gestor.altaTrabajo("trabajo3", "cliente3", 10.0, "2018-04-02");
		
		System.out.println();
		System.out.println("asignar responsables");
		gestor.asignarResponsable("12345", "trabajo1");
		gestor.asignarResponsable("12345", "trabajo2");
		
		System.out.println();
		System.out.println("listar trabajos responsables");
		gestor.listarTrabajosDeResponsable("12345");
		
		System.out.println();
		System.out.println("listar trabajos año");
		System.out.println();
		gestor.listarTrabajosAnno(2019);
		
		System.out.println();
		System.out.println("eliminar trabajo");
		gestor.eliminarTrabajo("trabajo2");
		
		System.out.println();
		System.out.println("listar trabajos responsables");
		gestor.listarTrabajosDeResponsable("12345");
		
		System.out.println();
		System.out.println("ere");
		gestor.ere();
		gestor.listarResponsables();
		
	}

}
