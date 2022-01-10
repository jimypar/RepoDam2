import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CargarCSV {

    private ArrayList<String> years;
    private ArrayList<String> incremento;

    //Constructor de la clase cargarCSV que inicia los arrays
    public CargarCSV() {
        this.years = new ArrayList<>();
        this.incremento = new ArrayList<>();
    }

    public ArrayList<String> getYears() {
        return years;
    }

    public void setYears(ArrayList<String> years) {
        this.years = years;
    }

    public ArrayList<String> getIncremento() {
        return incremento;
    }

    public void setIncremento(ArrayList<String> incremento) {
        this.incremento = incremento;
    }

    public void cargarCSV() {

        years = new ArrayList();
        incremento = new ArrayList();

        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("src/csv/evolucion_anual_de_la_concentracion_de_dioxido_de_carbono_(co2)_en_la_atmosferaNOAA.csv"));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(";");

                years.add(campos[0]);
                incremento.add(campos[1]);

                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
