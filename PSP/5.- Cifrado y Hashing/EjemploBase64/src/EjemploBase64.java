import java.util.Base64;

public class EjemploBase64 {

    public static void main(String[] args) {


        System.out.println("EJEMPLO");
        System.out.println("");

        String entrada = "El profesor de PSP es el más guapo del mundo";
        String cadenaCodificada = Base64.getEncoder().encodeToString(entrada.getBytes());

        System.out.println("codificado: " + cadenaCodificada);

        byte[] bytesDecodificados = Base64.getDecoder().decode(cadenaCodificada);
        String cadenaDecodificada = new String(bytesDecodificados);

        System.out.println("decodificado: " + cadenaDecodificada);


    }


}
