package main;

public class Domicilio {

	private String direccion;
	private String puerta;
	private String cv;
	private int piso;
	private String localidad;
	private String provincia;

	public Domicilio(String direccion, String puerta, String cv, int piso, String localidad, String provincia) {
		super();
		this.direccion = direccion;
		this.puerta = puerta;
		this.cv = cv;
		this.piso = piso;
		this.localidad = localidad;
		this.provincia = provincia;
	}
	
	

	@Override
	public String toString() {
		return "Domicilio [direccion=" + direccion + ", puerta=" + puerta + ", cv=" + cv + ", piso=" + piso
				+ ", localidad=" + localidad + ", provincia=" + provincia + "]";
	}

}
