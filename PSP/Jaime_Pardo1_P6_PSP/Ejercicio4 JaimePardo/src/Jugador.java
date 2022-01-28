public class Jugador {

    private String nombre;
    private String nacionalidad;
    private String equipo;
    private String posicion;

    public Jugador(String nombre, String nacionalidad, String equipo, String posicion) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.equipo = equipo;
        this.posicion = posicion;
    }

    public Jugador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
