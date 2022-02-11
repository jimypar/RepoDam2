public class Entrenador {

    private String nombre;
    private String nacionalidad;
    private String equipo;

    public Entrenador(String nombre, String nacionalidad, String equipo) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.equipo = equipo;
    }

    public Entrenador() {
    }

    public Entrenador(String nombre) {
        this.nombre = nombre;
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
}
