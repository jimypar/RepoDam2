package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.mysql.jdbc.Connection;

public class Festival {

	private ArrayList<Concierto> listaConciertos;
	private ArrayList<Personal> listaPersonal;
	private Connection conexion;

	public Festival() {
		super();
		this.listaConciertos = new ArrayList<Concierto>();
		this.listaPersonal = new ArrayList<Personal>();
	}

	public Festival(ArrayList<Concierto> listaConciertos, ArrayList<Personal> listarPersonal, Connection conexion) {
		super();
		this.listaConciertos = listaConciertos;
		this.listaPersonal = listarPersonal;
		this.conexion = conexion;
	}

	public ArrayList<Concierto> getListaConciertos() {
		return listaConciertos;
	}

	public void setListaConciertos(ArrayList<Concierto> listaConciertos) {
		this.listaConciertos = listaConciertos;
	}

	public ArrayList<Personal> getListarPersonal() {
		return listaPersonal;
	}

	public void setListarPersonal(ArrayList<Personal> listarPersonal) {
		this.listaPersonal = listarPersonal;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}


	public void altaArtista(String dni, String nombre, String estiloMusica, String cache) {
		Artista artista = new Artista(dni, nombre, estiloMusica, Float.valueOf(cache));
		Collections.sort(listaPersonal);
		listaPersonal.add(artista);
	}

	public void altaAsistente(String dni, String nombre, String fechaNacimiento, String nacionalidad) {
		Asistente asistente = new Asistente(dni, nombre, fechaNacimiento, nacionalidad);
		Collections.sort(listaPersonal);
		listaPersonal.add(asistente);
	}

	public void altaConcierto(String codigo, String nombre, String horaInicio, String dniArtista) {
		if (compruebaConcierto(codigo) == false && compruebaArtista(dniArtista)) {
			listaConciertos.add(new Concierto(codigo, nombre, horaInicio, devuelveArtista(dniArtista)));
			Collections.sort(listaConciertos);
		} else {
			System.out.println("Error");
		}
	}

	public Artista devuelveArtista(String dniArtista) {
		for (Personal personal : listaPersonal) {
			if (personal instanceof Artista) {
				if (personal.getDni().equals(dniArtista)) {
					return (Artista) personal;
				}
			}
		}
		return null;
	}

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

	public Concierto devuelveConcierto(String codigo) {
		for (Concierto concierto : listaConciertos) {
			if (concierto.getCodigo().equals(codigo)) {
				return concierto;
			}
		}
		return null;
	}

	public boolean compruebaConcierto(String codigo) {
		for (Concierto concierto : listaConciertos) {
			if (concierto.getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}

	public boolean compruebaArtista(String dni) {
		for (Personal personal : listaPersonal) {
			if (personal instanceof Artista) {
				if (personal.getDni().equals(dni)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean compruebaAsistente(String dni) {
		for (Personal personal : listaPersonal) {
			if (personal instanceof Asistente) {
				if (personal.getDni().equals(dni)) {
					return true;
				}
			}
		}
		return false;
	}

	public void registrarAsistenteConcierto(String codigoConcierto, String dniAsistente) {
		if (compruebaAsistente(dniAsistente) && compruebaConcierto(codigoConcierto)) {
			devuelveConcierto(codigoConcierto).getListaAsistentes().add(devuelveAsistente(dniAsistente));
		} else {
			System.out.println("Ha ocurrido un error");
		}
	}

	public void listarArtistas() {
		for (Personal personal : listaPersonal) {
			if (personal instanceof Artista) {
				System.out.println(personal);
			}
		}
	}

	public void listarAsistente() {
		for (Personal personal : listaPersonal) {
			if (personal instanceof Asistente) {
				System.out.println(personal);
			}
		}
	}

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

	@SuppressWarnings("unchecked")
	public void cargarDatos() {
		try {
			ObjectInputStream cargar = new ObjectInputStream(new FileInputStream(new File("src/datos.dat")));
			listaConciertos = (ArrayList<Concierto>) cargar.readObject();
			listaPersonal = (ArrayList<Personal>) cargar.readObject();
			cargar.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void conectarBBDD() {
		String servidor = "jdbc:mysql://localhost:3307/festival3ev";
		try {
			conexion = (Connection) DriverManager.getConnection(servidor, "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void guardarArtistasBBDD(String estiloMusica) {
		String query = "INSERT INTO artistas(dni, nombre, estilo, cache) VALUES(?,?,?,?)";
		try {
			PreparedStatement sentencia = conexion.prepareStatement(query);
			for (Personal personal : listaPersonal) {
				if (personal instanceof Artista && ((Artista) personal).getEstiloMusica().equals(estiloMusica)) {
					sentencia.setString(1, personal.getDni());
					sentencia.setString(2, personal.getNombre());
					sentencia.setString(3, ((Artista) personal).getEstiloMusica());
					sentencia.setFloat(4, ((Artista) personal).getCache());
					sentencia.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargarAsistentesBBDD(String nacionalidad) throws SQLException {

		String query = "SELECT * FROM asistentes";
		PreparedStatement sentencia = conexion.prepareStatement(query);
		ResultSet rs = sentencia.executeQuery();

		while (rs.next()) {
			if (rs.getString(5).equals(nacionalidad)) {
				listaPersonal.add(new Asistente(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}

		}

	}

}
