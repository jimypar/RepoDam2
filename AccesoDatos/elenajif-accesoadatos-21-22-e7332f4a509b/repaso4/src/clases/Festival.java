package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Festival {

	private ArrayList<Conciertos> listaConciertos;
	private ArrayList<Personal> listaPersonal;
	private Connection conexion;

	public Festival() {
		this.listaConciertos = new ArrayList<Conciertos>();
		this.listaPersonal = new ArrayList<Personal>();
	}

	public ArrayList<Conciertos> getListaConciertos() {
		return listaConciertos;
	}

	public void setListaConciertos(ArrayList<Conciertos> listaConciertos) {
		this.listaConciertos = listaConciertos;
	}

	public ArrayList<Personal> getListaPersonal() {
		return listaPersonal;
	}

	public void setListaPersonal(ArrayList<Personal> listaPersonal) {
		this.listaPersonal = listaPersonal;
	}

	// alta artista
	public void altaArtista(String dni, String nombre, String estiloMusica, String cache) {
		listaPersonal.add(new Artista(dni, nombre, estiloMusica, Float.valueOf(cache)));
		Collections.sort(listaPersonal);
	}

	// alta asistente
	public void altaAsistente(String dni, String nombre, String fechaNacimiento, String nacionalidad) {
		listaPersonal.add(new Asistente(dni, nombre, fechaNacimiento, nacionalidad));
		Collections.sort(listaPersonal);
	}

	// alta concierto (llama a compruebaConcierto y compruebaArtista)
	public void altaConcierto(String codigo, String nombre, String horaInicio, String dniArtista) {
		if (compruebaConcierto(codigo) == false && compruebaArtista(dniArtista)) {
			listaConciertos.add(new Conciertos(codigo, nombre, horaInicio, devuelveArtista(dniArtista)));
			Collections.sort(listaConciertos);
		} else {
			System.out.println("Ha ocurrido un error");
		}
	}

	//registrar asistente concierto
	public void registrarAsistenteConcierto(String codigoConcierto, String dniAsistente) {
		if (compruebaConcierto(codigoConcierto) && compruebaAsistente(dniAsistente)) {
			devuelveConcierto(codigoConcierto).getListaAsistentes().add(devuelveAsistente(dniAsistente));
		} else {
			System.out.println("Ha ocurrido un error");
		}
	}
	
	//listar artistas
	public void listarArtistas() {
		for (Personal personal: listaPersonal) {
			if (personal instanceof Artista) {
				System.out.println(personal);
			}
		}
	}
	
	//listar asistentes
	public void listarAsistentes() {
		for (Personal personal: listaPersonal) {
			if (personal instanceof Asistente) {
				System.out.println(personal);
			}
		}
	}
	
	//guardar datos en fichero
	public void guardarDatos() {
		try {
			ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(new File("src/datos.dat")));
			escritor.writeObject(listaConciertos);
			escritor.writeObject(listaPersonal);
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//cargar datos desde fichero
	public void cargarDatos() {
		try {
			ObjectInputStream cargar = new ObjectInputStream(new FileInputStream(("src/datos.dat")));
			listaConciertos = (ArrayList<Conciertos>) cargar.readObject();
			listaPersonal=(ArrayList<Personal>) cargar.readObject();
			cargar.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//conectar BBDD
	public void conectarBBDD() {
		String servidor="jdbc:mysql://localhost:3307/festival3ev";
		try {
			conexion=DriverManager.getConnection(servidor,"root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//guardar artistas en BBDD
	public void guardarArtistasBBDD(String estiloMusica) {
		String query = "INSERT INTO artistas(dni, nombre, estilo, cache) VALUES(?,?,?,?)";
		try {
			PreparedStatement sentencia=conexion.prepareStatement(query);
			for (Personal personal: listaPersonal) {
				if (personal instanceof Artista && 
						((Artista)personal).getEstiloMusica().equals(estiloMusica)) {
					sentencia.setString(1,personal.getDni());
					sentencia.setString(2, personal.getNombre());
					sentencia.setString(3, ((Artista)personal).getEstiloMusica());
					sentencia.setFloat(4,((Artista)personal).getCache());
					sentencia.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	//cargar asistentes desde BBDD
	//lanzo aqui la excepcion para controlarla en el main
	//controlar o lanzar con orden (no unas si y otras no)
	public void cargarAsistentesBBDD(String nacionalidad) throws SQLException {
		String query ="SELECT * FROM asistentes";
		PreparedStatement sentencia=conexion.prepareStatement(query);
		ResultSet resultado = sentencia.executeQuery();
		
		while (resultado.next()) {
			if(resultado.getString(5).equals(nacionalidad)) {
				listaPersonal.add(new Asistente(resultado.getString(2),resultado.getString(3),
						resultado.getString(4), resultado.getString(5)));
			}
		}
	}
	
	
	//mios (comprobaciones)
	// comprueba concierto
	public boolean compruebaConcierto(String codigo) {
		boolean existe = false;
		for (Conciertos concierto : listaConciertos) {
			if (concierto.getCodigo().equals(codigo)) {
				existe = true;
			}
		}
		return existe;
	}

	// comprueba artista
	public boolean compruebaArtista(String dni) {
		boolean existe = false;
		for (Personal personal : listaPersonal) {
			if (personal instanceof Artista) {
				if (personal.getDni().equals(dni)) {
					existe = true;
				}
			}
		}
		return existe;
	}

	// comprueba asistente
	public boolean compruebaAsistente(String dni) {
		boolean existe = false;
		for (Personal personal : listaPersonal) {
			if (personal instanceof Asistente) {
				if (personal.getDni().equals(dni)) {
					existe = true;
				}
			}
		}
		return existe;
	}

	// devuelve artista
	public Artista devuelveArtista(String dni) {
		for (Personal personal : listaPersonal) {
			if (personal instanceof Artista) {
				if (personal.getDni().equals(dni)) {
					return (Artista) personal;
				}
			}
		}
		return null;
	}

	// devuelve asistente
	public Asistente devuelveAsistente(String dni) {
		for (Personal personal : listaPersonal) {
			if (personal instanceof Asistente) {
				if (personal.getDni().equals(dni)) {
					return (Asistente) personal;
				}
			}
		}
		return null;
	}

	// devuelve conciertos
	public Conciertos devuelveConcierto(String codigo) {
		for (Conciertos concierto: listaConciertos) {
			if (concierto.getCodigo().equals(codigo)) {
				return concierto;
			}
		}
		return null;
	}

}
