package clases;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

public class Conciertos implements Comparable<Conciertos>, Serializable{

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private Time horaInicio;
	private Artista artista;
	private ArrayList<Asistente> listaAsistentes;

	public Conciertos(String codigo, String nombre, String horaInicio, Artista artista) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.horaInicio = Time.valueOf(horaInicio);
		this.artista = artista;
		this.listaAsistentes = new ArrayList<Asistente>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public ArrayList<Asistente> getListaAsistentes() {
		return listaAsistentes;
	}

	public void setListaAsistentes(ArrayList<Asistente> listaAsistentes) {
		this.listaAsistentes = listaAsistentes;
	}


	@Override
	public int compareTo(Conciertos arg0) {
		return getHoraInicio().compareTo(arg0.getHoraInicio());
	}

}
