public class Temperatura {

    private String year;
    private String concentracion;

    public Temperatura(String year, String concentracion) {
        this.year = year;
        this.concentracion = concentracion;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }
}

