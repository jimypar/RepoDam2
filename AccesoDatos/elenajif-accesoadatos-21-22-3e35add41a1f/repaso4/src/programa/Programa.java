package programa;

import java.sql.SQLException;

import clases.Festival;

public class Programa {

	public static void main(String[] args) {
		Festival festival = new Festival();
		
		System.out.println("Alta artistas");
		festival.altaArtista("1234", "Jesus", "Pop", "1234");
		festival.altaArtista("5678", "Maria", "Rock", "5678");
		
		System.out.println("Alta asistentes");
		festival.altaAsistente("2222", "Asistente1", "2001-11-04", "spain");
		
		System.out.println("Alta conciertos");
		festival.altaConcierto("1111", "Concierto1", "07:11:00", "1234");
		
		System.out.println("Registrar asistente en concierto");
		festival.registrarAsistenteConcierto("1111", "2222");
		
		System.out.println("Listar artistas");
		festival.listarArtistas();
		
		System.out.println("Listar asistentes");
		festival.listarAsistentes();
		
		System.out.println("Cargar datos");
		festival.cargarDatos();
		
		System.out.println("Guardar datos");
		festival.guardarDatos();
		
		System.out.println("Conectamos a BBDD");
		festival.conectarBBDD();
		
		System.out.println("Guardamos artista Pop");
		festival.guardarArtistasBBDD("Pop");
		
		System.out.println("Cargamos asistentes de BBDD");	
		try {
			festival.cargarAsistentesBBDD("spain");
		} catch (SQLException e) {
			// al lanzar en clase festival tengo que controlar aqui
			e.printStackTrace();
		}
		
		System.out.println("Listar artistas");
		festival.listarArtistas();
		
		System.out.println("Listar asistentes");
		festival.listarAsistentes();
	}

}
